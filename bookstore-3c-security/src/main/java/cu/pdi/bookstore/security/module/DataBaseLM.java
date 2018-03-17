/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.security.module;


import cu.pdi.bookstore.security.Encriptador;
import cu.pdi.bookstore.security.entities.SecurityPerson;
import cu.pdi.bookstore.security.entities.SecurityRole;
import cu.pdi.bookstore.security.jdbc.JaasSecurityRepository;
import cu.pdi.bookstore.security.module.exception.LoginCancelledException;
import cu.pdi.bookstore.security.principals.PasswordPrincipal;
import cu.pdi.bookstore.security.principals.RolePrincipal;
import cu.pdi.bookstore.security.principals.UserPrincipal;
import cu.pdi.bookstore.security.application.config.InternalBeansConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author R.S.F.
 */
public class DataBaseLM implements LoginModule {

    //
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;
    // configurable option
    private boolean debug = false;
    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;
    //user credentials
    private String username = null;
    private String password = null;
    private String nombre = null;
    private String pApellido = null;
    private String sApellido = null;
    //user principals
    private UserPrincipal userPrincipal = null;
    private PasswordPrincipal passwordPrincipal = null;

    private JaasSecurityRepository jaasSecurityRepository;

    @Override
    public void initialize(Subject sbjct, CallbackHandler ch, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = sbjct;
        this.callbackHandler = ch;
        this.sharedState = sharedState;
        this.options = options;
        jaasSecurityRepository = new AnnotationConfigApplicationContext(InternalBeansConfig.class)
                .getBean(JaasSecurityRepository.class);
        jaasSecurityRepository.initQueries(options);
        initAdminUser();
        debug = "true".equalsIgnoreCase((String) options.get("debug"));
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new RuntimeException("Error: no callbackHandler available "
                    + "to gather authentication information from the user");
        }
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("username");
        callbacks[1] = new PasswordCallback("password: ", false);
        boolean loginResult = false;

        try {

            callbackHandler.handle(callbacks);
            username = ((NameCallback) callbacks[0]).getName();
            password = new String(((PasswordCallback) callbacks[1]).getPassword());

            if (debug) {
                System.out.print("Username :" + username + "\n");
                System.out.print("Password : " + Encriptador.getStringMessageDigest(password, Encriptador.SHA256) + "\n");
            }

            if (username.isEmpty() || password.isEmpty()) {
                succeeded = true;
                throw new LoginException("Callback handler does not return login data properly");
            }else if(username.equals("cancelled")){
                throw new LoginCancelledException();

            } else if (isValidUser()) { //validate user.
                succeeded = true;
                loginResult = true;
            }

        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        return loginResult;
    }

    @Override
    public boolean commit() throws LoginException {
        if (!succeeded) {
            return false;
        } else {
            userPrincipal = new UserPrincipal(username, nombre, pApellido, sApellido);
            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
            }

            passwordPrincipal = new PasswordPrincipal(Encriptador.getStringMessageDigest(password, Encriptador.SHA256));
            if (!subject.getPrincipals().contains(passwordPrincipal)) {
                subject.getPrincipals().add(passwordPrincipal);
            }
            //populate subject with roles.
            List<SecurityRole> roles = getRoles();

            roles.stream().map((SecurityRole securityRole) ->
                    new RolePrincipal(securityRole.getRole())
            ).forEach((RolePrincipal rolePrincipal) -> {
                if (!subject.getPrincipals().contains(rolePrincipal)) {
                    subject.getPrincipals().add(rolePrincipal);
                }
            });
            commitSucceeded = true;


            return true;
        }
    }

    @Override
    public boolean abort() throws LoginException {
        if (!succeeded) {
            return false;
        } else if (succeeded && !commitSucceeded) {
            succeeded = false;
            username = null;
            if (password != null) {
                password = null;
            }
            userPrincipal = null;
        } else {
            logout();
        }
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;
        if (password != null) {
            password = null;
        }
        userPrincipal = null;
        return true;
    }

    private void initAdminUser() {
        jaasSecurityRepository.createDefaultAdminUser();

    }

    private boolean isValidUser() {
        boolean result = false;

        String hashPassword = Encriptador.getStringMessageDigest(password, Encriptador.SHA256);
        final SecurityPerson securityPerson = jaasSecurityRepository.getUserAssociatedPerson(username, hashPassword).orElse(null);
        if (securityPerson != null) {
            result = true;
            nombre = securityPerson.getNombre();
            pApellido = securityPerson.getPrimerApellido();
            sApellido = securityPerson.getSegundoApellido();
        }

        return result;

    }

    private List<SecurityRole> getRoles() {
        return jaasSecurityRepository.getUserAssociatedRoles(username);
    }
}
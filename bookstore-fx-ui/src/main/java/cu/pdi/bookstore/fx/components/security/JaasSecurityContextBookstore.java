package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.util.Objects;

@Component
public class JaasSecurityContextBookstore implements JaasSecurityContext {

    static {
        System.setProperty("java.security.auth.login.config", Objects.requireNonNull(JaasSecurityContext.class.getClassLoader().getResource("config/jaas.config")).toExternalForm());
    }

    private Subject authenticatedSubject;
    private LoginContext loginContext;

    public JaasSecurityContextBookstore() {

    }

    @Override
    public boolean logIn(CallbackHandler callbackHandler) throws LoginException {
        loginContext = new LoginContext("authClient", callbackHandler);
        loginContext.login();

        this.authenticatedSubject = loginContext.getSubject();

        return this.authenticatedSubject != null;
    }

    @Override
    public void logOut() throws LoginException {
        loginContext.logout();
    }

    public Subject getAuthenticatedSubject() {
        return authenticatedSubject;
    }
}

package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.fx.components.security.callbackHandler.AuthDialogCallbackHandlerFX;
import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Objects;

@Component
public class JaasSecurityContextBookstore implements JaasSecurityContext {

    static{
        System.setProperty("java.security.auth.login.config", Objects.requireNonNull(JaasSecurityContext.class.getClassLoader().getResource("config/jaas.config")).toExternalForm());
    }

    private CallbackHandler callbackHandler;
    private Subject authenticatedSubject;

    public JaasSecurityContextBookstore(FXMLLocator fxmlLocator) throws IOException {
        this.callbackHandler = new AuthDialogCallbackHandlerFX(fxmlLocator);
    }

    @Override
    public boolean logIn() throws LoginException {

        LoginContext loginContext = new LoginContext("authClient", callbackHandler);
        loginContext.login();

        this.authenticatedSubject = loginContext.getSubject();

        return this.authenticatedSubject != null;
    }

    @Override
    public void logOut() throws LoginException {

    }

    @Override
    public Subject getAuthenticatedUser() {
        return this.authenticatedSubject;
    }
}

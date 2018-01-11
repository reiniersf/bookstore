package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.fx.components.security.callbackHandler.AuthDialogCallbackHandlerFX;
import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;

@Component
public class JaasSecurityContextBookstore implements JaasSecurityContext {

    static{
        System.setProperty("java.security.auth.login.config", JaasSecurityContext.class.getClassLoader().getResource("config/jaas.config").toExternalForm());
    }

    private final FXMLLocator fxmlLocator;

    public JaasSecurityContextBookstore(FXMLLocator fxmlLocator) {
        this.fxmlLocator = fxmlLocator;
    }

    @Override
    public Subject logIn() throws IOException, LoginException {

        LoginContext loginContext = new LoginContext("authClient", new AuthDialogCallbackHandlerFX(fxmlLocator));
        loginContext.login();

        return loginContext.getSubject();
    }

    @Override
    public void logOut() throws LoginException {

    }
}

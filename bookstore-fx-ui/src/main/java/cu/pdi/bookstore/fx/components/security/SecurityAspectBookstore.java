package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.LoginException;
import java.io.IOException;

@Aspect
@Component
public class SecurityAspectBookstore {

    private final JaasSecurityContext jaasSecurityContext;

    @Autowired
    public SecurityAspectBookstore(JaasSecurityContext jaasSecurityContext) {
        this.jaasSecurityContext = jaasSecurityContext;
    }

    @Before("execution(* cu.pdi.bookstore.fx.gui.LauncherBookstore.launchApp(..)) && " +
            "@annotation(cu.pdi.bookstore.fx.components.security.annotation.LoginRequired)")
    public void checkCredentials() throws IOException, LoginException {
        jaasSecurityContext.logIn();

    }
}

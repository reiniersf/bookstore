package cu.pdi.bookstore.fx.components.security;

import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.springframework.stereotype.Component;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

@Component
public class JaasSecurityContextBookstore implements JaasSecurityContext {

    @Override
    public Subject logIn() {
        System.out.println("LOG IN THE USER!!");
        return null;
    }

    @Override
    public void logOut() throws LoginException {

    }
}

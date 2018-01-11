package cu.pdi.bookstore.security.context;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import java.io.IOException;

/**
 * Created by R.S.F
 */
public interface JaasSecurityContext {

    Subject logIn() throws IOException, LoginException;

    void logOut() throws LoginException;

}

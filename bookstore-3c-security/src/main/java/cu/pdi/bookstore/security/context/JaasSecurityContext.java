package cu.pdi.bookstore.security.context;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

/**
 * Created by R.S.F
 */
public interface JaasSecurityContext {

    Subject logIn();

    void logOut() throws LoginException;

}

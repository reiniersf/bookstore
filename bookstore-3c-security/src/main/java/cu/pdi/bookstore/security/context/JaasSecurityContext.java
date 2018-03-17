package cu.pdi.bookstore.security.context;

import cu.pdi.bookstore.security.principals.UserPrincipal;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by R.S.F
 */
public interface JaasSecurityContext {

    boolean logIn() throws IOException, LoginException;

    void logOut() throws LoginException;

    Subject getAuthenticatedUser();

    Optional<String> authenticatedUsername();

    Optional<String> authenticatedUserRoleName();
}

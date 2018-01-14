package cu.pdi.bookstore.security.module.exception;

import javax.security.auth.login.LoginException;

public class LoginCancelledException extends LoginException {
    public LoginCancelledException() {
        super("Login cancelled by the user");
    }
}

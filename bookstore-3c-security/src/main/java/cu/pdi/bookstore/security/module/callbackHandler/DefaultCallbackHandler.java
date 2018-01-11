package cu.pdi.bookstore.security.module.callbackHandler;

import javax.security.auth.callback.*;
import java.io.IOException;

public class DefaultCallbackHandler implements CallbackHandler {

    private final String username;
    private final char [] password;

    public DefaultCallbackHandler(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        ((NameCallback) callbacks[0]).setName(username);
        ((PasswordCallback) callbacks[1]).setPassword(password);
    }
}

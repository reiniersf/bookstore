/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.components.security.callbackHandler;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.util.List;

/**
 * @author R.S.F.
 */
public class SimpleCallbackHandler implements CallbackHandler {

    private List<String> authData;

    public SimpleCallbackHandler(List<String> credentials) {
        authData = credentials;
    }

    @Override
    public void handle(Callback[] callbacks) {

        if (authData != null) {
            ((NameCallback) callbacks[0]).setName(authData.get(0));
            ((PasswordCallback) callbacks[1]).setPassword(authData.get(1).toCharArray());
        }

        authData = null;
    }

}

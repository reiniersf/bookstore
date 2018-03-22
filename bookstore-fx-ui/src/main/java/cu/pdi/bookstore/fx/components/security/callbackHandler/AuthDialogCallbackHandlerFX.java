/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.components.security.callbackHandler;

import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author R.S.F.
 */
public class AuthDialogCallbackHandlerFX implements CallbackHandler {

    private String usuario = "";
    private char[] password = "".toCharArray();
    private Stage dialog;
    private List<String> datosAutenticacion;

    public AuthDialogCallbackHandlerFX(FXMLLocator fxmlLocator) throws IOException {
        dialog = new Stage();
        dialog.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> datosAutenticacion = Arrays.asList("cancelled", "no_password"));
        dialog.addEventFilter(WindowEvent.WINDOW_HIDING, event -> obtenerDatos());
        Parent root = fxmlLocator.getFXML("auth/autenticar.fxml");
        dialog.setTitle(ResourceBundle.getBundle("labels").getString("auth.title"));
        dialog.setScene(new Scene(root));
        dialog.setResizable(false);

    }

    @Override
    public void handle(Callback[] callbacks) {

        this.dialog.showAndWait();

        if (datosAutenticacion != null) {
            usuario = datosAutenticacion.get(0);
            password = datosAutenticacion.get(1).toCharArray();
        }

        ((NameCallback) callbacks[0]).setName(usuario);
        ((PasswordCallback) callbacks[1]).setPassword(password);

        datosAutenticacion = null;
    }

    @SuppressWarnings("unchecked")
    private void obtenerDatos() {
        if (datosAutenticacion == null) {
            Object userData = dialog.getScene().getRoot().getUserData();
            datosAutenticacion = (List<String>) userData;
        }
    }
}

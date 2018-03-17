/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.gui.auth;

import cu.pdi.bookstore.fx.components.ui.ResourceLocator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author R.S.F.
 */
@Component
public class AutenticarController implements Initializable {

    @FXML
    private TextField tx_usuario;
    @FXML
    private PasswordField password;
    @FXML
    private Button autenticar;
    @FXML
    private Button btn_Cancelar;
    @FXML
    private ImageView imagUsuario;
    private final ResourceLocator resourceLocator;

    private final int MIN_USERNAME_LENGTH = 5;
    private final int MIN_PASSWORD_LENGTH = 8;

    @Autowired
    public AutenticarController(ResourceLocator resourceLocator) {
        this.resourceLocator = resourceLocator;
    }

    @FXML
    public void Iniciar() {
        String usuario = tx_usuario.getText();
        String contrasenna = password.getText();
        autenticar.getScene().getRoot().setUserData(Arrays.asList(usuario, contrasenna));
        autenticar.getScene().getWindow().hide();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        autenticar.setDisable(true);
        autenticar.setDefaultButton(true);
        btn_Cancelar.setCancelButton(true);
        password.textProperty().addListener((ov, oldUsernameInput, newUsernameInput) -> {
            if (!tx_usuario.getText().isEmpty() && newUsernameInput.length() >= MIN_USERNAME_LENGTH) {
                autenticar.setDisable(false);
            } else {
                autenticar.setDisable(true);
            }
        });
        tx_usuario.textProperty().addListener((ov, oldPasswordInput, newPasswordInput) -> {
            if (!newPasswordInput.isEmpty() && password.getText().length() >= MIN_PASSWORD_LENGTH) {
                autenticar.setDisable(false);
            } else {
                autenticar.setDisable(true);
            }
        });
        resourceLocator.urlForImage("user-icon.png")
                .ifPresent(imageUrl -> imagUsuario.setImage(new Image(imageUrl)));

    }

    @FXML
    private void Close() {
        btn_Cancelar.getScene().getRoot().setUserData(Arrays.asList("cancelled", "no_password"));
        ((Stage) btn_Cancelar.getScene().getWindow()).close();
    }

}

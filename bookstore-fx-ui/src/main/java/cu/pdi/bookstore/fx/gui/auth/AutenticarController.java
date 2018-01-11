/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.gui.auth;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
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

    private final int MIN_USERNAME_LENGTH = 5;
    private final int MIN_PASSWORD_LENGTH = 8;

    @FXML
    public void Iniciar() {
        String usuario = tx_usuario.getText();
        String contrasenna = password.getText();
        ArrayList<String> datos = new ArrayList<>();
        datos.add(usuario);
        datos.add(contrasenna);


        autenticar.getScene().getRoot().setUserData(datos);

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
        imagUsuario.setImage(new Image(getClass().getClassLoader().getResourceAsStream("images/Icon-user.png")));
    }

    @FXML
    private void Close() {

        Stage s = (Stage) btn_Cancelar.getScene().getWindow();
        ArrayList<String> datos = new ArrayList<>();
        datos.add("");
        datos.add("");
        btn_Cancelar.getScene().getRoot().setUserData(datos);
        s.close();

    }

}

package cu.pdi.bookstore.fx.gui.auth;

import cu.pdi.bookstore.fx.components.ui.events.SimpleUIEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ChangePasswordController implements Initializable {
    @FXML
    private Button btnChangePassword;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;

    @EventListener
    public void resetFields(SimpleUIEvent uiEvent) {
        currentPassword.setText("");
        newPassword.setText("");
        confirmPassword.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentPassword.setPromptText("Contraseña actual...");
        newPassword.setPromptText("Contraseña nueva...");
        confirmPassword.setPromptText("Confirmación...");

        btnChangePassword.setDefaultButton(true);
    }
}

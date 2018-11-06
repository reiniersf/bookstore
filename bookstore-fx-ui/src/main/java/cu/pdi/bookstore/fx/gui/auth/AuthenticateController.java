/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.gui.auth;

import cu.pdi.bookstore.fx.components.security.events.FailedLoginEvent;
import cu.pdi.bookstore.fx.components.security.events.ReadyToLoginEvent;
import cu.pdi.bookstore.fx.components.ui.MessageUIBuilder;
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
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * @author R.S.F.
 */
@Component
public class AuthenticateController implements Initializable {

    @FXML
    private TextField txUser;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btnAuthenticate;
    @FXML
    private Button btnCancel;
    @FXML
    private ImageView imgUser;
    private final ResourceLocator resourceLocator;
    private final ApplicationEventPublisher eventPublisher;
    private final MessageUIBuilder messageUIBuilder;

    private final int MIN_USERNAME_LENGTH = 5;
    private final int MIN_PASSWORD_LENGTH = 8;

    @Autowired
    public AuthenticateController(ResourceLocator resourceLocator, ApplicationEventPublisher eventPublisher, MessageUIBuilder messageUIBuilder) {
        this.resourceLocator = resourceLocator;
        this.eventPublisher = eventPublisher;
        this.messageUIBuilder = messageUIBuilder;


    }

    @FXML
    public void init() {
        String username = txUser.getText();
        String password = pfPassword.getText();
        eventPublisher.publishEvent(ReadyToLoginEvent.withCredentials(Arrays.asList(username, password)));
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAuthenticate.setDisable(true);
        btnAuthenticate.setDefaultButton(true);
        btnCancel.setCancelButton(true);
        pfPassword.textProperty().addListener((ov, oldUsernameInput, newUsernameInput) -> {
            if (!txUser.getText().isEmpty() && newUsernameInput.length() >= MIN_USERNAME_LENGTH) {
                btnAuthenticate.setDisable(false);
            } else {
                btnAuthenticate.setDisable(true);
            }
        });
        txUser.textProperty().addListener((ov, oldPasswordInput, newPasswordInput) -> {
            if (!newPasswordInput.isEmpty() && pfPassword.getText().length() >= MIN_PASSWORD_LENGTH) {
                btnAuthenticate.setDisable(false);
            } else {
                btnAuthenticate.setDisable(true);
            }
        });
        resourceLocator.urlForImage("user-icon.png")
                .ifPresent(imageUrl -> imgUser.setImage(new Image(imageUrl)));

    }

    @FXML
    private void close() {
        btnCancel.getScene().getRoot().setUserData(Arrays.asList("cancelled", "no_password"));
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @EventListener
    public void onLoginFailed(FailedLoginEvent loginEvent) {
        messageUIBuilder.createMessage(loginEvent.getFailureMessage(), loginEvent.getMessageUIConfig())
                .show();
        cleanFields();

    }

    private void cleanFields() {
        txUser.setText("");
        txUser.requestFocus();
        pfPassword.setText("");

    }

}

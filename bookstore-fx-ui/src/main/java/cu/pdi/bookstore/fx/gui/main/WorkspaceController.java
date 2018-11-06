package cu.pdi.bookstore.fx.gui.main;

import cu.pdi.bookstore.fx.components.security.Role;
import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import cu.pdi.bookstore.fx.components.ui.I18nHandler;
import cu.pdi.bookstore.fx.components.ui.MenuAssembler;
import cu.pdi.bookstore.fx.components.ui.ResourceLocator;
import cu.pdi.bookstore.fx.components.ui.events.SimpleUIEvent;
import cu.pdi.bookstore.fx.components.security.events.SuccessLoginEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WorkspaceController implements Initializable {
    @FXML
    private TabPane container;
    @FXML
    private Label personName;
    @FXML
    private Label rolName;
    @FXML
    private BorderPane root;
    @FXML
    private Button btnChangePassword;
    @FXML
    private MenuButton btnGeneralMenu;

    private final ResourceLocator resourceLocator;
    private final FXMLLocator fxmlLocator;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final MenuAssembler menuAssembler;
    private final I18nHandler i18nHandler;

    @Autowired
    public WorkspaceController(
            ResourceLocator resourceLocator,
            FXMLLocator fxmlLocator, ApplicationEventPublisher applicationEventPublisher,
            MenuAssembler menuAssembler, I18nHandler i18nHandler) {
        this.resourceLocator = resourceLocator;
        this.fxmlLocator = fxmlLocator;
        this.applicationEventPublisher = applicationEventPublisher;
        this.menuAssembler = menuAssembler;
        this.i18nHandler = i18nHandler;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadInitialState();
    }

    private void loadInitialState() {
        loadDefaultContent();
        loadMenuIcon();
        btnGeneralMenu.setDisable(true);
        btnChangePassword.setDisable(true);
    }

    @EventListener
    public void onSuccessFullLogin(SuccessLoginEvent successLoginEvent) {
        successLoginEvent.authenticatedUsername().ifPresent(personName::setText);
        successLoginEvent.authenticatedUserRoleName().ifPresent(rolName::setText);

        initializeGeneralMenu();
        loadAuthorizedMenuActions(successLoginEvent);
        initializeChangePasswordPopOver();
        loadDefaultContent();
    }

    private void loadDefaultContent() {
        root.setCenter(fxmlLocator.getFXML("default.fxml"));
    }

    private void initializeGeneralMenu() {
        btnGeneralMenu.setText("");
        btnGeneralMenu.setDisable(false);
        btnGeneralMenu.setPopupSide(Side.RIGHT);
        loadMenuIcon();
    }

    private void loadMenuIcon() {
        resourceLocator.urlForImage("menu_icon.png")
                .ifPresent(imageUrl -> btnGeneralMenu.setGraphic(new ImageView(new Image(imageUrl))));
    }

    private void loadAuthorizedMenuActions(SuccessLoginEvent successLoginEvent) {
        Role role = successLoginEvent.authenticatedUserRoleName()
                .map(Role::valueOf)
                .orElseThrow(() -> new RuntimeException("No existent role"));
        btnGeneralMenu.getItems().clear();
        btnGeneralMenu.getItems().addAll(menuAssembler.assembleForRole(role));
    }

    private void initializeChangePasswordPopOver() {
        PopOver popOver = new PopOver(
                fxmlLocator.getFXML("auth/change_password.fxml")
        );
        popOver.setAutoHide(true);
        popOver.setArrowLocation(PopOver.ArrowLocation.BOTTOM_RIGHT);
        popOver.setArrowIndent(5d);
        popOver.setArrowSize(5d);
        popOver.setCloseButtonEnabled(false);
        popOver.setTitle(i18nHandler.labelForKey("workspace.changepassword.title"));
        popOver.setHeaderAlwaysVisible(true);
        popOver.setOnAutoHide(autoHideEvent -> applicationEventPublisher.publishEvent(SimpleUIEvent.RESET_FIELDS));
        popOver.setHideOnEscape(true);

        btnChangePassword.setOnAction(e -> popOver.show(btnChangePassword));
        btnChangePassword.setDisable(false);
    }

    public void renderContent(Parent content) {
        root.setCenter(content);
    }
}

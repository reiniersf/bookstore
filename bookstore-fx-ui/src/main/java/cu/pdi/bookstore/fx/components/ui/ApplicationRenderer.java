package cu.pdi.bookstore.fx.components.ui;

import cu.pdi.bookstore.fx.components.security.events.LoginRequiredEvent;
import cu.pdi.bookstore.fx.components.security.events.SuccessLoginEvent;
import cu.pdi.bookstore.fx.components.ui.events.WorkspaceRenderEvent;
import cu.pdi.bookstore.fx.gui.main.WorkspaceController;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRenderer {

    private final WorkspaceController workspaceController;
    private final FXMLLocator fxmlLocator;
    private StackPane sceneRoot;

    @Autowired
    public ApplicationRenderer(WorkspaceController workspaceController, FXMLLocator fxmlLocator) {
        this.workspaceController = workspaceController;
        this.fxmlLocator = fxmlLocator;
    }

    @EventListener
    public void processWorkspaceRenderEvent(WorkspaceRenderEvent renderEvent) {
        workspaceController.renderContent(fxmlLocator.getFXML(renderEvent.getFxmlToShow()));
    }

    @EventListener
    public void showLoginScreen(LoginRequiredEvent loginRequiredEvent) {
        this.sceneRoot = loginRequiredEvent.getSceneRoot();
        initBlockingScreen();
        showLogin(loginRequiredEvent.loginFxml());

    }

    private void initBlockingScreen() {
        AnchorPane blockingPane = new AnchorPane();
        blockingPane.setStyle("-fx-background-color: rgba(0,0,0,0.5);");
        sceneRoot.getChildren().add(blockingPane);
    }

    private void showLogin(String loginFxml) {
        Parent loginScreen = fxmlLocator.getFXML(loginFxml);
        AnchorPane loginBox = new AnchorPane(loginScreen);

        AnchorPane.setLeftAnchor(loginScreen, 100d);
        AnchorPane.setTopAnchor(loginScreen, 50d);

        loginBox.getStyleClass().add("login-background");

        sceneRoot.getChildren().add(loginBox);
    }

    @EventListener
    public void onSuccessFullLogin(SuccessLoginEvent successLoginEvent) {
        hideLogin();
    }

    private void hideLogin() {
        sceneRoot.getChildren().remove(sceneRoot.getChildren().size() - 1);
        sceneRoot.getChildren().remove(sceneRoot.getChildren().size() - 1);
    }
}

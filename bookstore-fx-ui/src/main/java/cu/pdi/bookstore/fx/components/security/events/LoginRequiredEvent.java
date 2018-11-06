package cu.pdi.bookstore.fx.components.security.events;

import cu.pdi.bookstore.fx.components.ui.events.RenderEvent;
import javafx.scene.layout.StackPane;
import lombok.Getter;

public class LoginRequiredEvent implements RenderEvent<String> {
    @Getter
    private final StackPane sceneRoot;

    private LoginRequiredEvent(StackPane sceneRoot) {

        this.sceneRoot = sceneRoot;
    }

    public static LoginRequiredEvent on(StackPane sceneRoot) {
        return new LoginRequiredEvent(sceneRoot);
    }

    public String loginFxml() {
        return "auth/authenticate.fxml";
    }
}

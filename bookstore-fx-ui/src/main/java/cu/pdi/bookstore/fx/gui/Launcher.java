package cu.pdi.bookstore.fx.gui;

import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import cu.pdi.bookstore.fx.components.ui.I18nHandler;
import cu.pdi.bookstore.fx.components.security.events.LoginRequiredEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class Launcher {
    private static final Logger log = LoggerFactory.getLogger(Launcher.class);

    private final FXMLLocator fxmlLocator;
    private final I18nHandler i18nHandler;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public Launcher(FXMLLocator fxmlLocator, I18nHandler i18nHandler , ApplicationEventPublisher eventPublisher) {
        this.fxmlLocator = fxmlLocator;
        this.i18nHandler = i18nHandler;
        this.eventPublisher = eventPublisher;
    }

    public void launchApp(Stage stage) {
        log.info("Starting Bookstore JavaFX application");

        Parent workspace = fxmlLocator.getFXML("main/workspace.fxml");
        StackPane sceneRoot = new StackPane(workspace);

        eventPublisher.publishEvent(LoginRequiredEvent.on(sceneRoot));

        Scene scene = new Scene(sceneRoot, 1024, 768);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle(i18nHandler.labelForKey("workspace.title"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}

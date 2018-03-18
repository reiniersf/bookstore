package cu.pdi.bookstore.fx.gui;

import cu.pdi.bookstore.fx.components.security.annotation.LoginRequired;
import cu.pdi.bookstore.fx.components.ui.FXMLLocator;
import cu.pdi.bookstore.fx.components.ui.I18nHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LauncherBookstore {
    private static final Logger log = LoggerFactory.getLogger(LauncherBookstore.class);

    private final FXMLLocator fxmlLocator;
    private final I18nHandler i18nHandler;

    @Autowired
    public LauncherBookstore(FXMLLocator fxmlLocator, I18nHandler i18nHandler) {
        this.fxmlLocator = fxmlLocator;
        this.i18nHandler = i18nHandler;
    }

    @LoginRequired
    public void launchApp(Stage stage) throws IOException {
        log.info("Starting Bookstore JavaFX application");

        Parent fxml = fxmlLocator.getFXML("main/workspace.fxml");

        Scene scene = new Scene(fxml, 1024, 768);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle(i18nHandler.labelForKey("workspace.title"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

}

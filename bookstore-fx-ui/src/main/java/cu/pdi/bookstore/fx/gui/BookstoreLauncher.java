package cu.pdi.bookstore.fx.gui;

import cu.pdi.bookstore.fx.MainApp;
import cu.pdi.bookstore.fx.components.FXMLLocator;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookstoreLauncher {
    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    private final FXMLLocator fxmlLocator;

    @Autowired
    public BookstoreLauncher(FXMLLocator fxmlLocator) {
        this.fxmlLocator = fxmlLocator;
    }


    public void launchApp(Stage stage) throws IOException {
        log.info("Starting Bookstore JavaFX application");

        Parent fxml = fxmlLocator.getFXML("main/workspace.fxml");

        Scene scene = new Scene(fxml, 400, 200);
        scene.getStylesheets().add("/styles/styles.css");

        stage.setTitle("GesLib v1.1");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}

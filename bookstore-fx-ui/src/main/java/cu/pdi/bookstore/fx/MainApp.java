package cu.pdi.bookstore.fx;

import cu.pdi.bookstore.fx.application.config.FxAppConfig;
import cu.pdi.bookstore.fx.gui.Launcher;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {
    static {
        if (System.getProperty("spring.profiles.active") == null)
            System.setProperty("spring.profiles.active", "dev");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FxAppConfig.class);
        Launcher bookstoreLauncher = applicationContext.getBean(Launcher.class);
        bookstoreLauncher.launchApp(stage);

    }
}

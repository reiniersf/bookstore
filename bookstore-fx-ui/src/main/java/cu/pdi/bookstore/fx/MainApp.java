package cu.pdi.bookstore.fx;

import cu.pdi.bookstore.fx.application.config.FxAppConfig;
import cu.pdi.bookstore.fx.gui.BookstoreLauncher;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FxAppConfig.class);
        BookstoreLauncher bookstoreLauncher = applicationContext.getBean(BookstoreLauncher.class);
        bookstoreLauncher.launchApp(stage);

    }
}

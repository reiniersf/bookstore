package cu.pdi.bookstore.fx;

import cu.pdi.bookstore.fx.application.config.FxAppConfig;
import cu.pdi.bookstore.fx.gui.LauncherBookstore;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MainApp extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage stage) throws Exception {

        System.setProperty("spring.profiles.active", "dev");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FxAppConfig.class);
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        LauncherBookstore bookstoreLauncher = applicationContext.getBean(LauncherBookstore.class);
        bookstoreLauncher.launchApp(stage);

    }
}

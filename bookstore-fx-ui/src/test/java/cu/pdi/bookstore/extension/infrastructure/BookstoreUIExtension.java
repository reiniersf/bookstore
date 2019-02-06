package cu.pdi.bookstore.extension.infrastructure;

import cu.pdi.bookstore.extension.annotation.bdd.AssumeUserAuthenticated;
import cu.pdi.bookstore.fx.MainApp;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import java.util.concurrent.TimeoutException;

public class BookstoreUIExtension implements BeforeEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    static {
        System.setProperty("spring.profiles.active", "test");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        restartApplication();
        FxToolkit.showStage();
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        executeAuthenticationIfAssumed(extensionContext);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
            FxToolkit.hideStage();
    }

    private void executeAuthenticationIfAssumed(ExtensionContext extensionContext) {
        FxRobot robot = new FxRobot();
        extensionContext.getTestMethod()
                .flatMap(method -> AnnotationSupport.findAnnotation(method, AssumeUserAuthenticated.class))
                .ifPresent(assumeUserAuthenticated -> {
                    robot.clickOn("#txUser").write(assumeUserAuthenticated.username());
                    robot.clickOn("#pfPassword").write(assumeUserAuthenticated.password());
                    robot.type(KeyCode.ENTER);
                });
    }

    private void restartApplication() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainApp.class);
    }

}

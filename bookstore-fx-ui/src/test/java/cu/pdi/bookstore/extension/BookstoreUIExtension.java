package cu.pdi.bookstore.extension;

import cu.pdi.bookstore.extension.annotation.AssumeUserAuthenticated;
import cu.pdi.bookstore.fx.MainApp;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class BookstoreUIExtension implements BeforeEachCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainApp.class);
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        FxRobot robot = new FxRobot();
        FxToolkit.showStage();
        extensionContext.getTestMethod()
                .flatMap(method -> AnnotationSupport.findAnnotation(method, AssumeUserAuthenticated.class))
                .ifPresent(assumeUserAuthenticated -> {
                    robot.clickOn("#txUser").write(assumeUserAuthenticated.username());
                    robot.clickOn("#pfPassword").write(assumeUserAuthenticated.password());
                    robot.type(KeyCode.ENTER);
                });
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        FxToolkit.hideStage();
    }


}

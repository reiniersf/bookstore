package cu.pdi.bookstore;

import cu.pdi.bookstore.fx.MainApp;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ATest {
    private final FxRobot robot = new FxRobot();
    private Stage primaryStage;

    @BeforeEach
    public void setup() throws Exception {
        primaryStage = FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(MainApp.class);
    }

    @Test
    void should() throws TimeoutException {
        WaitForAsyncUtils.waitForFxEvents();
        FxToolkit.showStage();

        FxToolkit.hideStage();
    }
}

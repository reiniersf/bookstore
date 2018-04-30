import cu.pdi.bookstore.fx.application.config.FxAppConfig;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

@ExtendWith({ApplicationExtension.class})
//@ContextConfiguration(classes = FxAppConfig.class)
//@ActiveProfiles("dev")
public class UITests extends ApplicationTest {

    private Button fB;

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        fB = new Button("First stack");
        fB.setOnAction(action -> {
            vBox.getChildren().add(new StackPane(new Button(vBox.getChildren().size() + " stack")));
        });
        vBox.getChildren().addAll(
                new StackPane(fB),
                new StackPane(new Button("Second stack")),
                new StackPane(new Button("Third stack")),
                new StackPane(new Button("Fourth stack"))
        );

        stage.setScene(new Scene(vBox, 400, 600));
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    @Test
    public void should() {
        Assertions.assertThat(true).isTrue();
        Parent parent = fB.getParent().getParent();
        int current = parent.getChildrenUnmodifiable().size();
        clickOn(fB, MouseButton.PRIMARY);
        clickOn(fB, MouseButton.PRIMARY);
        clickOn(fB, MouseButton.PRIMARY);
        Assertions.assertThat(parent.getChildrenUnmodifiable().size()).isEqualTo(current+3);

    }
}

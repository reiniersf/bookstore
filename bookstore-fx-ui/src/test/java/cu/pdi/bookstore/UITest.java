package cu.pdi.bookstore;

import cu.pdi.bookstore.extension.BookstoreUIExtension;
import cu.pdi.bookstore.extension.annotation.AssumeUserAuthenticated;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testfx.assertions.api.Assertions.assertThat;


@ExtendWith(BookstoreUIExtension.class)
class UITest extends ApplicationTest {
    private Stage primaryStage;


    @Test
    @AssumeUserAuthenticated
    void should(){
        MenuButton menuButton = lookup("#btnGeneralMenu").query();
        assertThat(menuButton).hasAnyChild();

    }

    @Test
    void shouldStartWithAuthenticateButtonDisable() {
        Button authBtn = lookup("#btnAuthenticate").queryButton();
        assertThat(authBtn).isDisabled();
    }

    @Test
    void shouldCloseAppWhenCancelButtonIsClicked() {
        clickOn("#btnCancel");
        assertThat(listWindows()).hasSize(0);
    }


}

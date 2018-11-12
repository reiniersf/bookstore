package cu.pdi.bookstore.features.auth;

import cu.pdi.bookstore.extension.annotation.bdd.ApplicationFeature;
import cu.pdi.bookstore.extension.annotation.bdd.Scenario;
import cu.pdi.bookstore.extension.bdd.BDDExtension;
import cu.pdi.bookstore.extension.infrastructure.BookstoreUIExtension;
import javafx.scene.control.MenuButton;
import javafx.stage.Modality;
import javafx.stage.Window;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javafx.scene.input.KeyCode.ENTER;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith({BookstoreUIExtension.class, BDDExtension.class})
@ApplicationFeature("User log in to bookstore application")
class UserLoginApplicationTest extends ApplicationTest {

    @Scenario("User decide to cancel login")
    void shouldCloseAppWhenCancelButtonIsClickedByUser() {
        //GIVEN: User open application
        //WHEN
        clickOn("#btnCancel");
        //THEN
        assertThat(listWindows()).hasSize(0);
    }

    @Scenario("User login with right credentials")
    void shouldAccessAppWhenRightCredentialsAreProvidedByUser() {
        //GIVEN
        String rightUsername = "admin";
        String rightPassword = "admin1234";
        //WHEN
        tryLoginWithCredentials(rightUsername, rightPassword);
        //THEN
        assertInitialApplicationStatus();
        sleep(2, SECONDS);

    }

    @Scenario("User login with wrong credentials")
    void shouldShowAnAlertWhenWrongCredentialsAreProvidedByUser() {
        //GIVEN
        String wrongUsername = "admon";
        String wrongPassword = "admin123";
        //WHEN
        tryLoginWithCredentials(wrongUsername, wrongPassword);
        //THEN
        assertThat(listWindows()).hasSize(2);
        sleep(1, SECONDS);

        type(ENTER);
        sleep(2, SECONDS);

    }

    @Scenario("User exceed login retry allowed")
    void shouldRetryLoginUntilMaxRetryAllowedWhenUserProvidesWrongCredentials() {
        //GIVEN
        int allowedRetryLogin = 4;
        String wrongUsername = "admon";
        String wrongPassword = "admin123";

        //WHEN
        for (int retry = 0; retry < allowedRetryLogin; retry++) {
            tryLoginWithCredentials(wrongUsername, wrongPassword);
            if (retry < allowedRetryLogin - 1)
                type(ENTER).release(ENTER);

        }
        //THEN
        assertThatInformationDialogIsOpen();
        type(ENTER).release(ENTER);
    }

    @Scenario("User login with wrong credentials then with the right ones")
    void shouldAccessAppWhenUserBeforeReachMaxRetriesAllowedProvidesRightCredentials() {
        //GIVEN
        //Wrong credentials
        String wrongUsername = "admon";
        String wrongPassword = "admin123";

        //Right credentials
        String rightUsername = "admin";
        String rightPassword = "admin1234";

        tryLoginWithCredentials(wrongUsername, wrongPassword);
        type(ENTER);

        //WHEN
        tryLoginWithCredentials(rightUsername, rightPassword);
        sleep(1, SECONDS);
        //THEN
        assertInitialApplicationStatus();
    }

    private void assertInitialApplicationStatus() {
        MenuButton menuButton = lookup("#btnGeneralMenu").queryAs(MenuButton.class);
        assertThat(menuButton).hasText("");
        assertThat(menuButton).hasAnyChild();
        assertThat(menuButton).isEnabled();
        assertThat(lookup("#btnChangePassword").queryButton()).isEnabled();
    }

    private void tryLoginWithCredentials(String username, String password) {

        clickOn("#txUser").write(username);
        clickOn("#pfPassword").write(password);
        type(ENTER);
    }

    private void assertThatInformationDialogIsOpen() {
        List<Window> windows = new ArrayList<>(listWindows());
        //THEN
        assertThat(windows).hasSize(2);

        windows.stream()
                .filter(window -> ((javafx.stage.Stage) window).getModality() == Modality.APPLICATION_MODAL)
                .findFirst()
                .ifPresent(stage -> Assertions.assertAll(
                        () -> assertThat(stage.getScene().getRoot()).hasExactlyChildren(1, ".button"),
                        () -> assertThat(from(stage.getScene().getRoot()).lookup(".button").queryButton()).hasText("OK")));
    }

}

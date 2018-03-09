package cu.pdi.bookstore.fx.gui.main;

import cu.pdi.bookstore.fx.components.ui.ResourceLocator;
import cu.pdi.bookstore.security.context.JaasSecurityContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WorkspaceController implements Initializable {
    @FXML
    private MenuButton btnGeneralMenu;

    private final JaasSecurityContext jaasSecurityContext;
    private final ResourceLocator resourceLocator;

    @Autowired
    public WorkspaceController(JaasSecurityContext jaasSecurityContext, ResourceLocator resourceLocator) {
        this.jaasSecurityContext = jaasSecurityContext;
        this.resourceLocator = resourceLocator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGeneralMenu.setOnAction(
                event -> {
                    new Alert(Alert.AlertType.INFORMATION, "Inv Almacén").showAndWait();
                    System.out.println(jaasSecurityContext.getAuthenticatedUser());
                }

        );
        btnGeneralMenu.setText("");
        resourceLocator.urlForImage("menu_icon.png")
                .ifPresent(imageUrl -> btnGeneralMenu.setGraphic(new ImageView(new Image(imageUrl))));

    }
}

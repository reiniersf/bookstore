package cu.pdi.bookstore.fx.gui.main;

import cu.pdi.bookstore.security.context.JaasSecurityContext;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WorkspaceController implements Initializable {
    @FXML
    private Button btnInvAlmacen;
    @FXML
    private Button btnInvGeneral;

    @Autowired
    JaasSecurityContext jaasSecurityContext;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInvAlmacen.setOnAction(
                event -> {new Alert(Alert.AlertType.INFORMATION, "Inv Almacén").showAndWait();
                    System.out.println(jaasSecurityContext.getAuthenticatedUser());}
        );

        btnInvGeneral.setOnAction(
                event -> new Alert(Alert.AlertType.INFORMATION, "Inv General").showAndWait()
        );
    }
}

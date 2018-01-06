package cu.pdi.bookstore.fx.gui.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WorkspaceController implements Initializable {
    @FXML
    private Button btnInvAlmacen;
    @FXML
    private Button btnInvGeneral;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInvAlmacen.setOnAction(
                event -> new Alert(Alert.AlertType.INFORMATION, "Inv Almacén").showAndWait()
        );

        btnInvGeneral.setOnAction(
                event -> new Alert(Alert.AlertType.INFORMATION, "Inv General").showAndWait()
        );
    }
}

package cu.pdi.bookstore.fx.gui.inventory;

import cu.pdi.bookstore.fx.components.ui.ResourceLocator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class InventoryPagingController implements Initializable {
    private final ResourceLocator resourceLocator;
    @FXML
    private Button btnNextPage;
    @FXML
    private Button btnPrevPage;

    @Autowired
    public InventoryPagingController(ResourceLocator resourceLocator) {
        this.resourceLocator = resourceLocator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnNextPage.setText("");
        btnPrevPage.setText("");
        resourceLocator.urlForImage("prev-paging.png")
                .ifPresent(imageUrl -> btnPrevPage.setGraphic(new ImageView(new Image(imageUrl))));
        resourceLocator.urlForImage("next-paging.png")
                .ifPresent(imageUrl -> btnNextPage.setGraphic(new ImageView(new Image(imageUrl))));
    }
}

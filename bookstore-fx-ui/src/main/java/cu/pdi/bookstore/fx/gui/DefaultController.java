package cu.pdi.bookstore.fx.gui;

import cu.pdi.bookstore.fx.components.ui.ResourceLocator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class DefaultController implements Initializable {
    @FXML
    private Label lblDefault;
    @FXML
    private ImageView imageHolder;
    private final ResourceLocator resourceLocator;

    @Autowired
    public DefaultController(ResourceLocator resourceLocator) {
        this.resourceLocator = resourceLocator;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceLocator.urlForImage("book-background-mid.png")
                .ifPresent(imageUrl -> imageHolder.setImage(new Image(imageUrl)));
        lblDefault.setStyle("-fx-text-fill: #adadad; -fx-font-size: 24");
    }
}

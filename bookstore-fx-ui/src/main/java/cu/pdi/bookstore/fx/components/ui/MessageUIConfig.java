package cu.pdi.bookstore.fx.components.ui;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by R.S.F.
 */
public enum MessageUIConfig {

    WARNING("images/warningIcon.png", Alert.AlertType.WARNING),
    INFORMATION("images/infoIcon.png", Alert.AlertType.INFORMATION),
    ERROR("images/errorIcon.png", Alert.AlertType.ERROR),
    CONFIRM("images/questionIcon.png", Alert.AlertType.CONFIRMATION);

    private final String imageUrl;
    private Alert.AlertType alertType;

    MessageUIConfig(String imageUrl, Alert.AlertType alertType){

        this.imageUrl = imageUrl;
        this.alertType = alertType;
    }

    public ImageView getIcon() {
        return new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(imageUrl)));

    }

    public Alert.AlertType toAlertType(){
        return this.alertType;
    }
}

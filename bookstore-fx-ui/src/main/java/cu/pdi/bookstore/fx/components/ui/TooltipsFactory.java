package cu.pdi.bookstore.fx.components.ui;

import cu.pdi.bookstore.fx.enums.ENUM_MSG_CONFIG;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.springframework.stereotype.Component;

/**
 * Created by R.S.F.
 */
@Component
public class TooltipsFactory {

    private final Tooltip ttp;

    public TooltipsFactory() {
        ttp = new Tooltip();
        ttp.centerOnScreen();
        ttp.setAutoHide(true);
        ttp.setFont(new Font(14d));
        ttp.setTextAlignment(TextAlignment.CENTER);
    }

    public Tooltip showTooltip(String msgText) {

        return showTooltip(msgText, ENUM_MSG_CONFIG.INFORMATION);
    }

    public Tooltip showTooltip(String msgText, ENUM_MSG_CONFIG enumMsgIcon) {
        ttp.setGraphic(enumMsgIcon.getIcon());

        String msgCierreTooltip = "\nPresione ESC para cerrar este mensaje.";
        ttp.setText(msgText.concat(msgCierreTooltip));

        //TODO pass main window reference
        ttp.show(null);

        return ttp;
    }

}

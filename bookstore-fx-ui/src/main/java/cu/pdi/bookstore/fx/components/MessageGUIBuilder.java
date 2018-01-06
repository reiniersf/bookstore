/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.components;

import cu.pdi.bookstore.fx.enums.ENUM_MSG_CONFIG;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


/**
 * @author R.S.F.
 */
public class MessageGUIBuilder {


    private Alert messageBody;
    private MessageAction acceptMessageAction;
    private MessageAction denyMessageAction;

    private MessageGUIBuilder(String msgText, ENUM_MSG_CONFIG config) {
        messageBody = new Alert(config.toAlertType(), msgText);
        acceptMessageAction = () -> {
        };
        denyMessageAction = () -> {
        };

    }

    public static MessageGUIBuilder createMessage(String msgText, ENUM_MSG_CONFIG config) {
        return new MessageGUIBuilder(msgText, config);
    }

    public MessageGUIBuilder onAccept(MessageAction messageAction) {
        this.acceptMessageAction = messageAction;
        return this;
    }

    public MessageGUIBuilder onDeny(MessageAction messageAction) {
        this.denyMessageAction = messageAction;
        return this;
    }

    public void show() {
        messageBody.setResizable(false);
        Optional<ButtonType> buttonTypeResult = messageBody.showAndWait();
        if (answerWasAccept(buttonTypeResult.get())) {
            this.acceptMessageAction.execute();
        } else {
            this.denyMessageAction.execute();
        }
    }

    private boolean answerWasAccept(ButtonType buttonType) {
        return ButtonType.APPLY.equals(buttonType)
                || ButtonType.OK.equals(buttonType)
                || ButtonType.YES.equals(buttonType);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.components.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

/**
 *
 * @author developer
 */
@Component
public class InputUIValidator {

    private final DropShadow effectNotValidSelection1;
    private final InnerShadow effectNotValidSelection;
    private static InputUIValidator instance;

    private InputUIValidator() {
        effectNotValidSelection = new InnerShadow();
        effectNotValidSelection.setChoke(0);
        effectNotValidSelection.setWidth(16.0);
        effectNotValidSelection.setHeight(16.0);
        effectNotValidSelection.setRadius(10.0);
        effectNotValidSelection.setColor(Color.RED);

        effectNotValidSelection1 = new DropShadow();
        effectNotValidSelection1.setWidth(16.0);
        effectNotValidSelection1.setHeight(16.0);
        effectNotValidSelection1.setRadius(7.0);
        effectNotValidSelection1.setColor(Color.RED);
        effectNotValidSelection1.setInput(effectNotValidSelection);

    }

    public ENUM_INPUT_ERROR_TYPE validateTextField(TextField tf, String pattern) {
        ENUM_INPUT_ERROR_TYPE errorType = ENUM_INPUT_ERROR_TYPE.OK;
        if (tf.getText().isEmpty()) {
            errorType = ENUM_INPUT_ERROR_TYPE.EMPTY;
        } else if (!tf.getText().trim().matches(pattern)) {
            errorType = ENUM_INPUT_ERROR_TYPE.NOT_VALID;
        }
        if (!errorType.equals(ENUM_INPUT_ERROR_TYPE.OK)) {
            tf.setEffect(effectNotValidSelection1);
            tf.setTooltip(new Tooltip("Entrada no válida"));
        }
        resetControlStatus(tf);
        return errorType;
    }

    @SafeVarargs
    public final ENUM_INPUT_ERROR_TYPE validateTextField(TextField tf, Predicate<TextField>... condition) {
        ENUM_INPUT_ERROR_TYPE errorType = ENUM_INPUT_ERROR_TYPE.OK;
        if (tf.getText().isEmpty()) {
            errorType = ENUM_INPUT_ERROR_TYPE.EMPTY;
        } else if (condition.length > 0) {
            for (Predicate<TextField> predicate : condition) {
                if (!predicate.test(tf)) {
                    errorType = ENUM_INPUT_ERROR_TYPE.NOT_VALID;
                    break;
                }
            }

        }
        if (!errorType.equals(ENUM_INPUT_ERROR_TYPE.OK)) {
            tf.setEffect(effectNotValidSelection1);
            tf.setTooltip(new Tooltip("Entrada no válida"));
        }
        resetControlStatus(tf);
        return errorType;
    }

    public ENUM_INPUT_ERROR_TYPE validateComboBox(ComboBox cbx) {
        ENUM_INPUT_ERROR_TYPE errorType = ENUM_INPUT_ERROR_TYPE.OK;
        if (cbx.getSelectionModel().isSelected(-1)) {
            errorType = ENUM_INPUT_ERROR_TYPE.NOT_VALID;
        }
        if (!errorType.equals(ENUM_INPUT_ERROR_TYPE.OK)) {
            cbx.setEffect(effectNotValidSelection1);
            cbx.setTooltip(new Tooltip("Selección no válida"));
        }
        resetControlStatus(cbx);
        return errorType;
    }

    @SafeVarargs
    public final ENUM_INPUT_ERROR_TYPE validatePasswordField(PasswordField pwdF, Predicate<PasswordField>... condition) {
        ENUM_INPUT_ERROR_TYPE errorType = ENUM_INPUT_ERROR_TYPE.OK;
        for (Predicate<PasswordField> predicate : condition) {
            if (predicate.test(pwdF)) {
                errorType = ENUM_INPUT_ERROR_TYPE.NOT_VALID;
                break;
            }
        }
        if (!errorType.equals(ENUM_INPUT_ERROR_TYPE.OK)) {
            pwdF.setEffect(effectNotValidSelection1);
            pwdF.setTooltip(new Tooltip("Entrada no válida"));
        }
        resetControlStatus(pwdF);
        return errorType;
    }

    private void resetControlStatus(Control c) {
        c.setOnMouseClicked(t -> {
            c.setEffect(null);
            c.setTooltip(null);
        });

        ChangeListener<Boolean> removeControlTooltip = (ov, t, t1) -> {
            c.setEffect(null);
            c.setTooltip(null);

        };

        c.focusedProperty().addListener(removeControlTooltip);
    }

    public enum ENUM_INPUT_ERROR_TYPE {

        OK, EMPTY, NOT_VALID
    }
}

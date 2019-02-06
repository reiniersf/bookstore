package cu.pdi.bookstore.fx.components.security.events;

import cu.pdi.bookstore.fx.components.ui.MessageUIConfig;
import lombok.Getter;

@Getter
public class FailedLoginEvent {
    private final String failureMessage;
    private final MessageUIConfig messageUIConfig;

    private FailedLoginEvent(String failureMessage, MessageUIConfig messageUIConfig) {

        this.failureMessage = failureMessage;
        this.messageUIConfig = messageUIConfig;
    }

    public static FailedLoginEvent with(String failureMessage, MessageUIConfig messageUIConfig) {
        return new FailedLoginEvent(failureMessage, messageUIConfig);
    }
}

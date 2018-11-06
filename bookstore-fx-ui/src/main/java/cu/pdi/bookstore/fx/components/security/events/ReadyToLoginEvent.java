package cu.pdi.bookstore.fx.components.security.events;

import java.util.List;

public class ReadyToLoginEvent {
    private final List<String> credentials;

    private ReadyToLoginEvent(List<String> credentials) {

        this.credentials = credentials;
    }

    public static  ReadyToLoginEvent withCredentials(List<String> credentials ) {
        return new ReadyToLoginEvent(credentials);
    }

    public List<String> getCredentials() {
        return credentials;
    }
}

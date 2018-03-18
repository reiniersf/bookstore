package cu.pdi.bookstore.fx.components.ui;

import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class I18nHandler {

    private ResourceBundle currentResourceBundle;

    public I18nHandler() {
        currentResourceBundle = ResourceBundle.getBundle("labels");
    }

    public ResourceBundle activeBundle() {
        return currentResourceBundle;
    }

    public String labelForKey(String key) {
        return currentResourceBundle.getString(key);
    }
}

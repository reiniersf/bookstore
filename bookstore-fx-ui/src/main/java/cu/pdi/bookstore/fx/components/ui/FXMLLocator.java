package cu.pdi.bookstore.fx.components.ui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author R.S.F.
 */
@Component
public class FXMLLocator implements ApplicationContextAware {
    private final String COMMON_PATH = "fxml/";
    private ApplicationContext applicationContext;
    private I18nHandler i18nHandler;

    public FXMLLocator(I18nHandler i18nHandler) {
        this.i18nHandler = i18nHandler;
    }

    public Parent getFXML(String fxmlPath) {
        try {
            return FXMLLoader.load(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource(COMMON_PATH.concat(fxmlPath))),
                    i18nHandler.activeBundle(),
                    new JavaFXBuilderFactory(),
                    (Class<?> param) -> applicationContext.getBean(param),
                    Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

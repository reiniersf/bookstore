/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.pdi.bookstore.fx.components.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author R.S.F.
 */
@Component
public class FXMLLocator implements ApplicationContextAware {
    private final String COMMON_PATH = "fxml/";
    private ApplicationContext applicationContext;

    public Parent getFXML(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory((Class<?> param) -> applicationContext.getBean(param));
        try {
            return loader.load(getClass().getClassLoader().getResourceAsStream(COMMON_PATH.concat(fxmlPath)));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

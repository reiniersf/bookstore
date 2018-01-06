package cu.pdi.bookstore.fx.application.config;


import cu.pdi.bookstore.application.config.AppConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(AppConfig.class)
@ComponentScan(basePackages = {"cu.pdi.bookstore.fx.components", "cu.pdi.bookstore.fx.gui"})
public class FxAppConfig {
}

package cu.pdi.bookstore.fx.application.config;


import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.security.application.config.BeansConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({AppConfig.class, BeansConfig.class})
@ComponentScan(basePackages = {"cu.pdi.bookstore.fx.components", "cu.pdi.bookstore.fx.gui"})
public class FxAppConfig {
}

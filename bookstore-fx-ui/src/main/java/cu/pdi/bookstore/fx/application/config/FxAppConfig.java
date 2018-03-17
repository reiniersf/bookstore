package cu.pdi.bookstore.fx.application.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.security.application.config.BeansConfig;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@Import({AppConfig.class, BeansConfig.class})
@ComponentScan(basePackages = {"cu.pdi.bookstore.fx.components", "cu.pdi.bookstore.fx.gui"})
public class FxAppConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

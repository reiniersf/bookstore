package cu.pdi.bookstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by taiyou
 * on 8/31/17.
 */
@Configuration
@ComponentScan(basePackages = {"cu.pdi.bookstore.domain", "cu.pdi.bookstore.infrastructure"})
@Import(AppPersistenceConfig.class)
public class AppConfig {
}

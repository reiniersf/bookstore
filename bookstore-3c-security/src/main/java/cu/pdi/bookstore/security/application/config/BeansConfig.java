package cu.pdi.bookstore.security.application.config;

import cu.pdi.bookstore.security.context.JaasSecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by R.S.F.
 */
@Configuration
@ComponentScan(basePackageClasses = {JaasSecurityContext.class})
public class BeansConfig {

    @Bean
    @Profile("prod")
    public DataSource dataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        String folder = System.getProperty("user.dir");
        dataSource.setUrl("jdbc:derby:" + folder + File.separator + "bookstore;create=true");
        dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        dataSource.setUsername("bookstore");
        dataSource.setPassword("bookstorepass");

        return dataSource;

    }
}

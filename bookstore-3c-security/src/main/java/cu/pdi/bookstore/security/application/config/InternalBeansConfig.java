package cu.pdi.bookstore.security.application.config;

import cu.pdi.bookstore.security.jdbc.JaasSecurityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;
import java.io.File;

/**
 * Created by R.S.F.
 */
@Configuration
@ComponentScan(basePackageClasses = {JaasSecurityRepository.class})
public class InternalBeansConfig {
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}

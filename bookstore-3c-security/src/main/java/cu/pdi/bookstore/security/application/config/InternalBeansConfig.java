package cu.pdi.bookstore.security.application.config;

import cu.pdi.bookstore.security.jdbc.JaasSecurityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

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

    @Bean
    @Profile("dev")
    public DataSource dataSourceInternalSecurity() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("org.postgresql.Driver");
        datasource.setPassword("sqlpq");
        datasource.setUrl("jdbc:postgresql://localhost:5432/bookstore");
        datasource.setUsername("postgres");
        return datasource;
    }

    @Bean
    @Profile("test")
    public DataSource dataSourceTest() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

}

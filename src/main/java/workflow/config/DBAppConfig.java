package workflow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("workflow")
@PropertySource("classpath:database.properties")
public class DBAppConfig {

    @Autowired
    Environment environment;


    private final String URL = "jdbc:postgresql://localhost:5432/camundaBD";
    private final String USER = "admin";
    private final String DRIVER = "org.postgresql.Driver";
    private final String PASSWORD = "admin";


    @Bean
     DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        //driverManagerDataSource.setUrl(environment.getProperty(URL));
        //driverManagerDataSource.setUsername(environment.getProperty(USER));
        //driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        //driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));

        driverManagerDataSource.setUrl(URL);
        driverManagerDataSource.setUsername(USER);
        driverManagerDataSource.setPassword(PASSWORD);
        driverManagerDataSource.setDriverClassName(DRIVER);

        return driverManagerDataSource;
    }
}

package hello.hellospring;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:tcp://localhost/~/test")
                .username("sa");
        return dataSourceBuilder.build();
    }
}
*/

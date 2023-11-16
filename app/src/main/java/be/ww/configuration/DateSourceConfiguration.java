package be.ww.configuration;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DateSourceConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource datasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @LiquibaseDataSource
    @ConfigurationProperties("spring.init-datasource")
    public DataSource datasourceInit() {
        return DataSourceBuilder.create().build();
    }

}

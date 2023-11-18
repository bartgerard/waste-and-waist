package be.ww.household.query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(
        basePackages = "be.ww.household.query.projection"
)
@EnableElasticsearchRepositories(
        basePackages = "be.ww.household.query.repository"
)
public class HouseHoldQueryConfiguration {
}

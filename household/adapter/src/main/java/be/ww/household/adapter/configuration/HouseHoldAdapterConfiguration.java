package be.ww.household.adapter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "be.ww.household.adapter.repository"
)
public class HouseHoldAdapterConfiguration {
}

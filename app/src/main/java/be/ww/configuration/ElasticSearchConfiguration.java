package be.ww.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = {
                "be.ww.configuration.test"
        },
        considerNestedRepositories = true
)
public class ElasticSearchConfiguration {
}

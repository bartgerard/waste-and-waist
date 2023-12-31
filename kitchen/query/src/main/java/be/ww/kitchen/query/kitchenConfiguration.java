package be.ww.kitchen.query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(
		basePackages = "be.ww.kitchen.query.projection"
)
@EnableElasticsearchRepositories(
		basePackages = "be.ww.kitchen.query.repository"
)
public class kitchenConfiguration {
}

package be.ww.stock.query.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(
		basePackages = "be.ww.stock.query.projection"
)
@EnableElasticsearchRepositories(
		basePackages = "be.ww.stock.query.repository"
)
public class StockQueryConfiguration {
}

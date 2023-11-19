package be.ww.store.query;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan(basePackages = {
		"be.ww.store.query"
})
@EnableElasticsearchRepositories(basePackages = {
		"be.ww.store.query.repository"
})
public class StoreQueryConfiguration {
}

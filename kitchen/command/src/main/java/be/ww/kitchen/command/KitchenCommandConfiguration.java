package be.ww.kitchen.command;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan({
		"be.ww.kitchen.command"
})
@EnableElasticsearchRepositories(basePackages = {
		"be.ww.kitchen.command.repository"
})
public class KitchenCommandConfiguration {
}

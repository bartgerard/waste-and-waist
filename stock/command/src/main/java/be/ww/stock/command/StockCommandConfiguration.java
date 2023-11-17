package be.ww.stock.command;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan({
        "be.ww.stock.command"
})
@EnableElasticsearchRepositories(basePackages = {
        "be.ww.stock.command.repository"
})
public class StockCommandConfiguration {
}

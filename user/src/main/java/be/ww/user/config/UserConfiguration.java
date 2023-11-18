package be.ww.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@ComponentScan({
        "be.ww.user.service"
})
@EnableElasticsearchRepositories(basePackages = {
        "be.ww.user.repository"
})
public class UserConfiguration {
}

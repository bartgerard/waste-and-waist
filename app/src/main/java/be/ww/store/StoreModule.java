package be.ww.store;

import be.ww.store.command.StoreCommandConfiguration;
import be.ww.store.query.StoreQueryConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        StoreCommandConfiguration.class,
        StoreQueryConfiguration.class
})
@ComponentScan({
        "be.ww.store.command.web",
        "be.ww.store.query.web"
})
public class StoreModule {
}

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
        "be.ww.household.command.web",
        "be.ww.household.query.web"
})
public class StoreModule {
}

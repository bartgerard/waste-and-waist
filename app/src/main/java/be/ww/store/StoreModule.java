package be.ww.store;

import be.ww.store.command.StoreCommandConfiguration;
import be.ww.store.query.StoreQueryConfiguration;
import be.ww.store.web.ProductRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ProductRestController.class,
        StoreCommandConfiguration.class,
        StoreQueryConfiguration.class
})
public class StoreModule {
}

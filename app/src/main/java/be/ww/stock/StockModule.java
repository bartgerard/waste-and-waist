package be.ww.stock;

import be.ww.store.command.StoreCommandConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        StoreCommandConfiguration.class
})
@ComponentScan({
        "be.ww.stock.command.web",
        "be.ww.stock.query.web"
})
public class StockModule {
}

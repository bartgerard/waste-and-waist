package be.ww.stock;

import be.ww.stock.command.StockCommandConfiguration;
import be.ww.stock.query.StockQueryConfiguration;
import be.ww.stock.web.LocationRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        LocationRestController.class,
        StockCommandConfiguration.class,
        StockQueryConfiguration.class
})
public class StockModule {
}

package be.ww.stock;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import be.ww.stock.command.StockCommandConfiguration;
import be.ww.stock.query.configuration.StockQueryConfiguration;

@Configuration
@Import({
		StockCommandConfiguration.class,
		StockQueryConfiguration.class
})
@ComponentScan({
        "be.ww.stock.command.web",
        "be.ww.stock.query.web"
})
public class StockModule {
}

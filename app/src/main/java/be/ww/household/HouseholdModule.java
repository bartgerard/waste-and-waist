package be.ww.household;

import be.ww.household.adapter.configuration.HouseHoldAdapterConfiguration;
import be.ww.household.command.configuration.HouseHoldCommandConfiguration;
import be.ww.household.query.configuration.HouseHoldQueryConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HouseHoldAdapterConfiguration.class,
        HouseHoldCommandConfiguration.class,
        HouseHoldQueryConfiguration.class
})
@ComponentScan({
        "be.ww.household.command.web",
        "be.ww.household.query.web"
})
public class HouseholdModule {
}

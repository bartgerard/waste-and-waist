package be.ww.household;

import be.ww.household.adapter.configuration.HouseHoldAdapterConfiguration;
import be.ww.household.command.HouseHoldCommandConfiguration;
import be.ww.household.query.HouseHoldQueryConfiguration;
import be.ww.household.web.HouseHoldSocketController;
import be.ww.household.web.HouseHoldRestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HouseHoldRestController.class,
        HouseHoldSocketController.class,
        HouseHoldAdapterConfiguration.class,
        HouseHoldCommandConfiguration.class,
        HouseHoldQueryConfiguration.class
})
public class HouseholdModule {
}

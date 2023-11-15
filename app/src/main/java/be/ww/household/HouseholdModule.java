package be.ww.household;

import be.ww.household.adapter.configuration.HouseHoldAdapterConfiguration;
import be.ww.household.query.configuration.HouseHoldQueryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        HouseHoldAdapterConfiguration.class,
        HouseHoldQueryConfiguration.class
})
public class HouseholdModule {
}

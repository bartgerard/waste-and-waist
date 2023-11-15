package be.ww.household.query.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "be.ww.household.query.projection"
)
public class HouseHoldQueryConfiguration {
}

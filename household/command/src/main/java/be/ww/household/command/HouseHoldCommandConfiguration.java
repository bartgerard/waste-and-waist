package be.ww.household.command;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = "be.ww.household.command.domain"
)
public class HouseHoldCommandConfiguration {
}

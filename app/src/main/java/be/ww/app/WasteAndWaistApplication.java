package be.ww.app;

import be.ww.configuration.AxonConfiguration;
import be.ww.configuration.DateSourceConfiguration;
import be.ww.configuration.ElasticSearchConfiguration;
import be.ww.household.HouseholdModule;
import be.ww.kitchen.KitchenModule;
import be.ww.notification.NotificationModule;
import be.ww.planner.PlannerModule;
import be.ww.stock.StockModule;
import be.ww.store.StoreModule;
import be.ww.user.UserModule;
import org.axonframework.springboot.autoconfig.JpaEventStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {
        JpaEventStoreAutoConfiguration.class
})
@Import({
        AxonConfiguration.class,
        DateSourceConfiguration.class,
        ElasticSearchConfiguration.class,
        HouseholdModule.class,
        StockModule.class,
        StoreModule.class,
        PlannerModule.class,
        KitchenModule.class,
        NotificationModule.class,
        UserModule.class
})
public class WasteAndWaistApplication {
    public static void main(String[] args) {
        SpringApplication.run(WasteAndWaistApplication.class, args);
    }
}

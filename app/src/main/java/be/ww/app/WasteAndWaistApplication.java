package be.ww.app;

import be.ww.household.HouseholdModule;
import be.ww.kitchen.KitchenModule;
import be.ww.notification.NotificationModule;
import be.ww.planner.PlannerModule;
import be.ww.stock.StockModule;
import be.ww.store.StoreModule;
import be.ww.user.UserModule;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WasteAndWaistApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .parent(WasteAndWaistApplication.class)
                .child(UserModule.class)
                .sibling(HouseholdModule.class)
                .sibling(StockModule.class)
                .sibling(StoreModule.class)
                .sibling(PlannerModule.class)
                .sibling(KitchenModule.class)
                .sibling(NotificationModule.class)
                .run(args);
    }
}

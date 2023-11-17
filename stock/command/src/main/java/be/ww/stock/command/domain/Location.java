package be.ww.stock.command.domain;

import be.ww.stock.api.command.AddLocation;
import be.ww.stock.api.event.LocationAdded;
import be.ww.stock.api.type.LocationId;
import be.ww.stock.command.repository.StockHouseHoldRepository;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Location {
    @AggregateIdentifier
    private LocationId locationId;

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(
            final AddLocation command,
            final StockHouseHoldRepository stockHouseHoldRepository
    ) {
        stockHouseHoldRepository.findByHouseHoldIdIs(command.houseHoldId().id())
                .orElseThrow(() -> new IllegalArgumentException("houseHoldId does not exist"));

        apply(new LocationAdded(
                command.locationId(),
                command.houseHoldId(),
                command.name()
        ));
    }

    @EventHandler
    public void on(
            final LocationAdded event
    ) {
        this.locationId = event.locationId();
    }
}

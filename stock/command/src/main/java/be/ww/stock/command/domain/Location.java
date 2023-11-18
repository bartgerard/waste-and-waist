package be.ww.stock.command.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.command.AddAppliancesCommand;
import be.ww.stock.api.command.AddLocationCommand;
import be.ww.stock.api.event.AppliancesAddedEvent;
import be.ww.stock.api.event.LocationAddedEvent;
import be.ww.stock.api.type.Appliance;
import be.ww.stock.command.repository.StockHouseHoldRepository;
import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class Location {
    @AggregateIdentifier
    private LocationId locationId;
    private final Set<Appliance> appliances = new HashSet<>();

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(
            final AddLocationCommand command,
            final StockHouseHoldRepository stockHouseHoldRepository
    ) {

        boolean houseHoldExists = stockHouseHoldRepository.findByHouseHoldIdIs(command.houseHoldId().id()).isPresent();
        Validate.isTrue(houseHoldExists);
        apply(new LocationAddedEvent(
                command.locationId(),
                command.houseHoldId(),
                command.name()
        ));
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(final AddAppliancesCommand command) {
        Validate.isTrue(Collections.disjoint(command.appliances(), this.appliances));
        apply(new AppliancesAddedEvent(
                command.locationId(),
                command.appliances()
        ));
    }

    @EventHandler
    public void on(
            final LocationAddedEvent event
    ) {
        this.locationId = event.locationId();
    }

    @EventSourcingHandler
    public void on(final AppliancesAddedEvent event) {
        this.appliances.addAll(event.appliances());
    }
}

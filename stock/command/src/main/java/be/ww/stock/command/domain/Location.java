package be.ww.stock.command.domain;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

import java.time.LocalDate;
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
import be.ww.shared.type.ingredient.Quantity;
import be.ww.stock.api.command.AddAppliancesCommand;
import be.ww.stock.api.command.AddLocationCommand;
import be.ww.stock.api.command.AddStorageFacilitiesCommand;
import be.ww.stock.api.command.ConsumeProvisionsCommand;
import be.ww.stock.api.command.DisposeProvisionsCommand;
import be.ww.stock.api.command.RemoveAppliancesCommand;
import be.ww.stock.api.command.RemoveLocationCommand;
import be.ww.stock.api.command.StoreProvisionsCommand;
import be.ww.stock.api.event.AppliancesAddedEvent;
import be.ww.stock.api.event.AppliancesRemovedEvent;
import be.ww.stock.api.event.LocationAddedEvent;
import be.ww.stock.api.event.LocationRemovedEvent;
import be.ww.stock.api.event.ProvisionDisposeEvent;
import be.ww.stock.api.event.ProvisionsConsumedEvent;
import be.ww.stock.api.event.ProvisionsStoredEvent;
import be.ww.stock.api.event.StorageFacilitiesAddedEvent;
import be.ww.stock.api.type.Appliance;
import be.ww.stock.api.type.StorageFacility;
import be.ww.stock.command.repository.StockHouseHoldRepository;
import be.ww.stock.command.repository.StockProductRepository;
import lombok.NoArgsConstructor;

@Aggregate
@NoArgsConstructor
public class Location {
	private final Set<Appliance> appliances = new HashSet<>();
	private final Set<StorageFacility> storageFacilities = new HashSet<>();
	@AggregateIdentifier
	private LocationId locationId;
	private Quantity quantity;

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

	@CommandHandler
	@CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
	public void handle(final AddStorageFacilitiesCommand command) {
		Validate.isTrue(Collections.disjoint(command.storageFacilities(), this.storageFacilities));
		apply(new StorageFacilitiesAddedEvent(
				command.locationId(),
				command.storageFacilities()
		));
	}

	@CommandHandler
	public void handle(final ConsumeProvisionsCommand command) {
		Validate.isTrue(this.quantity.amount().value().compareTo(command.quantity().amount().value()) > 0);
		apply(new ProvisionsConsumedEvent(
				command.locationId(),
				command.productId(),
				command.provisionId(),
				command.quantity()
		));
	}

	@CommandHandler
	public void handle(final DisposeProvisionsCommand command, StockProductRepository stockProductRepository) {
		Validate.isTrue(stockProductRepository.findByProvisionId(command.provisionId().id()).isPresent());
		apply(new ProvisionDisposeEvent(
				command.locationId(),
				command.productId(),
				command.provisionId()
		));
	}

	@CommandHandler
	public void handle(final RemoveAppliancesCommand command) {
		Validate.isTrue(Collections.disjoint(command.appliances(), this.appliances));
		apply(new AppliancesRemovedEvent(
				command.locationId(),
				command.appliances()
		));
	}

	@CommandHandler
	public void handle(final RemoveLocationCommand command) {
		apply(new LocationRemovedEvent(
				command.locationId()
		));
	}

	@CommandHandler
	@CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
	public void handle(final StoreProvisionsCommand command) {
		Validate.isTrue(command.useByDay().day().isAfter(LocalDate.now()));
		apply(new ProvisionsStoredEvent(
				command.locationId(),
				command.productId(),
				command.provisionId(),
				command.ingredientId(),
				command.quantity(),
				command.bestBeforeDay(),
				command.useByDay()
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

	@EventSourcingHandler
	public void on(final StorageFacilitiesAddedEvent event) {
		this.storageFacilities.addAll(event.storageFacilities());
	}

	@EventSourcingHandler
	public void on(final ProvisionsConsumedEvent event) throws IllegalAccessException {
		this.quantity.reduce(event.quantity());
	}

	@EventSourcingHandler
	public void on(final ProvisionDisposeEvent event) {
		markDeleted();
	}

	@EventSourcingHandler
	public void on(final AppliancesRemovedEvent event) {
		markDeleted();
	}

	@EventSourcingHandler
	public void on(final LocationRemovedEvent event) {
		markDeleted();
	}

	@EventSourcingHandler
	public void on(final ProvisionsStoredEvent event) {
		this.locationId = event.locationId();
		this.quantity = event.quantity();

	}
}

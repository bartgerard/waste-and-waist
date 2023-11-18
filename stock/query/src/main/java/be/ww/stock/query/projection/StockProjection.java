package be.ww.stock.query.projection;

import java.util.Objects;
import java.util.stream.Collectors;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.event.AppliancesAddedEvent;
import be.ww.stock.api.event.LocationAddedEvent;
import be.ww.stock.api.event.LocationRemovedEvent;
import be.ww.stock.api.event.StorageFacilitiesAddedEvent;
import be.ww.stock.api.query.FindLocationByIdQuery;
import be.ww.stock.api.query.LocationResponseData;
import be.ww.stock.query.repository.ApplianceField;
import be.ww.stock.query.repository.LocationDocument;
import be.ww.stock.query.repository.LocationRepository;
import be.ww.stock.query.repository.StorageFacilityField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
@ProcessingGroup("ww-stock-query-StockProjection")
public class StockProjection {
	private final LocationRepository locationRepository;
	private final QueryUpdateEmitter queryUpdateEmitter;


	@EventHandler
	public void on(
			final LocationAddedEvent event
	) {
		log.info("Handle locationAdded [{}]", event);
		if (locationRepository.findByLocationId(event.locationId().id()).isEmpty()) {
			locationRepository.save(LocationDocument.builder()
					.locationId(event.locationId().id())
					.build());
		}

		emitUpdateFoLocationId(event.locationId());
	}

	@EventHandler
	public void on(
			final AppliancesAddedEvent event
	) {
		log.info("Handle appliancesAdded [{}]", event);
		locationRepository.findByLocationId(event.locationId().id())
				.map(locationDocument -> locationDocument.toBuilder()
						.locationId(event.locationId().id())
						.appliances(event.appliances().stream()
								.map(appliance -> new ApplianceField(appliance.type())
								).toList()
						)
						.build()
				)
				.ifPresent(locationRepository::save);

		emitUpdateFoLocationId(event.locationId());
	}

	@EventHandler
	public void on(
			final LocationRemovedEvent event
	) {
		log.info("Handle locationRemoved [{}]", event);
		locationRepository.findByLocationId(event.locationId().id())
				.ifPresent(locationRepository::delete);
		emitUpdateFoLocationId(event.locationId());
	}

	@EventHandler
	public void on(
			final StorageFacilitiesAddedEvent event
	) {
		log.info("Handle StorageFacilities added [{}]", event);
		locationRepository.findByLocationId(event.locationId().id())
				.map(locationDocument -> locationDocument.toBuilder()
						.locationId(event.locationId().id())
						.storageFacilities(event.storageFacilities().stream()
								.map(sf -> new StorageFacilityField(sf.storageType())
								).toList()
						)
						.build()
				)
				.ifPresent(locationRepository::save);

	}

	@QueryHandler
	public LocationResponseData handle(
			final FindLocationByIdQuery query
	) {
		return locationRepository.findByLocationId(query.locationId().id())
				.map(location -> LocationResponseData.builder()
						.locationId(LocationId.of(location.getLocationId()))
						.appliances(location.getAppliances().stream()
								.map(applianceField -> new LocationResponseData.Appliance(applianceField.type()))
								.collect(Collectors.toUnmodifiableSet())
						)
						.build()
				)
				.orElseThrow(() -> new IllegalArgumentException("no appliances found on this location"));
	}

	private void emitUpdateFoLocationId(
			final LocationId locationId
	) {
		queryUpdateEmitter.emit(
				FindLocationByIdQuery.class,
				query -> Objects.equals(query.locationId(), locationId),
				handle(new FindLocationByIdQuery(locationId))
		);
	}
}
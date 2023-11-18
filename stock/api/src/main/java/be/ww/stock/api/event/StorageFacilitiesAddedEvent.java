package be.ww.stock.api.event;

import java.util.Set;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.type.StorageFacility;

public record StorageFacilitiesAddedEvent(
		LocationId locationId,
		Set<StorageFacility> storageFacilities
) {
}

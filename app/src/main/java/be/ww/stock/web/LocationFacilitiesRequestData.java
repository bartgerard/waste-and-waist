package be.ww.stock.web;

import java.util.Set;

import be.ww.stock.api.type.StorageFacility;

public record LocationFacilitiesRequestData(
		String locationId,
		Set<StorageFacility> storageFacilities
) {
}

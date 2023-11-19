package be.ww.stock.api.event;

import java.util.Set;

import be.ww.shared.type.LocationId;

public record AppliancesAddedEvent(
		LocationId locationId,
		Set<String> appliances
) {
}

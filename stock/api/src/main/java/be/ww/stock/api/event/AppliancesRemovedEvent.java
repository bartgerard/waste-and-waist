package be.ww.stock.api.event;

import java.util.Set;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.type.Appliance;

public record AppliancesRemovedEvent(
		LocationId locationId,
		Set<Appliance> appliances
) {

}

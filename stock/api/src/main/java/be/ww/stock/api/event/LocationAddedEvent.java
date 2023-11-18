package be.ww.stock.api.event;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.LocationId;

public record LocationAddedEvent(
        LocationId locationId,
        HouseHoldId houseHoldId,
        String name
) {
}

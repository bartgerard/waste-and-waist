package be.ww.stock.api.event;

import be.ww.stock.api.type.HouseHoldId;
import be.ww.stock.api.type.LocationId;

public record LocationAdded(
        LocationId locationId,
        HouseHoldId houseHoldId,
        String name
) {
}

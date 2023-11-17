package be.ww.stock.api.command;

import be.ww.stock.api.type.HouseHoldId;
import be.ww.stock.api.type.LocationId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddLocation(
        @TargetAggregateIdentifier
        LocationId locationId,
        HouseHoldId houseHoldId,
        String name
) {
}

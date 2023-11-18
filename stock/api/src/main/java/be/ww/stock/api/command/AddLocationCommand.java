package be.ww.stock.api.command;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.LocationId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddLocationCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        HouseHoldId houseHoldId,
        String name
) {
}

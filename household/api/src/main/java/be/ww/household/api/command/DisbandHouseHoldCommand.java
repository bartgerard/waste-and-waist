package be.ww.household.api.command;

import be.ww.shared.type.HouseHoldId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DisbandHouseHoldCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId
) {
}

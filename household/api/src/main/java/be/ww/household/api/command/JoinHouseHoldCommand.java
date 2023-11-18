package be.ww.household.api.command;

import be.ww.shared.type.HouseHoldId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record JoinHouseHoldCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId,
        String userId
) {
}

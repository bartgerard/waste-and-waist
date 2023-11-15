package be.ww.household.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record StartHouseHoldCommand(
        @TargetAggregateIdentifier
        String houseHoldId,
        String houseHoldName,
        String userId,
        String memberName
) {
}

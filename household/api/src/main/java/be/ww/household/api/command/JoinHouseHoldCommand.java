package be.ww.household.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record JoinHouseHoldCommand(
        @TargetAggregateIdentifier
        String houseHoldId,
        String userId
) {
}

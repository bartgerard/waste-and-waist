package be.ww.household.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddMemberCommand(
        @TargetAggregateIdentifier
        String houseHoldId,
        String memberName
) {
}

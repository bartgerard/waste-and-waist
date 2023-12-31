package be.ww.household.api.command;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record JoinHouseHoldCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId,
        MemberId memberId,
        UserId userId
) {
}

package be.ww.household.api.command;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RemoveMemberCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId,
        MemberId memberId
) {
}

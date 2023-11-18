package be.ww.household.api.command;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

public record AddMemberCommand(
        @TargetAggregateIdentifier
        HouseHoldId houseHoldId,
        MemberId memberId,
        String memberName,
        LocalDate birthDate
) {
}

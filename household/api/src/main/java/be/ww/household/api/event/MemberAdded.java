package be.ww.household.api.event;

import be.ww.household.api.type.HouseHoldId;
import be.ww.household.api.type.MemberId;

import java.time.LocalDate;

public record MemberAdded(
        HouseHoldId houseHoldId,
        MemberId memberId,
        String memberName,
        LocalDate birthDate
) {
}

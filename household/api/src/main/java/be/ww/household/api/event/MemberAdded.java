package be.ww.household.api.event;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;

import java.time.LocalDate;

public record MemberAdded(
        HouseHoldId houseHoldId,
        MemberId memberId,
        String memberName,
        LocalDate birthDate
) {
}

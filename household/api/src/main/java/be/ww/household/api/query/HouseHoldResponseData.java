package be.ww.household.api.query;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record HouseHoldResponseData(
        Set<HouseHold> houseHolds
) {
    public record HouseHold(
            HouseHoldId houseHoldId,
            String name,
            List<Member> members
    ) {
    }

    public record Member(
            MemberId memberId,
            String name,
            LocalDate birthDate
    ) {
    }
}

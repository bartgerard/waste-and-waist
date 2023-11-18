package be.ww.household.api.query;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record HouseHoldResponseData(
        Set<HouseHold> houseHolds
) {
    public record HouseHold(
            String houseHoldId,
            List<Member> members
    ) {
    }

    public record Member(
            String memberId,
            String name,
            LocalDate birthDate
    ) {
    }
}

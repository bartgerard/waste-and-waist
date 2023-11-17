package be.ww.household.api.query;

import java.util.Set;

public record HouseHoldResponseData(
        Set<HouseHold> houseHolds
) {
    public record HouseHold(
            String houseHoldId
    ) {
    }
}

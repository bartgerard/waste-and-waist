package be.ww.household.api.event;

import be.ww.household.api.type.HouseHoldId;

public record HouseHoldJoinedEvent(
        HouseHoldId houseHoldId,
        String userId,
        String memberName
) {
}

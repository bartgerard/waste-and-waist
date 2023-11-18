package be.ww.household.api.event;


import be.ww.shared.type.HouseHoldId;

public record HouseHoldJoinedEvent(
        HouseHoldId houseHoldId,
        String userId,
        String memberName
) {
}

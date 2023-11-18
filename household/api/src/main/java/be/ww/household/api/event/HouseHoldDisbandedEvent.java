package be.ww.household.api.event;


import be.ww.shared.type.HouseHoldId;

public record HouseHoldDisbandedEvent(
        HouseHoldId houseHoldId
) {
}

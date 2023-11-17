package be.ww.household.api.event;

import be.ww.household.api.type.HouseHoldId;

public record HouseHoldStartedEvent(
        HouseHoldId houseHoldId,
        String houseHoldName
) {
}

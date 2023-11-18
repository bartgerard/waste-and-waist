package be.ww.household.api.event;

import be.ww.shared.type.HouseHoldId;

public record HouseHoldStartedEvent(
        HouseHoldId houseHoldId,
        String houseHoldName
) {
}

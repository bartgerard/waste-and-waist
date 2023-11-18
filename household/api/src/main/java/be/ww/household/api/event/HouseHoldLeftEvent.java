package be.ww.household.api.event;


import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.UserId;

public record HouseHoldLeftEvent(
        HouseHoldId houseHoldId,
        UserId userId
) {
}

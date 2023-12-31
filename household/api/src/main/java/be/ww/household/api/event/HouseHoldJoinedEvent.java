package be.ww.household.api.event;


import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;

public record HouseHoldJoinedEvent(
        HouseHoldId houseHoldId,
        MemberId memberId,
        UserId userId
) {
}

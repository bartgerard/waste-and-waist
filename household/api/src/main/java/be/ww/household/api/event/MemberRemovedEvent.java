package be.ww.household.api.event;

import be.ww.shared.type.HouseHoldId;
import be.ww.shared.type.MemberId;

public record MemberRemovedEvent(
        HouseHoldId houseHoldId,
        MemberId memberId
) {
}

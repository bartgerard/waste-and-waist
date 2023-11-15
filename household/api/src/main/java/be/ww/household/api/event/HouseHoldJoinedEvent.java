package be.ww.household.api.event;

public record HouseHoldJoinedEvent(
        String houseHoldId,
        String userId,
        String memberName
) {
}

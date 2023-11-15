package be.ww.household.api.event;

public record HouseHoldStartedEvent(
        String houseHoldId,
        String houseHoldName,
        String userId,
        String memberName
) {
}

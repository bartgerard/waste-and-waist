package be.ww.household.api.event;

public record MemberAdded(
        String houseHoldId,
        String memberName
) {
}

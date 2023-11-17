package be.ww.household.command.web;

public record HouseHoldRequestData(
        String houseHoldName,
        String userId,
        String memberName
) {
}

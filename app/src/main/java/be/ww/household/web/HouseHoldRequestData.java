package be.ww.household.web;

import java.time.LocalDate;

public record HouseHoldRequestData(
        String houseHoldName,
        String userId,
        String memberName,
        LocalDate birthDate
) {
}

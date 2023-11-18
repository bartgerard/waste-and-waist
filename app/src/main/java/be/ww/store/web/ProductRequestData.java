package be.ww.store.web;

import java.time.LocalDate;

public record ProductRequestData(
        String houseHoldName,
        String userId,
        String memberName,
        LocalDate birthDate
) {
}

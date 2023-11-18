package be.ww.household.web;

import java.time.LocalDate;

public record MemberRequestData(
        String name,
        LocalDate birthDate
) {
}

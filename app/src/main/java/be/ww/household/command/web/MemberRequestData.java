package be.ww.household.command.web;

import java.time.LocalDate;

public record MemberRequestData(
        String name,
        LocalDate birthDate
) {
}

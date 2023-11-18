package be.ww.store.web;

import java.time.LocalDate;

public record MemberRequestData(
        String name,
        LocalDate birthDate
) {
}

package be.ww.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDate;

public abstract class DayMixin {
    @JsonCreator
    public DayMixin(
            LocalDate day
    ) {
    }

    @JsonValue
    abstract LocalDate day();
}

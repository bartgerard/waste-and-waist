package be.ww.shared.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDateTime;

abstract class ExampleTimeRecordMixin {
    @JsonCreator
    ExampleTimeRecordMixin(LocalDateTime value) {
    }

    @JsonValue
    abstract LocalDateTime value();
}

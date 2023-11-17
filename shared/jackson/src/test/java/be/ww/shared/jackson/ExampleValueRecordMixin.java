package be.ww.shared.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

abstract class ExampleValueRecordMixin {
    @JsonCreator
    ExampleValueRecordMixin(String value) {
    }

    @JsonValue
    abstract String value();
}

package be.ww.shared.jackson;

import com.fasterxml.jackson.annotation.JsonValue;

record AnnotatedExampleValueRecord(
        @JsonValue String value
) {
}

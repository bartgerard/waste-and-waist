package be.ww.shared.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;

abstract class ExampleValueObjectMixin {
    @JsonCreator
    ExampleValueObjectMixin(String value) {
    }
}

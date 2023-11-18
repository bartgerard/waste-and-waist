package be.ww.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public abstract class IdMixin {
    @JsonCreator
    public IdMixin(
            String id
    ) {
    }

    @JsonValue
    abstract String id();
}

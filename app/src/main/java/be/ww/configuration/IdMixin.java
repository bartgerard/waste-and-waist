package be.ww.configuration;

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

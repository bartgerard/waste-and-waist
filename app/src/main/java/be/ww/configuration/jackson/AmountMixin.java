package be.ww.configuration.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.math.BigDecimal;

public abstract class AmountMixin {
    @JsonCreator
    public AmountMixin(
            BigDecimal value
    ) {
    }

    @JsonValue
    abstract BigDecimal value();
}

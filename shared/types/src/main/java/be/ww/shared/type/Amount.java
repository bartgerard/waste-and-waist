package be.ww.shared.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public record Amount(
        BigDecimal value
) {
    public Amount(
            final BigDecimal value
    ) {
        notNull(value, "value is invalid [null]");
        isTrue(value.signum() >= 0, "amount should be positive");

        this.value = value.setScale(2, RoundingMode.DOWN);
    }

    public static Amount of(
            final BigDecimal value
    ) {
        return new Amount(value);
    }

    public Amount subtract(
            final Amount subtrahend
    ) {
        return new Amount(
                value().subtract(subtrahend.value())
        );
    }
}

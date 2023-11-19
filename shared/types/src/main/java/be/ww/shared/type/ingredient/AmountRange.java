package be.ww.shared.type.ingredient;

import be.ww.shared.type.Amount;

import java.math.BigDecimal;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public record AmountRange(
        Amount min,
        Amount max
) {
    public AmountRange {
        notNull(min);
        notNull(max);
    }

    public static AmountRange of(
            final Amount min,
            final Amount max
    ) {
        return new AmountRange(min, max);
    }

    public static AmountRange parse(
            final String range
    ) {
        notEmpty(range);

        final String[] minMax = range.split("\\.\\.");

        final BigDecimal min = new BigDecimal(minMax[0]);
        final BigDecimal max = minMax.length == 1
                ? min
                : new BigDecimal(minMax[1]);

        return AmountRange.of(
                Amount.of(min),
                Amount.of(max)
        );
    }

    public static String serialize(final AmountRange range) {
        if (range.min().isEqualTo(range.max)) {
            return String.format("%s", range.min().value());
        } else {
            return String.format("%s..%s", range.min().value(), range.max().value());
        }
    }

    @Override
    public String toString() {
        return serialize(this);
    }
}

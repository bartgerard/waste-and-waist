package be.ww.shared.type.ingredient;

import be.ww.shared.type.Amount;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;

public record Quantity(
        Amount amount,
        Unit unit
) {
    public Quantity subtract(
            final Quantity subtrahend
    ) {
        isTrue(Objects.equals(unit(), subtrahend.unit()), "Not the same units, please convert before subtraction");

        return new Quantity(amount().subtract(subtrahend.amount()), unit());
    }

    public enum Unit {
        LITER("L"),
        GRAM("g"),
        KILOGRAM("kg");

        private final String symbol;

        Unit(final String symbol) {
            this.symbol = symbol;
        }

        public String symbol() {
            return symbol;
        }
    }

}

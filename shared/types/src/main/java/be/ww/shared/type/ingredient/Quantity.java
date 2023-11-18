package be.ww.shared.type.ingredient;

import java.math.BigDecimal;

public record Quantity(
        BigDecimal amount,
        Unit unit
) {
    public enum Unit {
        LITER("L"),
        GRAM("g");

        private final String symbol;

        Unit(final String symbol) {
            this.symbol = symbol;
        }

        public String symbol() {
            return symbol;
        }
    }
}

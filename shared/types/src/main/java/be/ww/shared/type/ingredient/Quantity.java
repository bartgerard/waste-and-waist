package be.ww.shared.type.ingredient;

import be.ww.shared.type.Amount;

public record Quantity(
        Amount amount,
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

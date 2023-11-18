package be.ww.shared.type.ingredient;

import be.ww.shared.type.Amount;

public record Quantity(
        Amount amount,
        Unit unit
) {
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

    public Quantity reduce(Quantity reduceWithQuantity) throws IllegalAccessException {
        if (this.unit.equals(reduceWithQuantity.unit())) {
            if (this.amount.value().compareTo(reduceWithQuantity.amount().value()) > 0) {
                Amount subtractedAmount = new Amount(this.amount.value().subtract(reduceWithQuantity.amount.value()));
                return new Quantity(subtractedAmount, this.unit());
            } else {
                throw new IllegalAccessException("To reduce quantity is bigger then starting quantity");
            }
        } else {
            throw new IllegalAccessException("Not the same units, please convert before subtraction");
        }
    }

}

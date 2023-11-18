package be.ww.shared.type.ingredient;

import be.ww.shared.type.Amount;

public record NutritionalFact(
        Fact fact,
        Amount amount
) {
    public enum Fact {
        ENERGY("kcal"),
        PROTEIN("g"),
        CARBOHYDRATE("g"),
        SUGAR("g"),
        FAT("g"),
        FIBRE("g"),
        SALT("g");

        private final String unit;

        Fact(final String unit) {
            this.unit = unit;
        }

        public String unit() {
            return unit;
        }
    }
}

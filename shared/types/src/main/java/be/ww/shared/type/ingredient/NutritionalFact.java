package be.ww.shared.type.ingredient;

public record NutritionalFact(

) {
    public enum Type {
        ENERGY("kcal"),
        PROTEIN("g"),
        CARBOHYDRATE("g"),
        SUGAR("g"),
        FAT("g"),
        FIBRE("g"),
        SALT("g");

        private final String unit;

        Type(final String unit) {
            this.unit = unit;
        }

        public String unit() {
            return unit;
        }
    }
}

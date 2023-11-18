package be.ww.shared.type.ingredient;

public enum NutritionalFact {
    ENERGY("kcal"),
    PROTEIN("g"),
    CARBOHYDRATE("g"),
    SUGAR("g"),
    FAT("g"),
    SATURATED_FAT("g"),
    FIBRE("g"),
    SALT("g");

    private final String unit;

    NutritionalFact(final String unit) {
        this.unit = unit;
    }

    public String unit() {
        return unit;
    }
}

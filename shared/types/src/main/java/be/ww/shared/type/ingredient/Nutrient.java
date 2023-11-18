package be.ww.shared.type.ingredient;

public enum Nutrient {
    VITAMIN_A(null),
    VITAMIN_C(null),
    VITAMIN_B1("THIAMINE"),
    VITAMIN_B2("RIBOFLAVIN"),
    VITAMIN_B3("NIACIN"),
    VITAMIN_B5("PANTOTHENIC_ACID"),
    VITAMIN_B6("PYRIDOXINE"),
    VITAMIN_B9("FOLATE"),
    VITAMIN_B7("BIOTIN"),
    VITAMIN_B12("CYANOCOBALAMIN"),
    VITAMIN_D(null),
    VITAMIN_E(null),
    VITAMIN_K(null),
    CALCIUM(null),
    IRON(null),
    PHOSPHORUS(null),
    IODINE(null),
    MAGNESIUM(null),
    ZINC(null),
    SELENIUM(null),
    COPPER(null),
    MANGANESE(null),
    CHROMIUM(null),
    MOLYBDENUM(null),
    CHLORIDE(null);

    private final String alternateLabel;

    Nutrient(final String alternateLabel) {
        this.alternateLabel = alternateLabel;
    }

    public String alternateLabel() {
        return alternateLabel;
    }
}

package be.ww.shared.type.ingredient;


import be.ww.shared.domain.api.AggregateId;

public record IngredientId(
        String id
) implements AggregateId {
    public static IngredientId of(
            final String id
    ) {
        return new IngredientId(id);
    }

    @Override
    public String toString() {
        return "ingredient-%s".formatted(id);
    }
}

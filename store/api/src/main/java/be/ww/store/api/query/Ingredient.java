package be.ww.store.api.query;

import be.ww.shared.type.ingredient.IngredientId;
import lombok.Builder;

@Builder
public record Ingredient(
        IngredientId ingredientId
) {
}

package be.ww.store.api.query;

import be.ww.shared.type.ingredient.IngredientId;
import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public record Ingredient(
        IngredientId ingredientId,
        @Singular
        List<Product> products
) {
}

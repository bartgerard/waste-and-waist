package be.ww.store.api.query;

import be.ww.shared.type.ingredient.IngredientId;

public record FindIngredientByIdQuery(
        IngredientId ingredientId
) {
}

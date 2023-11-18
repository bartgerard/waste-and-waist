package be.ww.store.api.event;

import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.IngredientId;

import java.util.Set;

public record ProductAddedEvent(
        IngredientId ingredientId,
        ProductId productId,
        String productName,
        String brand,
        Set<String> stores
) {
}

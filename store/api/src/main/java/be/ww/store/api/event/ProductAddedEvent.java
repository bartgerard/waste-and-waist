package be.ww.store.api.event;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.Allergen;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.NutritionalFact;
import be.ww.shared.type.ingredient.Quantity;

import java.util.Map;
import java.util.Set;

public record ProductAddedEvent(
        IngredientId ingredientId,
        ProductId productId,
        String productName,
        String brand,
        Set<String> stores,
        Quantity unitQuantity,
        Map<NutritionalFact, Amount> nutritionalFacts,
        Set<Allergen> allergens
) {
}

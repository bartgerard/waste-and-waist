package be.ww.store.api.query;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.Allergen;
import be.ww.shared.type.ingredient.NutritionalFact;
import be.ww.shared.type.ingredient.Quantity;
import lombok.Builder;

import java.util.Map;
import java.util.Set;

@Builder
public record Product(
        ProductId productId,
        String productName,
        String brand,
        Set<String> stores,
        Quantity unitQuantity,
        Map<NutritionalFact, Amount> nutritionalFacts,
        Set<Allergen> allergens
) {
}

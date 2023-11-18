package be.ww.store.api.command;

import be.ww.shared.type.Amount;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.NutritionalFact;
import be.ww.shared.type.ingredient.Quantity;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Map;
import java.util.Set;

public record AddProductCommand(
        @TargetAggregateIdentifier
        IngredientId ingredientId,
        ProductId productId,
        String productName,
        String brand,
        Set<String> stores,
        Quantity unitQuantity,
        Map<NutritionalFact, Amount> nutritionalFacts
) {
}

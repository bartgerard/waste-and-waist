package be.ww.store.api.command;

import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.IngredientId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Set;

public record AddProductCommand(
        @TargetAggregateIdentifier
        IngredientId ingredientId,
        ProductId productId,
        String productName,
        String brand,
        Set<String> stores
) {
}

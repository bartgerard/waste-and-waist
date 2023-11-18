package be.ww.stock.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;
import be.ww.shared.type.ingredient.BestBeforeDay;
import be.ww.shared.type.ingredient.IngredientId;
import be.ww.shared.type.ingredient.Quantity;
import be.ww.shared.type.ingredient.UseByDay;

public record StoreProvisionsCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        ProductId productId,
        ProvisionId provisionId,
        IngredientId ingredientId,
        Quantity quantity,
        BestBeforeDay bestBeforeDay,
        UseByDay useByDay

) {
}

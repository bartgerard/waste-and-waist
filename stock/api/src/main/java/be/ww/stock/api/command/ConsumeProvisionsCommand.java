package be.ww.stock.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;
import be.ww.shared.type.ingredient.Quantity;

public record ConsumeProvisionsCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        ProductId productId,
        ProvisionId provisionId,
        Quantity quantity
) {
}

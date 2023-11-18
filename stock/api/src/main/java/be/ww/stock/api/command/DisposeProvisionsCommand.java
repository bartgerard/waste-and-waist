package be.ww.stock.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ProvisionId;

public record DisposeProvisionsCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        ProductId productId,
        ProvisionId provisionId
) {
}

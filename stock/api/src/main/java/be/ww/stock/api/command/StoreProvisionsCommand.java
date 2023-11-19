package be.ww.stock.api.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.shared.type.ProductId;
import be.ww.shared.type.ingredient.BestBeforeDay;
import be.ww.shared.type.ingredient.Quantity;
import be.ww.shared.type.ingredient.UseByDay;
import lombok.Builder;

@Builder
public record StoreProvisionsCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        List<Product> provisions

) {
    @Builder
    public record Product(
            ProductId productId,
            Quantity quantity,
            BestBeforeDay bestBeforeDay,
            UseByDay useByDay
    ) {
    }
}

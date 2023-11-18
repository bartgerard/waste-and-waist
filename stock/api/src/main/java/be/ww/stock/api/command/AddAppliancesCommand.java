package be.ww.stock.api.command;

import be.ww.shared.type.LocationId;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddAppliancesCommand(
        @TargetAggregateIdentifier
        LocationId locationId
) {
}

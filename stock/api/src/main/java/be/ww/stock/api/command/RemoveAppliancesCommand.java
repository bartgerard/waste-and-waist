package be.ww.stock.api.command;

import java.util.Set;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;

public record RemoveAppliancesCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        Set<String> appliances
) {
}

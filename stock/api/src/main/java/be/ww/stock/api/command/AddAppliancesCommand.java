package be.ww.stock.api.command;

import java.util.Set;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.type.Appliance;

public record AddAppliancesCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        Set<Appliance> appliances
) {
}

package be.ww.stock.api.command;

import java.util.Set;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.LocationId;
import be.ww.stock.api.type.StorageFacility;

public record AddStorageFacilitiesCommand(
        @TargetAggregateIdentifier
        LocationId locationId,
        Set<StorageFacility> storageFacilities
) {
}

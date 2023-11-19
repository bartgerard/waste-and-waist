package be.ww.stock.api.query;

import be.ww.shared.type.LocationId;
import lombok.Builder;

import java.util.Set;

public record LocationsResponseData(
        Set<Location> locations
) {
    public record Location(
            LocationId locationId,
            String name
    ) {
    }
}

package be.ww.stock.api.query;

import be.ww.shared.type.LocationId;

public record FindLocationByIdQuery(
		LocationId locationId
) {
}

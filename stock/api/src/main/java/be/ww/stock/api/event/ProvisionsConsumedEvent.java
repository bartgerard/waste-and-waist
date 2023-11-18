package be.ww.stock.api.event;

import be.ww.shared.type.LocationId;

public record ProvisionsConsumedEvent(
        LocationId locationId
) {
}

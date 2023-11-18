package be.ww.stock.api.type;

import be.ww.shared.domain.api.EntityId;

public record HouseHoldId(
        String id
) implements EntityId {
    public static HouseHoldId of(
            final String id
    ) {
        return new HouseHoldId(id);
    }
}

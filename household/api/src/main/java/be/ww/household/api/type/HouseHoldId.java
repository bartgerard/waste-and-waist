package be.ww.household.api.type;

import be.ww.shared.domain.api.AggregateId;

import java.util.UUID;

public record HouseHoldId(
        String id
) implements AggregateId {
    public static HouseHoldId create() {
        return new HouseHoldId(
                "house-hold-%s".formatted(UUID.randomUUID())
        );
    }

    public static HouseHoldId of(
            final String id
    ) {
        return new HouseHoldId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

package be.ww.shared.type;


import be.ww.shared.domain.api.AggregateId;

import java.util.UUID;

public record LocationId(
        String id
) implements AggregateId {
    public static LocationId create() {
        return new LocationId(
                "location-%s".formatted(UUID.randomUUID())
        );
    }

    public static LocationId of(
            final String id
    ) {
        return new LocationId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

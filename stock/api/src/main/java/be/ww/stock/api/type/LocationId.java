package be.ww.stock.api.type;

import java.util.UUID;

public record LocationId(
        String id
) {
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
}

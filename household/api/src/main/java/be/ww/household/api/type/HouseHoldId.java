package be.ww.household.api.type;

import java.util.UUID;

public record HouseHoldId(
        String id
) {
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
}

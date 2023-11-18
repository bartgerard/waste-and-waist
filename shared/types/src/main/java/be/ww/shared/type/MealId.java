package be.ww.shared.type;

import be.ww.shared.domain.api.EntityId;

import java.util.UUID;

public record MealId(
        String id
) implements EntityId {
    public static MealId create() {
        return new MealId(
                "meal-%s".formatted(UUID.randomUUID())
        );
    }

    public static MealId of(
            final String id
    ) {
        return new MealId(id);
    }
}

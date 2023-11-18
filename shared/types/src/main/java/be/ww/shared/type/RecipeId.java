package be.ww.shared.type;


import be.ww.shared.domain.api.AggregateId;

import java.util.UUID;

public record RecipeId(
        String id
) implements AggregateId {
    public static RecipeId create() {
        return new RecipeId(
                "recipe-%s".formatted(UUID.randomUUID())
        );
    }

    public static RecipeId of(
            final String id
    ) {
        return new RecipeId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

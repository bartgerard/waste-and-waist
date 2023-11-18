package be.ww.shared.type;

import be.ww.shared.domain.api.EntityId;

import java.util.UUID;

public record ProductId(
        String id
) implements EntityId {
    public static ProductId create() {
        return new ProductId(
                "product-%s".formatted(UUID.randomUUID())
        );
    }

    public static ProductId of(
            final String id
    ) {
        return new ProductId(id);
    }
}

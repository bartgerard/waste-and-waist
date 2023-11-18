package be.ww.shared.type;

import be.ww.shared.domain.api.EntityId;

import java.util.UUID;

public record UserId(
        String id
) implements EntityId {
    public static UserId create() {
        return new UserId(UUID.randomUUID().toString());
    }

    public static UserId of(
            final String id
    ) {
        return new UserId(id);
    }
}

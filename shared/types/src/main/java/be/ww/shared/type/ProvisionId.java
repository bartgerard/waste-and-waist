package be.ww.shared.type;


import be.ww.shared.domain.api.AggregateId;

import java.util.UUID;

public record ProvisionId(
        String id
) implements AggregateId {
    public static ProvisionId create() {
        return new ProvisionId(
                "provision-%s".formatted(UUID.randomUUID())
        );
    }

    public static ProvisionId of(
            final String id
    ) {
        return new ProvisionId(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

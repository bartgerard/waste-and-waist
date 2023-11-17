package be.ww.household.api.type;

import java.util.UUID;

public record MemberId(
        String id
) {
    public static MemberId create() {
        return new MemberId(
                "member-%s".formatted(UUID.randomUUID())
        );
    }

    public static MemberId of(
            final String id
    ) {
        return new MemberId(id);
    }
}

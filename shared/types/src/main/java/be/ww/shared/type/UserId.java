package be.ww.shared.type;

import be.ww.shared.domain.api.EntityId;

public record UserId(
        String id
) implements EntityId {
    public static MemberId of(
            final String id
    ) {
        return new MemberId(id);
    }
}

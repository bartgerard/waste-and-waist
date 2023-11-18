package be.ww.user.query;

import be.ww.shared.type.UserId;

public record UserResponseData(
        UserId userId,
        String username
) {
}

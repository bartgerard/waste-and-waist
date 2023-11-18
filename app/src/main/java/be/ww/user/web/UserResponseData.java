package be.ww.user.web;

import be.ww.shared.type.UserId;

public record UserResponseData(UserId userId, String Username) {
}

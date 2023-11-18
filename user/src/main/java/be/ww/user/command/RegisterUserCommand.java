package be.ww.user.command;

public record RegisterUserCommand(
        String username,
        String password
) {
}

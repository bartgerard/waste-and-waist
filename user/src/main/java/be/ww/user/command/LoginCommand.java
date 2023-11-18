package be.ww.user.command;

public record LoginCommand(
        String username,
        String password
) {
}

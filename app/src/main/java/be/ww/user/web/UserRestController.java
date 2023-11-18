package be.ww.user.web;

import be.ww.user.command.LoginCommand;
import be.ww.user.command.RegisterUserCommand;
import be.ww.user.query.UserResponseData;
import be.ww.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping(path = "register")
    public void register(
            final String username,
            final String password
    ) {
        userService.handle(new RegisterUserCommand(
                username,
                password
        ));
    }

    @PostMapping(path = "login")
    public UserResponseData login(
            final String username,
            final String password
    ) {
        return userService.handle(new LoginCommand(
                username,
                password
        ));
    }
}


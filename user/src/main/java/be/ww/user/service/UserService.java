package be.ww.user.service;

import be.ww.shared.type.UserId;
import be.ww.user.command.LoginCommand;
import be.ww.user.command.RegisterUserCommand;
import be.ww.user.query.UserResponseData;
import be.ww.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.isTrue;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void handle(
            final RegisterUserCommand command
    ) {
        final UserId userId = UserId.create();

        userRepository.save(new UserDocument(
                null,
                userId.id(),
                command.username(),
                command.password()
        ));
    }

    public UserResponseData handle(
            final LoginCommand loginCommand
    ) {
        final UserDocument user = userRepository.findUserByUsername(loginCommand.username())
                .orElseThrow(() -> new IllegalArgumentException("user does not exist"));

        isTrue(Objects.equals(user.getPassword(), loginCommand.password()), "invalid password");

        return new UserResponseData(
                UserId.of(user.getUserId()),
                user.getUsername()
        );
    }

}

package be.ww.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    UserRepository repository;


    public User registerUser(String username, String password) {
        User user = new User(username, password);
        return repository.save(user);
    }

    public User loginUser(String username, String password) {
        User foundUser = repository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("user does not exist"));
        if (foundUser.password.equals(password)) {
            return foundUser;
        } else {
            throw new IllegalArgumentException("wrong password");
        }
    }

    public User getUser(String userId) {
        return repository.findUserByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
    }
}

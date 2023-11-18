package be.ww.user.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ww.shared.type.UserId;
import be.ww.user.User;
import be.ww.user.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("users") @AllArgsConstructor
public class UserRestController {
	private UserService userService;

	@PostMapping(path="/register")
	public UserResponseData register (String username, String password) {
		User user = userService.registerUser(username, password);
		return new UserResponseData(UserId.of(user.getUserId()),user.getUsername());
	}
	@PostMapping(path="/login")
	public UserResponseData login (String username, String password) {
		User user = userService.loginUser(username, password);
		return new UserResponseData(UserId.of(user.getUserId()),user.getUsername());
	}
}


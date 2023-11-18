package be.ww.user;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity @NoArgsConstructor @Getter
public class User {
	@Id
	String userId;
	String username;
	String password;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}

package be.ww.user;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
	Optional<User> findUserByUsername(String username);
	Optional<User> findUserByUserId(String userId);


}

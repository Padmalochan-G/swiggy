package user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import user.service.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.email = :email AND u.phone = :phone")
	public User findByEmailPhone(@Param("email") String email,@Param("phone") String phone);

	@Query("SELECT u FROM User u WHERE u.email = :emailId AND u.password = :password")
	public User findByEmailIdPassword(@Param("emailId") String emailId,@Param("password") String password);

}

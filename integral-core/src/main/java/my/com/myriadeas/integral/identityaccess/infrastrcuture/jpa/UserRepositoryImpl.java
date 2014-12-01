package my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa;

import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryImpl extends JpaRepository<User, Long>,
		UserRepository {

}

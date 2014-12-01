package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.identityaccess.domain.model.DuplicatedUsernameException;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;
import my.com.myriadeas.integral.publisher.Publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("identityServiceImpl")
public class IdentityServiceImpl implements IdentityService {

	private UserRepository userRepository;
	private Publisher publisher;

	public IdentityServiceImpl() {
	}

	@Transactional
	public User registerUser(RegisterUserCommand command) {
		User user = new User(command.getUsername(), command.getPassword());
		try {
			user = this.userRepository.save(user);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicatedUsernameException("Duplicated Username. ", e,
					command.getPassword());
		}

		this.publisher.publish(user.getRegisteredUserEvent());
		return user;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	@Qualifier("identityAccessPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}
package my.com.myriadeas.integral.identityaccess.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTest {

	@Test
	public void test() {
		PasswordEncoder passwordEncoder = new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				// TODO Auto-generated method stub
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword,
					String encodedPassword) {
				// TODO Auto-generated method stub
				return false;
			}

		};
		User user = new User("username", "password");
		user.setPasswordEncoder(passwordEncoder);
		user.setRegisterUserValidator(new RegisterUserValidator());
		user.postConstruct();
		Map<String, DomainEvent> expectedDomainEvent = new HashMap<String, DomainEvent>();
		UserRegistered domainEvent = new UserRegistered("username", null);
		expectedDomainEvent.put("userRegistered", domainEvent);
		assertEquals(expectedDomainEvent, user.getRegisteredUserEvent());
		try {
			user = new User("", "");
			user.setPasswordEncoder(passwordEncoder);
			user.setRegisterUserValidator(new RegisterUserValidator());
			user.postConstruct();
			fail("should throw register user exception");
		} catch (RegisterUserException e) {

		}
	}

}

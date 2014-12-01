package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.identityaccess.AbstractIdentityAccessIntegrationTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentityServiceImplTest extends
		AbstractIdentityAccessIntegrationTest {

	@Autowired
	private IdentityService identityService;

	@Test
	public void testRegisterUser() {
		RegisterUserCommand command = new RegisterUserCommand("username",
				"password");
		identityService.registerUser(command);
	}

}

package my.com.myriadeas.integral.identityaccess.domain.model.access;

import static org.junit.Assert.assertEquals;
import my.com.myriadeas.integral.identityaccess.AbstractIdentityAccessIntegrationTest;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa.RoleRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleTest extends AbstractIdentityAccessIntegrationTest {

	@Autowired
	RoleRepositoryImpl repo;

	@Test
	public void test() {

	}

	@Test
	public void testProvisionRole() throws Exception {
		User user = new User("username", "password");
		Role role = user.provisionRole("Manager", "A manager role.");
		repo.save(role);
		assertEquals(1, repo.findAll().size());
	}

}

package my.com.myriadeas.integral.identityaccess.domain.model.access;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

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
		System.out.println(repo.findAll());
		Set<Permission> permissions = new HashSet<Permission>();
		Permission PERM_FOUNDATION = new Permission("PERM_FOUNDATION",
				"Permission to manage foundation");
		Permission PERM_CIRCULATION_POLICY = new Permission(
				"PERM_CIRCULATION_POLICY",
				"Permission to change circulation policy");
		Permission PERM_CIRCULATION = new Permission("PERM_CIRCULATION",
				"Permission to checkin/checkout/renew/reserve");
		permissions.add(PERM_FOUNDATION);
		permissions.add(PERM_CIRCULATION_POLICY);
		permissions.add(PERM_CIRCULATION);
		role.assignPermission(permissions);
		repo.save(role);
		assertEquals(1, repo.findAll().size());
		assertEquals(3, repo.findAll().get(0).getPermissions().size());
		System.out.println(repo.findAll());
	}

}

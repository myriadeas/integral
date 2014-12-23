package my.com.myriadeas.integral.identityaccess.domain.model.access;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.integral.identityaccess.AbstractIdentityAccessIntegrationTest;
import my.com.myriadeas.integral.identityaccess.domain.model.Group;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.access.RoleRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

public class RoleTest extends AbstractIdentityAccessIntegrationTest {

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	GroupRepository groupRepo;

	@Autowired
	private GroupMemberService groupMemberService;

	@Test
	public void test() {

	}

	@Test
	public void testProvisionRole() throws Exception {
		User user = new User("username", "password");
		Role role = user.provisionRole("Manager", "A manager role.");
		roleRepo.save(role);
		assertEquals(1, roleRepo.findAll().size());
		System.out.println(roleRepo.findAll());
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
		roleRepo.save(role);
		assertEquals(1, roleRepo.findAll().size());
		assertEquals(3, roleRepo.findAll().get(0).getPermissions().size());
	}

	@Test
	public void testRoleUniqueness() throws Exception {

		User user = new User("username", "password");
		Role role1 = user.provisionRole("Manager", "A manager role.");
		roleRepo.save(role1);

		boolean nonUnique = false;

		try {
			Role role2 = user.provisionRole("Manager", "A manager role.");
			roleRepo.save(role2);

			fail("Should have thrown exception for nonuniqueness.");

		} catch (DataIntegrityViolationException e) {
			nonUnique = true;
		}

		assertTrue(nonUnique);
	}

	@Test
	public void testUserIsInRole() throws Exception {
		User user = new User("username", "password");
		user = userRepo.save(user);
		Role managerRole = user.provisionRole("Manager", "A manager role.",
				true);
		Group group = new Group("Managers", "A group of managers.");
		groupRepo.save(group);
		managerRole.assignGroup(group, groupMemberService);
		roleRepo.save(managerRole);
		group.addUser(user);

		assertTrue(group.isMember(user, groupMemberService));
		assertTrue(managerRole.isInRole(user, groupMemberService));
	}

	@Test
	public void testUserIsNotInRole() throws Exception {
		User user = new User("username", "password");
		user = userRepo.save(user);
		Role managerRole = user.provisionRole("Manager", "A manager role.",
				true);
		Group group = user.provisionGroup("Managers", "A group of managers.");
		groupRepo.save(group);
		managerRole.assignGroup(group, groupMemberService);
		roleRepo.save(managerRole);
		Role accountantRole = new Role("Accountant", "An accountant role.");
		accountantRole.assignGroup(group, groupMemberService);
		roleRepo.save(accountantRole);

		assertFalse(managerRole.isInRole(user, groupMemberService));
		assertFalse(accountantRole.isInRole(user, groupMemberService));

		group.addUser(user);
		assertTrue(managerRole.isInRole(user, groupMemberService));
		assertTrue(accountantRole.isInRole(user, groupMemberService));
	}

	@Test
	public void testNoRoleInternalGroupsInFindGroupByName() throws Exception {
		User user = new User("username", "password");
		Role roleA = user.provisionRole("RoleA", "A role of A.");
		roleRepo.save(roleA);

		Group group = groupRepo.findByName(roleA.group().name());
		System.out.println(group);
			
	}

}

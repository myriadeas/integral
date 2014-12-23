package my.com.myriadeas.integral.identityaccess.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import my.com.myriadeas.integral.identityaccess.AbstractIdentityAccessIntegrationTest;
import my.com.myriadeas.integral.identityaccess.domain.model.Group;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa.GroupRepositoryImpl;
import my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa.RoleRepositoryImpl;
import my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa.UserRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IdentityServiceImplTest extends
		AbstractIdentityAccessIntegrationTest {

	@Autowired
	private IdentityService identityService;

	@Autowired
	RoleRepositoryImpl roleRepo;

	@Autowired
	UserRepositoryImpl userRepo;

	@Autowired
	GroupRepositoryImpl groupRepo;

	@Autowired
	private GroupMemberService groupMemberService;

	@Test
	public void testRegisterUser() {
		RegisterUserCommand command = new RegisterUserCommand("username",
				"password");
		identityService.registerUser(command);
	}

	@Test
	public void testGroupNameToRoleName() {
		String groupName = "GROUP_PATRON";
		String expectedRoleName = "Patron";

		String roleName = "";
		roleName = toCamelCase(groupName);
		roleName = roleName.substring(roleName.indexOf(" ") + 1);
		assertEquals(expectedRoleName, roleName);

		groupName = "GROUP_SPECIAL_PATRON";
		expectedRoleName = "Special Patron";

		roleName = toCamelCase(groupName);
		roleName = roleName.substring(roleName.indexOf(" ") + 1);
		assertEquals(expectedRoleName, roleName);
	}

	static String toCamelCase(String s) {
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + " " + toProperCase(part);
		}
		return camelCaseString.substring(1);
	}

	static String toProperCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	@Test
	public void testRegisterOfficer() {
		String userName = "smeiyee";
		String password = "smeiyee";
		RegisterUserCommand command = new RegisterUserCommand(userName,
				password);
		identityService.registerOfficer(command);

		User user = userRepo.findByUsername(userName);
		Group group = groupRepo.findByName("GROUP_OFFICER");
		Role role = roleRepo.findByName("Officer");
		assertTrue(group.isMember(user, groupMemberService));
		assertTrue(role.isInRole(user, groupMemberService));
	}

	@Test
	public void testRegisterPatron() {
		String userName = "limsyenie";
		String password = "limsyenie";
		RegisterUserCommand command = new RegisterUserCommand(userName,
				password);
		identityService.registerPatron(command);

		User user = userRepo.findByUsername(userName);
		Group group = groupRepo.findByName("GROUP_PATRON");
		Role role = roleRepo.findByName("Patron");
		assertTrue(group.isMember(user, groupMemberService));
		assertTrue(role.isInRole(user, groupMemberService));
	}

	@Test
	public void testInvalidRole() {
		String userName1 = "smeiyee1";
		String password1 = "smeiyee1";
		RegisterUserCommand command1 = new RegisterUserCommand(userName1,
				password1);
		identityService.registerPatron(command1);

		User patron = userRepo.findByUsername(userName1);
		Group groupPatron = groupRepo.findByName("GROUP_PATRON");
		Role rolePatron = roleRepo.findByName("Patron");
		assertTrue(groupPatron.isMember(patron, groupMemberService));
		assertTrue(rolePatron.isInRole(patron, groupMemberService));

		String userName2 = "smeiyee2";
		String password2 = "smeiyee2";
		RegisterUserCommand command2 = new RegisterUserCommand(userName2,
				password2);
		identityService.registerOfficer(command2);
		User officer = userRepo.findByUsername(userName2);
		
		assertFalse(groupPatron.isMember(officer, groupMemberService));
		assertFalse(rolePatron.isInRole(officer, groupMemberService));
	}

}

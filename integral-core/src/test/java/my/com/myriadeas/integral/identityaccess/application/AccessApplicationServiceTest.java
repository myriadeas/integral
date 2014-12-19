//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package my.com.myriadeas.integral.identityaccess.application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import my.com.myriadeas.integral.identityaccess.AbstractIdentityAccessIntegrationTest;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa.RoleRepositoryImpl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccessApplicationServiceTest extends
		AbstractIdentityAccessIntegrationTest {

	@Autowired
	private IdentityService identityService;

	@Autowired
	RoleRepositoryImpl roleRepo;

	@Autowired
	private GroupMemberService groupMemberService;

	@Autowired
	private AccessApplicationService accessApplicationService;

	@Test
	public void testAssignUserToRole() throws Exception {

		String userName = "username";
		String password = "password";

		RegisterUserCommand command = new RegisterUserCommand(userName,
				password);
		User user = identityService.registerUser(command);

		Role role = user.provisionRole("Manager", "A manager role.");
		roleRepo.save(role);

		assertFalse(role.isInRole(user, groupMemberService));

		accessApplicationService.assignUserToRole(new AssignUserToRoleCommand(
				userName, role.name()));

		assertTrue(role.isInRole(user, groupMemberService));
	}
}

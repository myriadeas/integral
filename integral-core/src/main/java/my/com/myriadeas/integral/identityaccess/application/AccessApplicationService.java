package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.identityaccess.domain.model.access.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class AccessApplicationService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	public AccessApplicationService() {
		super();
	}

	@Transactional
	public void assignUserToRole(AssignUserToRoleCommand aCommand) {

		User user = this.userRepository()
				.findByUsername(aCommand.getUsername());

		if (user != null) {
			Role role = this.roleRepository()
					.findByName(aCommand.getRoleName());

			if (role != null) {
				role.assignUser(user);
			}
		}
	}

	@Transactional(readOnly = true)
	public boolean isUserInRole(String aUsername, String aRoleName) {

		User user = this.userInRole(aUsername, aRoleName);

		return user != null;
	}

	@Transactional
	public void provisionRole(ProvisionRoleCommand aCommand) {

		User user = this.userRepository()
				.findByUsername(aCommand.getUserName());

		Role role = user.provisionRole(aCommand.getRoleName(),
				aCommand.getDescription(), aCommand.isSupportsNesting());

		this.roleRepository().save(role);
	}

	@Transactional(readOnly = true)
	public User userInRole(String aUsername, String aRoleName) {

		User userInRole = null;

		User user = this.userRepository().findByUsername(aUsername);

		if (user != null) {
			Role role = this.roleRepository().findByName(aRoleName);

			if (role != null) {
				GroupMemberService groupMemberService = new GroupMemberService(
						this.userRepository(), this.groupRepository());

				if (role.isInRole(user, groupMemberService)) {
					userInRole = user;
				}
			}
		}

		return userInRole;
	}

	private GroupRepository groupRepository() {
		return this.groupRepository;
	}

	private RoleRepository roleRepository() {
		return this.roleRepository;
	}

	private UserRepository userRepository() {
		return this.userRepository;
	}
}

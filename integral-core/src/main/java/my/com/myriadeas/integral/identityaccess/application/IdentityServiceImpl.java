package my.com.myriadeas.integral.identityaccess.application;

import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.identityaccess.domain.model.DuplicatedUsernameException;
import my.com.myriadeas.integral.identityaccess.domain.model.Group;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupMemberService;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;
import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.identityaccess.domain.model.access.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("identityServiceImpl")
public class IdentityServiceImpl implements IdentityService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private GroupRepository groupRepository;
	private Publisher publisher;
	private GroupMemberService groupMemberService;

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

	@Override
	public void assignUserToGroup(
			AssignUserToGroupCommand assignUserToGroupCommand) {
		User user = userRepository.findByUsername(assignUserToGroupCommand
				.getUsername());
		Group group = groupRepository.findByName(assignUserToGroupCommand
				.getGroupName());
		if (group == null) {
			group = user.provisionGroup(
					assignUserToGroupCommand.getGroupName(), "A group of "
							+ assignUserToGroupCommand.getGroupName());
			groupRepository.save(group);
			String roleName = groupNameToRoleName(assignUserToGroupCommand
					.getGroupName());
			Role role = user.provisionRole(roleName,
					"A " + roleName + " role.", true);
			role.assignGroup(group, groupMemberService);
			roleRepository.save(role);
		}

		this.publisher.publish(group.addUser(user));
	}

	@Override
	public User registerOfficer(RegisterUserCommand registerUserCommand) {
		User user = registerUser(registerUserCommand);
		AssignUserToGroupCommand assignUserToGroupCommand = new AssignUserToGroupCommand(
				registerUserCommand.getUsername(), "GROUP_OFFICER");
		assignUserToGroup(assignUserToGroupCommand);
		return user;
	}

	@Override
	public User registerPatron(RegisterUserCommand registerUserCommand) {
		User user = registerUser(registerUserCommand);
		AssignUserToGroupCommand assignUserToGroupCommand = new AssignUserToGroupCommand(
				registerUserCommand.getUsername(), "GROUP_PATRON");
		assignUserToGroup(assignUserToGroupCommand);
		return user;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setGroupRepository(GroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	@Qualifier("identityAccessPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Autowired
	@Qualifier("groupMemberService")
	public void setGroupMemberService(GroupMemberService groupMemberService) {
		this.groupMemberService = groupMemberService;
	}

	private String groupNameToRoleName(String groupName) {
		String roleName = "";
		roleName = toCamelCase(groupName);
		return roleName.substring(roleName.indexOf(" ") + 1);
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

}
package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.com.myriadeas.integral.data.jpa.domain.Role;
import my.com.myriadeas.integral.data.jpa.domain.UserGroup;
import my.com.myriadeas.integral.data.jpa.repositories.impl.UserGroupRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userGroupDatabasePopulator")
public class UserGroupDatabasePopulator extends AbstractDatabasePopulator implements UserGroupDatabasePopulatorIntf {

	@Autowired
	UserGroupRepositoryImpl userGroupRepository;
	
	@Autowired
	RoleDatabasePopulator roleDatabasePopulator;

	public void init() {
		List<UserGroup> userGroups = new ArrayList<UserGroup>();
		userGroups.add(CATALOGUING_GROUP);
		userGroups.add(CIRCULATION_GROUP);
		Set<Role> roles = new HashSet<Role>();
		roles.add(ROLE_USER);
		CATALOGUING_GROUP.setRoles(roles);
		CIRCULATION_GROUP.setRoles(roles);
		userGroupRepository.save(userGroups);
	}
}

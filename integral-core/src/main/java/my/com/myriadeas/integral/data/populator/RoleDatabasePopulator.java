package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Role;
import my.com.myriadeas.integral.data.jpa.repositories.impl.RoleRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleDatabasePopulator")
public class RoleDatabasePopulator extends AbstractDatabasePopulator implements
		RoleDatabasePopulatorIntf {

	@Autowired
	private RoleRepositoryImpl roleRepository;

	@Autowired
	PermissionDatabasePopulatorIntf permissionDatabasePopulator;

	public void init() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(ROLE_SUPERUSER);
		roles.add(ROLE_USER);
		ROLE_SUPERUSER.getPermissions().add(PERM_CIRCULATION);
		ROLE_SUPERUSER.getPermissions().add(PERM_CIRCULATION_POLICY);
		ROLE_SUPERUSER.getPermissions().add(PERM_FOUNDATION);
		ROLE_USER.getPermissions().add(PERM_FOUNDATION);
		ROLE_USER.getPermissions().add(PERM_CIRCULATION);
		roleRepository.save(roles);
	}
}

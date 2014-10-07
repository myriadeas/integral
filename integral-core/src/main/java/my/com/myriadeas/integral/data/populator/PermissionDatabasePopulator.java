package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Permission;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PermissionRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "permissionDatabasePopulator")
public class PermissionDatabasePopulator extends AbstractDatabasePopulator implements PermissionDatabasePopulatorIntf {

	@Autowired
	private PermissionRepositoryImpl permissionRepository;

	public void init() {
		List<Permission> permissions = new ArrayList<Permission>();
		permissions.add(PERM_CIRCULATION);
		permissions.add(PERM_CIRCULATION_POLICY);
		permissions.add(PERM_FOUNDATION);
		permissionRepository.save(permissions);
	}


}

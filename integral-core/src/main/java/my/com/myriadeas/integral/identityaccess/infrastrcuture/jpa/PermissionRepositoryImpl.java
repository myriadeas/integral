package my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa;

import my.com.myriadeas.integral.identityaccess.domain.model.access.Permission;
import my.com.myriadeas.integral.identityaccess.domain.model.access.PermissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepositoryImpl extends
		JpaRepository<Permission, Long>, PermissionRepository {

}

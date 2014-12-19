package my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa;

import my.com.myriadeas.integral.identityaccess.domain.model.access.Role;
import my.com.myriadeas.integral.identityaccess.domain.model.access.RoleRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepositoryImpl extends JpaRepository<Role, Long>,
		RoleRepository {

}

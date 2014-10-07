package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Role;

public interface RoleData {
	Role ROLE_SUPERUSER = new Role("ROLE_SUPERUSER", "ROLE_SUPERUSER");
	Role ROLE_USER = new Role("ROLE_USER", "ROLE_USER");
}

package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Permission;

public interface PermissionData {
	Permission PERM_FOUNDATION = new Permission("PERM_FOUNDATION",
			"Permission to manage foundation");
	Permission PERM_CIRCULATION_POLICY = new Permission(
			"PERM_CIRCULATION_POLICY",
			"Permission to change circulation policy");
	Permission PERM_CIRCULATION = new Permission("PERM_CIRCULATION",
			"Permission to checkin/checkout/renew/reserve");
}

package my.com.myriadeas.integral.data.jpa.repositories.impl;

import my.com.myriadeas.integral.data.jpa.domain.Permission;


public interface PermissionRepositoryImpl extends
		my.com.myriadeas.integral.data.jpa.repositories.PermissionRepository {
	
	public Permission findByPermission(String permission);
}
package my.com.myriadeas.integral.identityaccess.domain.model.access;

public interface PermissionRepository {

	public Permission save(Permission permission);

	public Permission findByName(String aPermissionName);
}
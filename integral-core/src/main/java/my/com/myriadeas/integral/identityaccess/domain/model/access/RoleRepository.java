package my.com.myriadeas.integral.identityaccess.domain.model.access;

public interface RoleRepository {

	public Role save(Role role);

	public Role findByName(String aRoleName);
}
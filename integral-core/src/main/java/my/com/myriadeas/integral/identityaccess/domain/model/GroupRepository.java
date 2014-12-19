package my.com.myriadeas.integral.identityaccess.domain.model;

public interface GroupRepository {

	public Group save(Group group);

	public Group findByName(String name);

}
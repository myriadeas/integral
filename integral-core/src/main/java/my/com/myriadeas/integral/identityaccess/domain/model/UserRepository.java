package my.com.myriadeas.integral.identityaccess.domain.model;

public interface UserRepository {

	public abstract User save(User user);

	public abstract User findByUsername(String s);
}
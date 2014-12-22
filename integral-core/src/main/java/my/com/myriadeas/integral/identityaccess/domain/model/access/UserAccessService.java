package my.com.myriadeas.integral.identityaccess.domain.model.access;

import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.integral.identityaccess.domain.model.User;
import my.com.myriadeas.integral.identityaccess.domain.model.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAccessService")
public class UserAccessService {

	private UserRepository userRepository;

	public Set<Permission> getUserPermissions(String userName) {

		User user = userRepository.findByUsername(userName);

		Permission permission = new Permission();

		Set<Permission> permissions = new HashSet<Permission>();

		permissions.add(permission);

		return permissions;

	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}

package my.com.myriadeas.integral.identityaccess.infrastrcuture.jpa;

import my.com.myriadeas.integral.identityaccess.domain.model.Group;
import my.com.myriadeas.integral.identityaccess.domain.model.GroupRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepositoryImpl extends JpaRepository<Group, Long>,
		GroupRepository {

}

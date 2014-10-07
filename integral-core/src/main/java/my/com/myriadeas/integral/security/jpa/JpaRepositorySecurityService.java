package my.com.myriadeas.integral.security.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public interface JpaRepositorySecurityService {

	public Map<String, List<ConfigAttribute>> getMethodMap();

	public void setJpaRepositoryService(
			JpaRepositoryService jpaRepositoryService);
}

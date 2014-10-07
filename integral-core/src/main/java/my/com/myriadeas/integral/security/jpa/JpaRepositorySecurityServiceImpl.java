package my.com.myriadeas.integral.security.jpa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

public class JpaRepositorySecurityServiceImpl implements
		JpaRepositorySecurityService {

	private static final String PERM_PREFIX = "PERM_";

	@Autowired
	private JpaRepositoryService jpaRepositoryService;

	@Override
	public Map<String, List<ConfigAttribute>> getMethodMap() {
		Map<String, List<ConfigAttribute>> methodMap = new LinkedHashMap<String, List<ConfigAttribute>>();
		for (RepositoryInformation repoInfo : jpaRepositoryService
				.getRepositoryInformations()) {
			methodMap.put(repoInfo.getRepositoryInterface().getCanonicalName() + ".find*",
					getConfigAttribute("VIEW", repoInfo));
			methodMap.put(repoInfo.getRepositoryInterface().getCanonicalName() + ".*",
					getConfigAttribute("EDIT", repoInfo));
		}
		return methodMap;
	}

	protected List<ConfigAttribute> getConfigAttribute(String action,
			RepositoryInformation repoInfo) {
		String permission = PERM_PREFIX + action + "_"
				+ repoInfo.getDomainType().getSimpleName().toUpperCase();
		ConfigAttribute configAttribute = new SecurityConfig(permission);
		List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
		configAttributes.add(configAttribute);
		return configAttributes;
	}

	@Override
	public void setJpaRepositoryService(
			JpaRepositoryService jpaRepositoryService) {
		this.jpaRepositoryService = jpaRepositoryService;
	}

}

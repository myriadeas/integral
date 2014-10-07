package my.com.myriadeas.integral.security.jpa;

import java.util.List;

import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.core.RepositoryInformation;

public interface JpaRepositoryService extends ApplicationContextAware {

	public void setIncludeClasses(Class<?>... classes);

	public void setExcludeClasses(Class<?>... classes);

	public List<RepositoryInformation> getRepositoryInformations();
}

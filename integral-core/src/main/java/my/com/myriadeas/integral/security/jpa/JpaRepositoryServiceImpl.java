package my.com.myriadeas.integral.security.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.support.Repositories;

public class JpaRepositoryServiceImpl implements JpaRepositoryService {

	private Repositories repositories;

	private List<Class<?>> excludeClasses;

	private List<Class<?>> includeClasses;

	private List<RepositoryInformation> repositoryInformations;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		repositories = new Repositories(applicationContext);

	}
	
	public List<RepositoryInformation> getRepositoryInformations() {
		if (repositoryInformations == null) {
			populateJpaClasses();
		}
		return repositoryInformations;
	}

	protected void populateJpaClasses() {
		if (repositoryInformations == null) {
			repositoryInformations = new ArrayList<RepositoryInformation>();
		}
		for (Class<?> clazz : repositories) {
			RepositoryInformation repoInfo = repositories
					.getRepositoryInformationFor(clazz);
			if (includeClasses != null) {
				if (includeClasses.contains(repoInfo.getDomainType())) {
					repositoryInformations.add(repoInfo);
				}
			} else if (excludeClasses != null) {
				if (!excludeClasses.contains(repoInfo.getDomainType())) {
					repositoryInformations.add(repoInfo);
				}
			} else {
				repositoryInformations.add(repoInfo);
			}
		}
	}

	@Override
	public void setIncludeClasses(Class<?>... classes) {
		if (includeClasses == null) {
			includeClasses = new ArrayList<Class<?>>();
		}
		includeClasses.addAll(Arrays.asList(classes));
	}

	@Override
	public void setExcludeClasses(Class<?>... classes) {
		if (excludeClasses == null) {
			excludeClasses = new ArrayList<Class<?>>();
		}
		excludeClasses.addAll(Arrays.asList(classes));
	}
}

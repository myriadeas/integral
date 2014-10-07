package my.com.myriadeas.dao.rest.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

public class IntegralRepositoryRestConfiguration extends
		org.springframework.data.rest.core.config.RepositoryRestConfiguration {

	private List<Class<?>> exposeIdsFor = new ArrayList<Class<?>>();

	private boolean isExposeIdsForAll = false;

	/**
	 * Should we expose the ID property for this domain type?
	 * 
	 * @param domainType
	 *            The domain type we may need to expose the ID for.
	 * 
	 * @return {@literal true} is the ID is to be exposed, {@literal false}
	 *         otherwise.
	 */
	public boolean isIdExposedFor(Class<?> domainType) {
		return isExposeIdsForAll || exposeIdsFor.contains(domainType);
	}

	/**
	 * Set the list of domain types for which we will expose the ID value as a
	 * normal property.
	 * 
	 * @param domainTypes
	 *            Array of types to expose IDs for.
	 * 
	 * @return {@literal this}
	 */
	public RepositoryRestConfiguration exposeIdsFor(Class<?>... domainTypes) {
		Collections.addAll(exposeIdsFor, domainTypes);
		return this;
	}

	public boolean isExposeIdsForAll() {
		return isExposeIdsForAll;
	}

	public void setExposeIdsForAll(boolean isExposeIdsForAll) {
		this.isExposeIdsForAll = isExposeIdsForAll;
	}

}

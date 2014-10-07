package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.skyscreamer.yoga.annotations.Core;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * http://docs.spring.io/spring-data/jpa/docs/1.4.2.RELEASE/reference/html/jpa.
 * repositories.html https://github.com/spring-projects/spring-data-envers
 * http://docs.jboss.org/envers/docs/
 * https://github.com/hantsy/spring-sandbox/wiki/jpa-revision
 * 
 * @author hutingung
 * 
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class AbstractDomain extends AbstractAuditable<Officer, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1848181401341093843L;

	@Version
	@Access(AccessType.FIELD)
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this);
	}
	
	@Transient
	@Core
	//TODO - JSON-YOGA View requirement. JSON YOGA cannot resolve id, added PK 
	// as json yoga view.
	public Long getPK() {
		return this.getId();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}

		if (null == obj) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (!getClass().equals(obj.getClass())) {
			return false;
		}

		AbstractPersistable<?> that = (AbstractPersistable<?>) obj;

		return null == this.getId() || null == that.getId() ? true : this
				.getId().equals(that.getId());
	}

}

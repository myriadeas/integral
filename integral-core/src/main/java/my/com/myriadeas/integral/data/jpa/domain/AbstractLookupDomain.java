package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public abstract class AbstractLookupDomain extends AbstractDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@NotBlank
	@Column(unique = true)
	@Length(max = 40)
	private String code;
	
	@Length(max = 80)
	private String description;
	
	public AbstractLookupDomain() {
		super();
	}
	

	public AbstractLookupDomain(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractLookupDomain other = (AbstractLookupDomain) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AbstractLookupDomain [code=" + code + ", description="
				+ description + "]";
	}
	
	
	
}

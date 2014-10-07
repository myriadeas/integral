package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.MappedSuperclass;

import my.com.myriadeas.integral.eligibility.EligibilityCriteria;

@MappedSuperclass
public abstract class AbstractCategoryDomain extends AbstractLookupDomain
		implements EligibilityCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractCategoryDomain() {
		super();
	}

	public AbstractCategoryDomain(String code, String description) {
		super(code, description);
	}

	public abstract String getCriteriaCode();

	public String getCriteriaValue() {
		return "-" + this.getCriteriaCode() + " " + this.getCode();
	}
}

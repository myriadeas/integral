package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

@Entity
public class PatronCategory extends AbstractCategoryDomain {

	private static final long serialVersionUID = 1L;
	
	public PatronCategory() {
	}

	public PatronCategory(String code, String description) {
		super(code, description);
	}

	@Override
	public String getCriteriaCode() {
		return PATRON_CATEGORY;
	}
}
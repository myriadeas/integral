package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLSMD database table.
 * 
 */
@Entity
public class SMD extends AbstractCategoryDomain {

	private static final long serialVersionUID = 1L;
	
	public SMD() {
	}

	public SMD(String code, String description) {
		super(code, description);
	}

	@Override
	public String getCriteriaCode() {
		return SMD;
	}	
}
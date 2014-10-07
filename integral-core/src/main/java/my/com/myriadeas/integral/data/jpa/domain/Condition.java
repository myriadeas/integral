package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the GLCOND database table.
 * 
 */
@Entity
@Table(name = "condition_")
public class Condition extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	
	
	public Condition() {
	}
	
	public Condition(String code, String description) {
		super(code, description);
	}

	
}
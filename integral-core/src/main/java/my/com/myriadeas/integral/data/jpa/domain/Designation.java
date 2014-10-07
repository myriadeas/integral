package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLDESG database table.
 * 
 */
@Entity
public class Designation extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;
	public Designation() {

	}

	public Designation(String code, String description) {
		super(code, description);
	}
}
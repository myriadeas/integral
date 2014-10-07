package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLRELIG database table.
 * 
 */
@Entity
public class Religion extends AbstractLookupDomain {

	private static final long serialVersionUID = 1L;

	public Religion() {
	}

	public Religion(String code, String description) {
		super(code, description);
	}
}
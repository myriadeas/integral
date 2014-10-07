package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the Race database table.
 * 
 */
@Entity
public class Race extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;
	public Race() {
	}

	public Race(String code, String description) {
		super(code, description);
	}
	
	
}
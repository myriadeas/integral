package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLTITLE database table.
 * 
 */
@Entity
public class Title extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	public Title() {
		// TODO Auto-generated constructor stub
	}

	public Title(String code, String description) {
		super(code, description);
	}

}
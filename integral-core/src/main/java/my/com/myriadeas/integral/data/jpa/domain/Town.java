package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the GLTOWN database table.
 * 
 */
@Entity
@Configurable
public class Town extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;
	
	public Town() {
	}

	public Town(String code, String description) {
		super(code, description);
	}

	
		
}
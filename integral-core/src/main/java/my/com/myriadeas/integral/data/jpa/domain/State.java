package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the GLState database table.
 * 
 */
@Entity
@Configurable
public class State extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;
	
	public State() {
	}

	public State(String code, String description) {
		super(code, description);
	}

	
		
}
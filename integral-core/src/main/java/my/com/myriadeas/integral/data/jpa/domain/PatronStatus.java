package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;

/**
 * The persistent class for the GLSTAT database table.
 * 
 */
@Entity
public class PatronStatus extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	private String action1;

	private String action2;

	
	public PatronStatus() {
	}


	public String getAction1() {
		return action1;
	}


	public void setAction1(String action1) {
		this.action1 = action1;
	}


	public String getAction2() {
		return action2;
	}


	public void setAction2(String action2) {
		this.action2 = action2;
	}
	
	


}
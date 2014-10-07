package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

/**
 * The persistent class for the GLBRNC database table.
 * 
 */
@Entity

public class Branch extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	
	@Length(max = 100)
	private String address1;

	@Length(max = 100)
	private String address2;

	@Length(max = 100)
	private String address3;

	@Length(max = 5)
	private String postcode;

	@ManyToOne(optional = true)
	private Town town;

	public Branch() {
	}
	
	public Branch(String code, String description) {
		super(code, description);
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String addresss1) {
		this.address1 = addresss1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	
		

}
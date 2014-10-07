package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

/**
 * The persistent class for the GLDEPT database table.
 * 
 */
@Entity
public class Department extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	@Length(max = 100)
	private String address1;

	@Length(max = 100)
	private String address2;

	@Length(max = 100)
	private String address3;

	@Length(max = 14)
	private String fax;

	@ManyToOne(optional = true)
	private Officer departmentHead;

	
	@Length(max = 5)
	private String postcode;

	@Length(max = 14)
	private String telephone;

	@ManyToOne(optional = true)
	private Town town;

	public Department() {

	}

	public Department(String code, String description) {
		super(code, description);
	}

	public Department(String code, String description, Officer headOfDepartment) {
		super(code, description);
		this.departmentHead = headOfDepartment;
	}
	

	public Department(String code, String description, Officer departmentHead, Town town) {
		this(code, description, departmentHead);
		this.town = town;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Officer getDepartmentHead() {
		return departmentHead;
	}

	public void setDepartmentHead(Officer departmentHead) {
		this.departmentHead = departmentHead;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	

}
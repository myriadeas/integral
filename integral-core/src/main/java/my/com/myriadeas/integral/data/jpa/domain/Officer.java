package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class Officer extends AbstractUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = true)
	private Department department;

	public Officer() {
	}

	public Officer(String username, String password, String firstname,
			Branch branch) {
		super(username, password, firstname, branch);

	}

	public void checkout(String patronId, String itemId, Date transactionDate) {
		Item item = new Item();
		Officer officer = new Officer();
		Patron borrower = new Patron();
		item.checkOut(officer, borrower, transactionDate);
	}

	public void renew(String itemId) {

	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}

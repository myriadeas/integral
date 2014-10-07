package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the GLLOCA database table.
 * 
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "code",
		"branch_id" }))
@Entity
@AttributeOverride(name = "code", column = @Column(name = "code", unique = false))
public class Location extends AbstractLookupDomain {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@NotNull
	private Branch branch;

	public Location() {
	}

	public Location(String code, String description, Branch branch) {
		super(code, description);

		this.branch = branch;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}
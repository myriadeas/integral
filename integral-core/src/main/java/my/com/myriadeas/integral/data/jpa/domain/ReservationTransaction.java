package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the CIRESV database table.
 * 
 */
/*
 * @Table(uniqueConstraints = @UniqueConstraint(columnNames = {
 * "ci03patr_gl14id", "ci03matno_ct02id" }))
 */
@Entity
@Configurable
public class ReservationTransaction extends AbstractDomain {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	@NotNull
	private Patron patron;

	@ManyToOne(optional = true)
	private Material material;

	@ManyToOne(optional = true)
	private Branch branch;

	
	private Date scruitinyDateTime;

	@ManyToOne(optional = true)
	private Item item;

	@ManyToOne(optional = true)
	private ItemCategory itemCategory;

	private Date onReserveForPatronDate;

	@NotBlank
	private String status;

	@ManyToOne(optional = true)
	private Officer officer;

	@NotNull
	private Integer priorityWeight;

	private Date reserveDateTime;
	
	@Transient
	private ReservationTransactionRepositoryImpl reservationTransactionRepo;

	public ReservationTransaction() {
	}

	public ReservationTransaction(String status, Patron patron, Material material, Integer priorityWeight) {
		super();
		this.status = status;
		this.patron = patron;
		this.material = material;
		this.priorityWeight = priorityWeight;
	}
	
	public ReservationTransaction(String status, Patron patron, Material material, Integer priorityWeight, Branch branch) {
		super();
		this.status = status;
		this.patron = patron;
		this.material = material;
		this.priorityWeight = priorityWeight;
		this.branch = branch;
	}
	
	
	@Autowired
	public void setReservationTransactionRepo(
			ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Date getScruitinyDateTime() {
		return scruitinyDateTime;
	}

	public void setScruitinyDateTime(Date scruitinyDateTime) {
		this.scruitinyDateTime = scruitinyDateTime;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemCategory getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Date getOnReserveForPatronDate() {
		return onReserveForPatronDate;
	}

	public void setOnReserveForPatronDate(Date onReserveForPatronDate) {
		this.onReserveForPatronDate = onReserveForPatronDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public Integer getPriorityWeight() {
		return priorityWeight;
	}

	public void setPriorityWeight(Integer priorityWeight) {
		if (priorityWeight == null) {
			priorityWeight = new Integer("0");
		}
		this.priorityWeight = priorityWeight;
	}

	public Date getReserveDateTime() {
		return reserveDateTime;
	}

	public void setReserveDateTime(Date reserveDateTime) {
		this.reserveDateTime = reserveDateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((officer == null) ? 0 : officer.hashCode());
		result = prime
				* result
				+ ((onReserveForPatronDate == null) ? 0
						: onReserveForPatronDate.hashCode());
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
		result = prime * result
				+ ((priorityWeight == null) ? 0 : priorityWeight.hashCode());
		result = prime * result
				+ ((reserveDateTime == null) ? 0 : reserveDateTime.hashCode());
		result = prime
				* result
				+ ((scruitinyDateTime == null) ? 0 : scruitinyDateTime
						.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationTransaction other = (ReservationTransaction) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (itemCategory == null) {
			if (other.itemCategory != null)
				return false;
		} else if (!itemCategory.equals(other.itemCategory))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (officer == null) {
			if (other.officer != null)
				return false;
		} else if (!officer.equals(other.officer))
			return false;
		if (onReserveForPatronDate == null) {
			if (other.onReserveForPatronDate != null)
				return false;
		} else if (!onReserveForPatronDate.equals(other.onReserveForPatronDate))
			return false;
		if (patron == null) {
			if (other.patron != null)
				return false;
		} else if (!patron.equals(other.patron))
			return false;
		if (priorityWeight == null) {
			if (other.priorityWeight != null)
				return false;
		} else if (!priorityWeight.equals(other.priorityWeight))
			return false;
		if (reserveDateTime == null) {
			if (other.reserveDateTime != null)
				return false;
		} else if (!reserveDateTime.equals(other.reserveDateTime))
			return false;
		if (scruitinyDateTime == null) {
			if (other.scruitinyDateTime != null)
				return false;
		} else if (!scruitinyDateTime.equals(other.scruitinyDateTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservationTransaction [patron=" + patron + ", material="
				+ material + ", branch=" + branch + ", scruitinyDateTime="
				+ scruitinyDateTime + ", item=" + item + ", itemCategory="
				+ itemCategory + ", onReserveForPatronDate="
				+ onReserveForPatronDate + ", status=" + status + ", officer="
				+ officer + ", priorityWeight=" + priorityWeight
				+ ", reserveDateTime=" + reserveDateTime + "]";
	}

	

}
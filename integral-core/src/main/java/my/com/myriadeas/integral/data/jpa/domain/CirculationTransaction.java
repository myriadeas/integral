package my.com.myriadeas.integral.data.jpa.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.FinesTransactionRepositoryImpl;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the CICIRC database table.
 * 
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"item_id", "patron_id", "chargeDateTime" }))
@Entity
@Configurable
public class CirculationTransaction extends AbstractDomain {
	private static final Logger logger = LoggerFactory
			.getLogger(CirculationTransaction.class);
	
	@Transient
	private CirculationTransactionRepositoryImpl circulationTransactionRepo;
	
	@Transient
	private FinesTransactionRepositoryImpl finesTransactionRepo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5985496263725615929L;

	@ManyToOne(optional = false)
	@NotNull
	private Item item;

	@NotNull
	@ManyToOne(optional = false)
	private Patron patron;

	@NotNull
	private Date chargeDateTime;

	// @GeneratedValue(strategy=GenerationType.SEQUENCE,
	// generator="cicirc.ci02counter")
	// @SequenceGenerator(name="cicirc.ci02counter",
	// sequenceName="cicirc.ci02counter", initialValue=1, allocationSize=1)
	private Long counter;

	@NotNull
	private Date dueDateTime;

	private Date dischargeDateTime;

	@ManyToOne(optional = true)
	private Officer dischargeOfficer;

	@NotBlank
	private String flag;

	@ManyToOne(optional = true)
	private Officer chargeOfficer;

	private Integer noOfRenew = new Integer("0");
	
	@OneToOne(optional = true)
	private CirculationTransaction circulationTransactionParentForRenew;
		
	@Autowired
	public void setCirculationTransactionRepo(
			CirculationTransactionRepositoryImpl circulationTransactionRepo) {
		this.circulationTransactionRepo = circulationTransactionRepo;
	}
		
	@Autowired
	public void setFinesTransactionRepo(
			FinesTransactionRepositoryImpl finesTransactionRepo) {
		this.finesTransactionRepo = finesTransactionRepo;
	}



	public CirculationTransaction() {
	}

	public CirculationTransaction(Item item, Patron patron, String flag) {
		super();
		this.item = item;
		this.patron = patron;
		this.flag = flag;
		this.chargeDateTime = new Date();
		this.dueDateTime = new Date();//TODO-
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Date getChargeDateTime() {
		return chargeDateTime;
	}

	public void setChargeDateTime(Date chargeDateTime) {
		this.chargeDateTime = chargeDateTime;
	}

	
	public Long getCounter() {
		return getId();
		// return ci02counter;
	}

	public void setCounter(Long counter) {
		if (counter == null) {
			counter = getId();
		}
		this.counter = counter;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public Date getDischargeDateTime() {
		return dischargeDateTime;
	}

	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}

	public Officer getDischargeOfficer() {
		return dischargeOfficer;
	}

	public void setDischargeOfficer(Officer dischargeOfficer) {
		this.dischargeOfficer = dischargeOfficer;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Officer getChargeOfficer() {
		return chargeOfficer;
	}

	public void setChargeOfficer(Officer chargeOfficer) {
		this.chargeOfficer = chargeOfficer;
	}

	public Integer getNoOfRenew() {

		return noOfRenew;
	}

	public void setNoOfRenew(Integer noOfRenew) {
		if (noOfRenew == null) {
			noOfRenew = new Integer("0");
		}
		this.noOfRenew = noOfRenew;
	}


	public CirculationTransaction getCirculationTransactionParentForRenew() {
		return circulationTransactionParentForRenew;
	}

	public void setCirculationTransactionParentForRenew(CirculationTransaction circulationTransactionParentForRenew) {
		this.circulationTransactionParentForRenew = circulationTransactionParentForRenew;
	}
	
		
	public List<FinesTransaction> getFinesTransactions(){
		logger.debug("Entering getFinesTransactions()");
		List<FinesTransaction> finesTransactions = finesTransactionRepo
				.findByCirculationTransactionCounter(this.getCounter());
		logger.debug("Leaving getFinesTransactions(). finesTransactions={}",
				finesTransactions);
		return finesTransactions;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((chargeDateTime == null) ? 0 : chargeDateTime.hashCode());
		result = prime * result
				+ ((chargeOfficer == null) ? 0 : chargeOfficer.hashCode());
		result = prime
				* result
				+ ((circulationTransactionParentForRenew == null) ? 0
						: circulationTransactionParentForRenew.hashCode());
		result = prime * result + ((counter == null) ? 0 : counter.hashCode());
		result = prime
				* result
				+ ((dischargeDateTime == null) ? 0 : dischargeDateTime
						.hashCode());
		result = prime
				* result
				+ ((dischargeOfficer == null) ? 0 : dischargeOfficer.hashCode());
		result = prime * result
				+ ((dueDateTime == null) ? 0 : dueDateTime.hashCode());
		result = prime * result + ((flag == null) ? 0 : flag.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result
				+ ((noOfRenew == null) ? 0 : noOfRenew.hashCode());
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
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
		CirculationTransaction other = (CirculationTransaction) obj;
		if (chargeDateTime == null) {
			if (other.chargeDateTime != null)
				return false;
		} else if (!chargeDateTime.equals(other.chargeDateTime))
			return false;
		if (chargeOfficer == null) {
			if (other.chargeOfficer != null)
				return false;
		} else if (!chargeOfficer.equals(other.chargeOfficer))
			return false;
		if (circulationTransactionParentForRenew == null) {
			if (other.circulationTransactionParentForRenew != null)
				return false;
		} else if (!circulationTransactionParentForRenew
				.equals(other.circulationTransactionParentForRenew))
			return false;
		if (counter == null) {
			if (other.counter != null)
				return false;
		} else if (!counter.equals(other.counter))
			return false;
		if (dischargeDateTime == null) {
			if (other.dischargeDateTime != null)
				return false;
		} else if (!dischargeDateTime.equals(other.dischargeDateTime))
			return false;
		if (dischargeOfficer == null) {
			if (other.dischargeOfficer != null)
				return false;
		} else if (!dischargeOfficer.equals(other.dischargeOfficer))
			return false;
		if (dueDateTime == null) {
			if (other.dueDateTime != null)
				return false;
		} else if (!dueDateTime.equals(other.dueDateTime))
			return false;
		if (flag == null) {
			if (other.flag != null)
				return false;
		} else if (!flag.equals(other.flag))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (noOfRenew == null) {
			if (other.noOfRenew != null)
				return false;
		} else if (!noOfRenew.equals(other.noOfRenew))
			return false;
		if (patron == null) {
			if (other.patron != null)
				return false;
		} else if (!patron.equals(other.patron))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CirculationTransaction [item=" + item + ", patron=" + patron
				+ ", chargeDateTime=" + chargeDateTime + ", counter=" + counter
				+ ", dueDateTime=" + dueDateTime + ", dischargeDateTime="
				+ dischargeDateTime + ", dischargeOfficer=" + dischargeOfficer
				+ ", flag=" + flag + ", chargeOfficer=" + chargeOfficer
				+ ", noOfRenew=" + noOfRenew
				+ ", circulationTransactionParentForRenew="
				+ circulationTransactionParentForRenew + "]";
	}

	
	
}
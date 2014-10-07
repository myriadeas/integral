package my.com.myriadeas.integral.data.jpa.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class PatronEligibility extends AbstractEligibilityDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Boolean allowOverdue = new Boolean(true);

	@NotNull
	private Boolean allowReserve = new Boolean(true);

	@NotNull
	@Max(999)
	@Min(0)
	private Integer maxLoanAllowed = new Integer(0);

	@NotNull
	@Min(0)
	private BigDecimal finesLimit = new BigDecimal("0.00");

	@NotNull
	@Min(0)
	private BigDecimal maxFines = new BigDecimal("0.00");

	@Max(999)
	@Min(0)
	@NotNull
	private Integer maxReservationAllowed = new Integer(0);

	public PatronEligibility() {
	}

	public PatronEligibility(String code, String description) {
		super(code, description);
	}

	public Boolean isAllowOverdue() {
		return allowOverdue;
	}

	public void setAllowOverdue(Boolean allowOverdue) {
		this.allowOverdue = allowOverdue;
	}

	public Boolean isAllowReserve() {
		return allowReserve;
	}

	public void setAllowReserve(Boolean allowReserve) {
		this.allowReserve = allowReserve;
	}

	public Integer getMaxLoanAllowed() {
		return maxLoanAllowed;
	}

	public void setMaxLoanAllowed(Integer maxLoanAllowed) {
		this.maxLoanAllowed = maxLoanAllowed;
	}

	public BigDecimal getFinesLimit() {
		return finesLimit;
	}

	public void setFinesLimit(BigDecimal finesLimit) {
		this.finesLimit = finesLimit;
	}

	public BigDecimal getMaxFines() {
		return maxFines;
	}

	public void setMaxFines(BigDecimal maxFines) {
		this.maxFines = maxFines;
	}

	public Integer getMaxReservationAllowed() {
		return maxReservationAllowed;
	}

	public void setMaxReservationAllowed(Integer maxReservationAllowed) {
		this.maxReservationAllowed = maxReservationAllowed;
	}

	public List<PatronCategory> getPatronCategories() {
		return this.patronCategories;
	}

	protected void loadCriteriaDomain() {
		populatePatronCategories();
	}

	@PostLoad
	void postLoad() {
		loadCriteriaDomain();
	}
}

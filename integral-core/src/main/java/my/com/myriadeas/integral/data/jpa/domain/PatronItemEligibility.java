package my.com.myriadeas.integral.data.jpa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Configurable
public class PatronItemEligibility extends AbstractEligibilityDomain {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Boolean allowOverdue = new Boolean(false);

	@NotNull
	private Boolean allowReserve = new Boolean(true);

	@Max(999)
	@Min(0)
	@NotNull
	private Integer maxLoanAllowed = new Integer(0);

	@NotNull
	private Boolean includeFines = new Boolean(true);

	@Min(0)
	@NotNull
	private BigDecimal maxFines = new BigDecimal("0");

	@Max(999)
	@Min(0)
	@NotNull
	private Integer loanPeriod;

	@NotNull
	@Enumerated(EnumType.STRING)
	private LoanUnit loanUnit;

	@Max(999)
	@Min(0)
	@NotNull
	private Integer maxRenewAllowed = new Integer(0);

	@Max(999)
	@Min(0)
	@NotNull
	private Integer multiplyFinesOfLost = new Integer(0);

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Fine> fines = new ArrayList<Fine>();
	
	public PatronItemEligibility() {
	}

	public PatronItemEligibility(String code, String description,
			LoanUnit loanUnit, Integer loanPeriod) {
		super(code, description);
		this.loanUnit = loanUnit;
		this.loanPeriod = loanPeriod;
	}

	public Boolean isALlowOverdue() {
		return allowOverdue;
	}

	public void setAllowOverdue(Boolean allowOverdue) {
		if (allowOverdue == null) {
			allowOverdue = new Boolean(false);
		}
		this.allowOverdue = allowOverdue;
	}

	public Boolean isAllowReserve() {
		return allowReserve;
	}

	public void setAllowReserve(Boolean allowReserve) {
		if (allowReserve == null) {
			allowReserve = new Boolean(true);
		}
		this.allowReserve = allowReserve;
	}

	public Integer getMaxLoanAllowed() {
		return maxLoanAllowed;
	}

	public void setMaxLoanAllowed(Integer maxLoanAllowed) {
		this.maxLoanAllowed = maxLoanAllowed;
	}

	public Boolean isIncludeFines() {
		return includeFines;
	}

	public void setIncludeFines(Boolean includeFines) {
		if (includeFines == null) {
			includeFines = new Boolean(true);
		}
		this.includeFines = includeFines;
	}

	public BigDecimal getMaxFines() {
		return maxFines;
	}

	public void setMaxFines(BigDecimal maxFines) {
		this.maxFines = maxFines;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public LoanUnit getLoanUnit() {
		return loanUnit;
	}

	public void setLoanUnit(LoanUnit loanUnit) {
		this.loanUnit = loanUnit;
	}

	public Integer getMaxRenewAllowed() {
		return maxRenewAllowed;
	}

	public void setMaxRenewAllowed(Integer maxRenewAllowed) {
		this.maxRenewAllowed = maxRenewAllowed;
	}

	public Integer getMultiplyFinesOfLost() {
		return multiplyFinesOfLost;
	}

	public void setMultiplyFinesOfLost(Integer multiplyFinesOfLost) {
		this.multiplyFinesOfLost = multiplyFinesOfLost;
	}

	public List<Fine> getFines() {
		return fines;
	}

	// Always create a new fines if there is save
	public void setFines(List<Fine> fines) {
		if (fines == null) {
			this.fines = new ArrayList<Fine>();
		}
		Map<Long, Fine> finesMap = tempFinesMap();
		this.fines.clear();
		for (Fine fine : fines) {
			if (finesMap.containsKey(fine.getId())) {
				fine.setVersion(finesMap.get(fine.getId()).getVersion());
			}
			this.fines.add(fine);
		}
	}

	@JsonIgnore
	@Transient
	private Map<Long, Fine> tempFinesMap() {
		Map<Long, Fine> _fines = new HashMap<Long, Fine>();
		for (Fine fine : this.getFines()) {
			_fines.put(fine.getId(), fine);
		}
		return _fines;
	}

	public Boolean isDailyLoan() {
		return this.loanUnit.equals(LoanUnit.DAILY);
	}

	public Boolean isHourlyLoan() {
		return this.loanUnit.equals(LoanUnit.HOURLY);
	}

	public List<PatronCategory> getPatronCategories() {
		return this.patronCategories;
	}

	public List<ItemCategory> getItemCategories() {
		return this.itemCategories;
	}
	

	@PostLoad
	void postLoad() {
		loadCriteriaDomain();
	}

	@Override
	protected void loadCriteriaDomain() {
		this.populatePatronCategories();
		this.populateItemCategories();
		this.populateBranches();
		this.populateSMDs();
	}

}
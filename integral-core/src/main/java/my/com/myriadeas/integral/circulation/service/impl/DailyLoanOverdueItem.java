package my.com.myriadeas.integral.circulation.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.FinesTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.fines.EmptyFinesException;
import my.com.myriadeas.integral.fines.FinesCalculator;
import my.com.myriadeas.integral.holiday.LibraryCalendarService;
import net.objectlab.kit.datecalc.common.DateCalculator;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DailyLoanOverdueItem implements OverdueItem {
	private static final Logger logger = LoggerFactory
			.getLogger(DailyLoanOverdueItem.class);

	private LoanUnit loanUnit = LoanUnit.DAILY;
	private Date checkOutDateTime;
	private Date checkInDateTime;
	private Date dueDateTime;
	private Date creationDateTime;
	// private boolean includeHoliday;
	private Patron patron;
	private Item item;
	private Branch branch;
	private Officer checkInOfficer;
	private Long circulationTransactionCounter;
	private LibraryCalendarService<LocalDate> libraryCalendarService;
	private FinesCalculator finesCalculator;
	private FinesTransactionFactory finesTransactionFactory;
	

	public DailyLoanOverdueItem() {

	}

	public DailyLoanOverdueItem(Date checkOutDateTime, Date checkInDateTime,
			Date dueDateTime, Branch branch, Patron patron, Item item, 
			Officer checkInOfficer, Long circulationTransactionCounter) {
		Assert.notNull(checkOutDateTime);
		Assert.notNull(checkInDateTime);
		Assert.notNull(dueDateTime);
		Assert.notNull(branch);
		this.checkOutDateTime = checkOutDateTime;
		this.checkInDateTime = checkInDateTime;
		this.dueDateTime = dueDateTime;
		this.branch = branch;
		this.patron = patron;
		this.item = item;
		this.checkInOfficer = checkInOfficer;
		this.circulationTransactionCounter = circulationTransactionCounter;
		this.creationDateTime = new Date();
	}

	public void setLibraryCalendarService(
			LibraryCalendarService<LocalDate> libraryCalendarService) {
		this.libraryCalendarService = libraryCalendarService;
	}

	public void setFinesCalculator(FinesCalculator finesCalculator) {
		this.finesCalculator = finesCalculator;
	}
	
	
	public void setFinesTransactionFactory(
			FinesTransactionFactory finesTransactionFactory) {
		this.finesTransactionFactory = finesTransactionFactory;
	}

	public Date getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public Date getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(Date checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public Date getDueDateTime() {
		return dueDateTime;
	}

	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public Patron getPatron() {
		return patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
		
	public Officer getCheckInOfficer() {
		return checkInOfficer;
	}

	public void setCheckInOfficer(Officer checkInOfficer) {
		this.checkInOfficer = checkInOfficer;
	}

	public Long getCirculationTransactionCounter() {
		return circulationTransactionCounter;
	}

	public void setCirculationTransactionCounter(Long circulationTransactionCounter) {
		this.circulationTransactionCounter = circulationTransactionCounter;
	}


	public LoanUnit getLoanUnit() {
		return loanUnit;
	}

	@Override
	public int getLateBy() {
		logger.debug("Entering getLateBy");

		LocalDate localDueDate = new LocalDate(dueDateTime);
		LocalDate localCheckInDate = new LocalDate(checkInDateTime);
		int lateBy = 0;

		DateCalculator<LocalDate> nextBusinessDay = getDateCalculator()
				.moveByBusinessDays(1);

		if (localCheckInDate.isAfter(localDueDate)) {
			while (nextBusinessDay.getCurrentBusinessDate().isBefore(
					localCheckInDate)
					|| nextBusinessDay.getCurrentBusinessDate().isEqual(
							localCheckInDate)) {
				lateBy++;
				nextBusinessDay = nextBusinessDay.moveByBusinessDays(1);
			}
		}
		logger.debug("Leaving getLateBy(). lateBy={}", lateBy);
		return lateBy;
	}

	protected boolean isNonWorkingDay(LocalDate date) {
		DateCalculator<LocalDate> dateCalculator = getDateCalculator();
		return dateCalculator.isNonWorkingDay(date);
	}

	@Override
	public BigDecimal getFines() {
		BigDecimal finesAmount;
		try{
			finesAmount = finesCalculator.calculateFines(getLateBy());
		} catch (EmptyFinesException e){
			finesAmount = new BigDecimal(0);
		}
		return finesAmount;
	}
	
	@Override
	public FinesTransaction createFinesTransaction(){
		logger.debug("Entering createFinesTransaction()");

		Patron patron = this.getPatron();
		Item item = this.getItem();
		int lateBy = this.getLateBy();
		BigDecimal finesAmount = this.getFines();
			
		FinesTransaction finesTransaction = null;		
		if (finesAmount.compareTo(new BigDecimal(0)) == 1) {
			finesTransaction = finesTransactionFactory.createFinesTransaction(
					checkInDateTime, finesAmount, patron, item, 
					lateBy, checkInOfficer, circulationTransactionCounter);
		}

		logger.debug("Leaving createFinesTransaction(). finesTransaction={}",
				finesTransaction);
		return finesTransaction;
	}
	
	
	

	@Override
	public BigDecimal getBalance() {
		// TODO to implement
		return getFines();
	}

	protected DateCalculator<LocalDate> getDateCalculator() {
		LocalDate startDate;;
		if (checkInDateTime.before(dueDateTime)){
			startDate = new LocalDate(checkInDateTime);
		} else {
			startDate = new LocalDate(dueDateTime);
		}
		
		
		DateCalculator<LocalDate> dateCalculator = libraryCalendarService
				.getDateCalculator(branch, new LocalDate(startDate));
		return dateCalculator;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branch == null) ? 0 : branch.hashCode());
		result = prime * result
				+ ((checkInDateTime == null) ? 0 : checkInDateTime.hashCode());
		result = prime
				* result
				+ ((creationDateTime == null) ? 0 : creationDateTime.hashCode());
		result = prime * result
				+ ((dueDateTime == null) ? 0 : dueDateTime.hashCode());
		result = prime * result
				+ ((loanUnit == null) ? 0 : loanUnit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyLoanOverdueItem other = (DailyLoanOverdueItem) obj;
		if (branch == null) {
			if (other.branch != null)
				return false;
		} else if (!branch.equals(other.branch))
			return false;
		if (checkInDateTime == null) {
			if (other.checkInDateTime != null)
				return false;
		} else if (!checkInDateTime.equals(other.checkInDateTime))
			return false;
		if (creationDateTime == null) {
			if (other.creationDateTime != null)
				return false;
		} else if (!creationDateTime.equals(other.creationDateTime))
			return false;
		if (dueDateTime == null) {
			if (other.dueDateTime != null)
				return false;
		} else if (!dueDateTime.equals(other.dueDateTime))
			return false;
		if (loanUnit == null) {
			if (other.loanUnit != null)
				return false;
		} else if (!loanUnit.equals(other.loanUnit))
			return false;
		return true;
	}

}

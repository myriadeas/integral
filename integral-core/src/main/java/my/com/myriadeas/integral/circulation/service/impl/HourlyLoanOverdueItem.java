package my.com.myriadeas.integral.circulation.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.BusinessHour;
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

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class HourlyLoanOverdueItem implements OverdueItem {
	private static final Logger logger = LoggerFactory
			.getLogger(HourlyLoanOverdueItem.class);

	private LoanUnit loanUnit = LoanUnit.HOURLY;
	private Date checkOutDateTime;
	private Date checkInDateTime;
	private Date dueDateTime;
	private Date creationDateTime;
	private boolean includeClosing;
	private Branch branch;
	private Patron patron;
	private Item item;
	private Officer checkInOfficer;
	private Long circulationTransactionCounter;
	
	private LibraryCalendarService libraryCalendarService;
	private FinesCalculator finesCalculator;
	private FinesTransactionFactory finesTransactionFactory;

	public void setLibraryCalendarService(
			LibraryCalendarService libraryCalendarService) {
		this.libraryCalendarService = libraryCalendarService;
	}

	public void setFinesCalculator(FinesCalculator finesCalculator) {
		this.finesCalculator = finesCalculator;
	}
	
	public void setFinesTransactionFactory(
			FinesTransactionFactory finesTransactionFactory) {
		this.finesTransactionFactory = finesTransactionFactory;
	}

	public HourlyLoanOverdueItem() {

	}

	public HourlyLoanOverdueItem(Date checkOutDateTime, Date checkInDateTime,
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

	public boolean isIncludeClosing() {
		return includeClosing;
	}

	public void setIncludeClosing(boolean includeClosing) {
		this.includeClosing = includeClosing;
	}

	@Override
	public int getLateBy() {
		logger.debug("Entering getLateBy().");

		DateTime jodaDueDateTime = new DateTime(dueDateTime);
		DateTime jodaCheckInDateTime = new DateTime(checkInDateTime);
		int lateBy = 0;

		if (jodaCheckInDateTime.isAfter(jodaDueDateTime)) {
			lateBy = calculateLateBy();
			/*
			 * if (isOverdueOvernight()){ lateBy = calculateOvernightLateBy(); }
			 * else { lateBy = calculateSameDayLateBy(); }
			 */
		}

		logger.debug("Leaving getLateBy(). lateBy={}", lateBy);
		return lateBy;
	}

	protected int calculateLateBy() {
		logger.debug("Entering calculateOvernightLateBy().");

		DateTime jodaDueDateTime = new DateTime(getDueDateTime());
		DateTime jodaCheckInDateTime = new DateTime(getCheckInDateTime());
		int lateBy = 0;

		LocalDate tempLocalDate = jodaDueDateTime.toLocalDate();
		while (tempLocalDate.isBefore(jodaCheckInDateTime.toLocalDate())
				|| tempLocalDate.isEqual(jodaCheckInDateTime.toLocalDate())) {

			if (!isNonWorkingDay(tempLocalDate
					.toDateTime(new LocalTime(0, 0, 0)))) {
				if (tempLocalDate.isEqual(jodaCheckInDateTime.toLocalDate())) {
					lateBy = lateBy
							+ getTotalBusinessHourUntilGivenTime(jodaCheckInDateTime);
					break;
				} else {
					DateTime newDateTime = new DateTime(
							tempLocalDate.getYear(),
							tempLocalDate.getMonthOfYear(),
							tempLocalDate.getDayOfMonth(), 23, 59, 59);
					lateBy = lateBy
							+ getTotalBusinessHourUntilGivenTime(newDateTime);
					tempLocalDate = tempLocalDate.plusDays(1);
				}
			}

		}

		logger.debug("Leaving calculateOvernightLateBy(). lateBy={}", lateBy);
		return lateBy;

	}

	protected int getTotalBusinessHourUntilGivenTime(DateTime dateTime) {
		return getDateCalculator().getWeekBusinessHour()
				.getTotalBusinessHourUntilGivenTime(dateTime.toLocalDateTime());
	}

	/*
	 * protected int calculateSameDayLateBy() {
	 * logger.debug("Entering calculateSameDayLateBy()."); DateTime
	 * jodaCheckInDateTime = new DateTime(getCheckInDateTime()); int lateBy =
	 * getTotalBusinessHourUntilGivenTime(jodaCheckInDateTime);
	 * //List<BusinessHour> businessHours =
	 * getBusinessHours(jodaCheckInDateTime); //int lateBy =
	 * calculateLateBy(businessHours, jodaCheckInDateTime);
	 * logger.debug("Leaving calculateSameDayLateBy(). lateBy={}", lateBy);
	 * return lateBy; }
	 */

	protected List<BusinessHour> getBusinessHours(DateTime dateTime) {
		return getDateCalculator().getWeekBusinessHour().getBusinessHours(
				dateTime.toLocalDateTime());
	}

	protected int calculateLateByUsingSlots(List<LocalTime[]> slots,
			LocalTime time) {
		logger.debug("Entering calculateLateByUsingSlots().");
		int lateBy = 0;
		DateTime now = new DateTime();
		DateTime dateTime = new DateTime(now.getYear(), now.getMonthOfYear(),
				now.getDayOfMonth(), time.getHourOfDay(),
				time.getMinuteOfHour(), time.getSecondOfMinute());

		for (LocalTime[] slot : slots) {
			DateTime slot0DateTime = new DateTime(now.getYear(),
					now.getMonthOfYear(), now.getDayOfMonth(),
					slot[0].getHourOfDay(), slot[0].getMinuteOfHour(),
					slot[0].getSecondOfMinute());
			DateTime slot1DateTime = new DateTime(now.getYear(),
					now.getMonthOfYear(), now.getDayOfMonth(),
					slot[1].getHourOfDay(), slot[1].getMinuteOfHour(),
					slot[1].getSecondOfMinute());

			Interval interval = new Interval(slot0DateTime, slot1DateTime);
			if (interval.contains(dateTime)) {
				lateBy = lateBy
						+ Minutes.minutesBetween(slot[0], time).getMinutes();
			} else if (dateTime.isEqual(slot1DateTime)
					|| dateTime.isAfter(slot1DateTime)) {
				lateBy = lateBy
						+ Minutes.minutesBetween(slot[0], slot[1]).getMinutes();
			}
		}
		logger.debug("Leaving calculateLateByUsingSlots(). lateBy={}", lateBy);
		return lateBy;
	}

	/*
	 * protected int calculateLateBy(List<BusinessHour> businessHours, DateTime
	 * dateTime){ logger.debug("Entering calculateLateByUsingBusinessHours().");
	 * int lateBy = 0;
	 * 
	 * for (BusinessHour businessHour: businessHours){ DateTime startDateTime =
	 * new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(),
	 * dateTime.getDayOfMonth(), businessHour.getStart().getHourOfDay(),
	 * businessHour.getStart().getMinuteOfHour(),
	 * businessHour.getStart().getSecondOfMinute()); DateTime endDateTime = new
	 * DateTime(dateTime.getYear(), dateTime.getMonthOfYear(),
	 * dateTime.getDayOfMonth(), businessHour.getEnd().getHourOfDay(),
	 * businessHour.getEnd().getMinuteOfHour(),
	 * businessHour.getEnd().getSecondOfMinute());
	 * 
	 * Interval interval = new Interval(startDateTime, endDateTime);
	 * if(interval.contains(dateTime)) { lateBy = lateBy +
	 * Minutes.minutesBetween(businessHour.getStart(), new
	 * LocalTime(dateTime)).getMinutes(); } else if
	 * (dateTime.isEqual(endDateTime) || dateTime.isAfter(endDateTime)) { lateBy
	 * = lateBy + Minutes.minutesBetween(businessHour.getStart(),
	 * businessHour.getEnd()).getMinutes(); } }
	 * logger.debug("Leaving calculateLateByUsingBusinessHours(). lateBy={}",
	 * lateBy); return lateBy; }
	 * 
	 * 
	 * protected boolean isOverdueOvernight(){ LocalDate localDueDateTime = new
	 * LocalDate (getDueDateTime()); LocalDate localCheckInDateTime = new
	 * LocalDate (getCheckInDateTime());
	 * 
	 * return localCheckInDateTime.isAfter(localDueDateTime); }
	 */

	protected boolean isNonWorkingDay(DateTime date) {
		DateCalculator<LocalDate> dateCalculator = getDateCalculator();
		LocalDate localDate = new LocalDate(date);
		return dateCalculator.isNonWorkingDay(localDate);
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
	public BigDecimal getBalance() {
		// TODO to implement
		return getFines();
	}

	protected BusinessCalendarCalculator getDateCalculator() {
		LocalDate startDate;;
		if (checkInDateTime.before(dueDateTime)){
			startDate = new LocalDate(checkInDateTime);
		} else {
			startDate = new LocalDate(dueDateTime);
		}
		return libraryCalendarService.getDateCalculator(branch, startDate);

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
		result = prime * result + (includeClosing ? 1231 : 1237);
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
		HourlyLoanOverdueItem other = (HourlyLoanOverdueItem) obj;
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
		if (includeClosing != other.includeClosing)
			return false;
		if (loanUnit == null) {
			if (other.loanUnit != null)
				return false;
		} else if (!loanUnit.equals(other.loanUnit))
			return false;
		return true;
	}

	@Override
	public FinesTransaction createFinesTransaction() {
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

}

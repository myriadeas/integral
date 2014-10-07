package my.com.myriadeas.integral.circulation.service.impl;

import java.util.Date;
import java.util.List;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.BusinessHour;
import my.com.myriadeas.WeekBusinessHour;
import my.com.myriadeas.integral.circulation.exception.UnknownLoanTypeException;
import my.com.myriadeas.integral.circulation.service.DueDateCalculationService;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;
import my.com.myriadeas.integral.holiday.LibraryCalendarService;
import net.objectlab.kit.datecalc.common.DateCalculator;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("dueDateCalculationService")
public class DueDateCalculationServiceImpl implements DueDateCalculationService {
	private static final Logger logger = LoggerFactory
			.getLogger(DueDateCalculationServiceImpl.class);

	private PatronItemEligibilityLookup patronItemEligibilityLookup;

	private LibraryCalendarService libraryCalendarService;

	@Autowired
	public void setLibraryCalendarService(
			LibraryCalendarService libraryCalendarService) {
		this.libraryCalendarService = libraryCalendarService;
	}

	protected Date calculateDueDateTime(Date checkOutDateTime,
			PatronItemEligibility patronItemEligibility, Branch branch) {
		logger.debug(
				"Entering calculateDueDateTime(checkOutDateTime={}, patronItemEligibility={})",
				new Object[] { checkOutDateTime, patronItemEligibility });
		Assert.notNull(checkOutDateTime);
		Assert.notNull(patronItemEligibility);

		if (patronItemEligibility.isDailyLoan()) {
			Date dueDateTime = calculateDueDateTimeForDailyLoanExcludeHoliday(
					checkOutDateTime, patronItemEligibility, branch);
			logger.debug("Leaving calculateDueDateTime(). dueDateTime={}",
					dueDateTime);
			return dueDateTime;
		}
		if (patronItemEligibility.isHourlyLoan()) {
			Date dueDateTime = calculateDueDateTimeForHourlyLoan(
					checkOutDateTime, patronItemEligibility, branch);
			logger.debug("Leaving calculateDueDateTime(). dueDateTime={}",
					dueDateTime);
			return dueDateTime;
		}
		String msg = "Unknown loan type. Unable to calculate due date time.";
		logger.error(msg);
		throw new UnknownLoanTypeException(msg);

	}

	/*
	 * protected Date calculateDueDateTimeForDailyLoan(Date checkOutDateTime,
	 * Glpatritemelig patronItemEligibility) { logger.debug(
	 * "Entering calculateDueDateTimeForDailyLoan(checkOutDateTime={}, patronItemEligibility={})"
	 * , checkOutDateTime, patronItemEligibility);
	 * Assert.notNull(checkOutDateTime); Assert.notNull(patronItemEligibility);
	 * 
	 * DateTime jCheckOutDateTime = new DateTime(checkOutDateTime); DateTime
	 * dueDateTime = new DateTime(); int loanPeriod =
	 * patronItemEligibility.getGl27ploan(); dueDateTime =
	 * jCheckOutDateTime.plusDays(loanPeriod);
	 * 
	 * logger.debug(
	 * "Leaving calculateDueDateTimeForDailyLoan(). dueDateTime={}",
	 * dueDateTime.toDate()); return dueDateTime.toDate(); }
	 */

	/*
	 * public DateTime calculateDueDateTimeForDailyLoan(DateTime
	 * checkOutDateTime, Glpatritemelig patronItemEligibility, Glpatr patron,
	 * Ctdocm item, Gllibr gllibr){ DateTime dueDateTime = new DateTime(); int
	 * loanPeriod = patronItemEligibility.getGl27ploan();
	 * 
	 * dueDateTime = checkOutDateTime.plusDays(loanPeriod); if
	 * (gllibr.isIncludeHoliday()) { String sTempCurrent =
	 * utility.formatToyyyyMMdd(cdtCurrent); do { int iHolidayCount =
	 * daoServiceFactory.getHolidayDaoService().getNoOfHoliday(sTempCurrent,
	 * sTmpDueDate, location);
	 * 
	 * if ((iHolidayCount > 0)) { sTempCurrent =
	 * utility.formatToyyyyMMdd(utility.dateAdd(CalendarDateTime.DATE, 1,
	 * CalendarDateTime.fromString(sTmpDueDate))); sTmpDueDate =
	 * utility.formatToyyyyMMdd(utility.dateAdd(CalendarDateTime.DATE,
	 * iHolidayCount, CalendarDateTime.fromString(sTmpDueDate)));
	 * 
	 * } else { break; } } while(true);
	 * 
	 * } else if (gllibr.isExcludeHoliday()) { sTmpDueDate =
	 * utility.formatToyyyyMMdd(cdtDue); CalendarDateTime cdtTmpDueDate =
	 * skipHoliday(sTmpDueDate, location); if (cdtTmpDueDate == null) { throw
	 * new DueDateCalculationException(); } else { sTmpDueDate =
	 * utility.formatToyyyyMMdd(cdtTmpDueDate); } }
	 * 
	 * cdtDueDateTime = CalendarDateTime.fromString(sTmpDueDate + sTmpDueTime);
	 * 
	 * }
	 */

	public Date calculateDueDateTimeForHourlyLoan(Date checkOutDateTime,
			PatronItemEligibility patronItemEligibility, Branch branch) {
		logger.debug(
				"Entering calculateDueDateTimeForHourlyLoan(checkOutDateTime={}, patronItemEligibility={})",
				checkOutDateTime, patronItemEligibility);

		DateTime jCheckOutDateTime = new DateTime(checkOutDateTime);
		DateTime dueDateTime = new DateTime();
		int loanPeriod = patronItemEligibility.getLoanPeriod();

		dueDateTime = jCheckOutDateTime.plusHours(loanPeriod);
		while (!isInBusinessHour(dueDateTime, branch)) {
			dueDateTime = dueDateTime.plusMinutes(1);
		}

		logger.debug(
				"Leaving calculateDueDateTimeForHourlyLoan(). dueDateTime={}",
				dueDateTime.toDate());
		return dueDateTime.toDate();
	}

	protected boolean isInBusinessHour(DateTime dateTime, Branch branch) {
		BusinessCalendarCalculator businessCalendarCalculator = getDateCalculator(branch, dateTime.toDate());
		WeekBusinessHour weekBusinessHour = businessCalendarCalculator.getWeekBusinessHour();
		/*
		System.out.println(businessCalendarCalculator.isNonWorkingDay(dateTime.toLocalDate()));
		List<BusinessHour> businessHours = weekBusinessHour.getBusinessHours(dateTime.toLocalDateTime());		
		for (BusinessHour businessHour: businessHours){
			System.out.println(businessHour.getStart() + "-" + businessHour.getEnd());
		}
		*/
		return weekBusinessHour.isInBusinessHour(dateTime.toLocalDateTime());
		/*
		return getDateCalculator(branch, dateTime.toDate())
				.getWeekBusinessHour().isInBusinessHour(
						dateTime.toLocalDateTime());
		*/		
	}
	

	@Autowired
	@Override
	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup) {
		this.patronItemEligibilityLookup = patronItemEligibilityLookup;
	}

	@Override
	public Date calculateDueDateTime(Item item, Patron patron,
			Date checkOutDateTime) {
		return calculateDueDateTime(checkOutDateTime,
				this.patronItemEligibilityLookup.lookup(patron, item)
						.getDomain(), item.getBranch());
	}

	protected Date calculateDueDateTimeForDailyLoanIncludeHoliday(
			Date checkOutDateTime, PatronItemEligibility patronItemEligibility,
			Branch branch) {
		logger.debug(
				"Entering calculateDueDateTimeForDailyLoanIncludeHoliday(checkOutDateTime={}, patronItemEligibility={}, glbrnc={})",
				new Object[] { checkOutDateTime, patronItemEligibility, branch });
		Assert.notNull(checkOutDateTime);
		Assert.notNull(patronItemEligibility);

		int loanPeriod = patronItemEligibility.getLoanPeriod();
		DateCalculator<LocalDate> dateCalculator = getDateCalculator(branch,
				checkOutDateTime);
				
		Date dueDateTime = dateCalculator.moveByBusinessDays(loanPeriod)
				.getCurrentBusinessDate().toDate();
		logger.debug(
				"Leaving calculateDueDateTimeForDailyLoanIncludeHoliday(). dueDateTime={}",
				dueDateTime);
		return dueDateTime;
	}

	protected Date calculateDueDateTimeForDailyLoanExcludeHoliday(
			Date checkOutDateTime, PatronItemEligibility patronItemEligibility,
			Branch branch) {
		logger.debug(
				"Entering calculateDueDateTimeForDailyLoanExcludeHoliday(checkOutDateTime={}, patronItemEligibility={}, glbrnc={})",
				new Object[] { checkOutDateTime, patronItemEligibility, branch });
		Assert.notNull(checkOutDateTime);
		Assert.notNull(patronItemEligibility);

		int loanPeriod = patronItemEligibility.getLoanPeriod();
		DateCalculator<LocalDate> dateCalculator = getDateCalculator(branch,
				checkOutDateTime);
		System.out.println(dateCalculator.isNonWorkingDay(new LocalDate(checkOutDateTime).plusDays(4)));
		Date dueDateTime = dateCalculator.moveByDays(loanPeriod)
				.getCurrentBusinessDate().toDate();
		logger.debug(
				"Leaving calculateDueDateTimeForDailyLoanExcludeHoliday(). dueDateTime={}",
				dueDateTime);
		return dueDateTime;
	}

	protected Date calculateDueDateTimeForHourlyLoanIncludeClosingHour(
			Date checkOutDateTime, PatronItemEligibility patronItemEligibility,
			Branch branch) {

		// TODO
		// will not implement because we will put a disclaimer saying you must
		// return on give due time
		// or before library closing hour depends on whichever come 1st

		return null;
	}

	/*
	 * protected Date calculateDueDateTimeIncludeHoliday(Date checkOutDateTime,
	 * int loanPeriod, Glbrnc branch){ DateCalculator<LocalDate> dateCalculator
	 * = getDateCalculator(branch, checkOutDateTime); Date dueDateTime =
	 * dateCalculator
	 * .moveByBusinessDays(loanPeriod).getCurrentBusinessDate().toDate(); return
	 * dueDateTime; }
	 * 
	 * protected Date calculateDueDateTimeExcludeHoliday(Date checkOutDateTime,
	 * int loanPeriod, Glbrnc branch){ DateCalculator<LocalDate> dateCalculator
	 * = getDateCalculator(branch, checkOutDateTime); Date dueDateTime =
	 * dateCalculator.moveByDays(loanPeriod).getCurrentBusinessDate().toDate();
	 * return dueDateTime; }
	 */
	protected BusinessCalendarCalculator getDateCalculator(Branch branch,
			Date date) {
		return libraryCalendarService.getDateCalculator(branch, new LocalDate(
				date));
	}
}

package my.com.myriadeas.integral.circulation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.BusinessHour;
import my.com.myriadeas.BusinessHourCalendarFactory;
import my.com.myriadeas.WeekBusinessHour;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;
import net.objectlab.kit.datecalc.joda.LocalDateCalculator;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class AbstractCirculationTest{

	private static final DateTimeFormatter STANDARD_DATE_FORMAT = DateTimeFormat
			.forPattern("dd/MM/yyyy");

	private static final DateTimeFormatter STANDARD_DATE_FORMAT_WITHTIME = DateTimeFormat
			.forPattern("dd/MM/yyyy HH:mm:ss");

	/**
	 * Return date with ddMMyyyy format. If format is wrong, return current date
	 * 
	 * @param input
	 *            format - dd/MM/yyyy
	 * @return
	 */
	public Date getDate(String input) {
		return STANDARD_DATE_FORMAT.parseDateTime(input).toDate();
	}

	/**
	 * 
	 * @param input
	 *            format = dd/MM/yyyy HH:mm:ss. example - 01/01/2001 01:01:01
	 * @return
	 */
	public Date getDatetime(String input) {
		return STANDARD_DATE_FORMAT_WITHTIME.parseDateTime(input).toDate();
	}
	
	
	public BusinessCalendarCalculator getDateCalculatorNoWeekendNoHolidayFrom9amTo5pm(){
		LocalDateCalculator localDateCalculator = BusinessHourCalendarFactory.getDefaultInstance()
				.getDateCalculator("UK", HolidayHandlerType.FORWARD);
		
		BusinessCalendarCalculator businessCalendarCalculator = new BusinessCalendarCalculator(localDateCalculator);
		businessCalendarCalculator.setName(localDateCalculator.getName());
		businessCalendarCalculator.setHolidayHandler(localDateCalculator
				.getHolidayHandler());
		
		
		JodaWorkingWeek workingWeek = new JodaWorkingWeek();
		boolean working = true;
		
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.MONDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.TUESDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.WEDNESDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.THURSDAY);		
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.FRIDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.SATURDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.SUNDAY);
		businessCalendarCalculator.setWorkingWeek(workingWeek);
		
		Set<LocalDate> holidays = new HashSet<LocalDate>();		
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		businessCalendarCalculator.setHolidayCalendar(holidayCalendar);
		
		BusinessHour businessHour = new BusinessHour(new LocalTime(9,0), new LocalTime(17,0));
		WeekBusinessHour weekBusinessHour = new WeekBusinessHour();		
		weekBusinessHour.addBusinessHour(DateTimeConstants.MONDAY, businessHour);
		weekBusinessHour.addBusinessHour(DateTimeConstants.TUESDAY, businessHour);
		weekBusinessHour.addBusinessHour(DateTimeConstants.WEDNESDAY, businessHour);
		weekBusinessHour.addBusinessHour(DateTimeConstants.THURSDAY, businessHour);
		weekBusinessHour.addBusinessHour(DateTimeConstants.FRIDAY, businessHour);
		weekBusinessHour.addBusinessHour(DateTimeConstants.SATURDAY, businessHour);		
		weekBusinessHour.addBusinessHour(DateTimeConstants.SUNDAY, businessHour);
		businessCalendarCalculator.setWeekBusinessHour(weekBusinessHour);
		
		return businessCalendarCalculator;
	}
}

package my.com.myriadeas.integral.circulation.service.impl;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.integral.circulation.AbstractCirculationTest;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.holiday.LibraryCalendarServiceImpl;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

import org.easymock.IMockBuilder;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class DailyLoanOverdueItemTest extends
	AbstractCirculationTest {
	
	private DateCalculator<LocalDate> mockDateCalculator;
			
	private DailyLoanOverdueItem dailyLoanOverdueItem;
	
	private LibraryCalendarServiceImpl libraryCalendarService = new LibraryCalendarServiceImpl();

	@Before
	public void setup(){		
		mockDateCalculator = getDateCalculatorNoWeekendNoHolidayFrom9amTo5pm();
		IMockBuilder<DailyLoanOverdueItem> dailyLoanOverdueItemMock = createMockBuilder(DailyLoanOverdueItem.class);
		dailyLoanOverdueItemMock.addMockedMethod("getDateCalculator");		
		this.dailyLoanOverdueItem = dailyLoanOverdueItemMock.createMock();
	}
	
	@Test
	public void testNoLateReturn() {
		Date dueDate = getDate("02/01/2001");
		Date checkInDate = getDate("01/01/2001");	
		Branch branch = new Branch();
		
		
		mockDateCalculator.setStartDate(new LocalDate(dueDate));
		expect(dailyLoanOverdueItem.getDateCalculator()).andReturn(mockDateCalculator).times(1);
		replay(dailyLoanOverdueItem);
				
		
		int expectedLateBy = 0;
		dailyLoanOverdueItem.setDueDateTime(dueDate);
		dailyLoanOverdueItem.setCheckInDateTime(checkInDate);
		dailyLoanOverdueItem.setBranch(branch);
		int returnedLateBy = dailyLoanOverdueItem.getLateBy();		
		assertEquals(expectedLateBy, returnedLateBy);				
	}
		
	
	@Test
	public void testOneDayLateReturn() {
		Date dueDate = getDate("01/01/2001");
		Date checkInDate = getDate("02/01/2001");	
		Branch branch = new Branch();
		
		mockDateCalculator.setStartDate(new LocalDate(dueDate));
		expect(dailyLoanOverdueItem.getDateCalculator()).andReturn(mockDateCalculator).times(1);
		replay(dailyLoanOverdueItem);
				
		
		int expectedLateBy = 1;
		dailyLoanOverdueItem.setDueDateTime(dueDate);
		dailyLoanOverdueItem.setCheckInDateTime(checkInDate);
		dailyLoanOverdueItem.setBranch(branch);
		int returnedLateBy = dailyLoanOverdueItem.getLateBy();		
		assertEquals(expectedLateBy, returnedLateBy);				
	}
	
	
	@Test
	public void testThreeDayLateReturnWithOneDayHolidayInBetween() {
		Date dueDate = getDate("01/01/2001");
		Date checkInDate = getDate("04/01/2001");	
		Branch branch = new Branch();
		
		Set<LocalDate> holidays = new HashSet();		
		holidays.add(new LocalDate(getDate("03/01/2001")));				
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		mockDateCalculator.setStartDate(new LocalDate(dueDate));		
		expect(dailyLoanOverdueItem.getDateCalculator()).andReturn(mockDateCalculator).times(1);
		replay(dailyLoanOverdueItem);
		
		int expectedLateBy = 2;
		dailyLoanOverdueItem.setDueDateTime(dueDate);
		dailyLoanOverdueItem.setCheckInDateTime(checkInDate);
		dailyLoanOverdueItem.setBranch(branch);
		int returnedLateBy = dailyLoanOverdueItem.getLateBy();		
		assertEquals(expectedLateBy, returnedLateBy);				
	}
	
	@Test
	public void testFiveDayLateReturnWithTwoDayHolidayInBetween() {
		Date dueDate = getDate("01/01/2001");
		Date checkInDate = getDate("06/01/2001");	
		Branch branch = new Branch();
		
		Set<LocalDate> holidays = new HashSet();		
		holidays.add(new LocalDate(getDate("02/01/2001")));
		holidays.add(new LocalDate(getDate("05/01/2001")));	
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		mockDateCalculator.setStartDate(new LocalDate(dueDate));
		expect(dailyLoanOverdueItem.getDateCalculator()).andReturn(mockDateCalculator).times(1);
		replay(dailyLoanOverdueItem);
		
		int expectedLateBy = 3;
		dailyLoanOverdueItem.setDueDateTime(dueDate);
		dailyLoanOverdueItem.setCheckInDateTime(checkInDate);
		dailyLoanOverdueItem.setBranch(branch);
		int returnedLateBy = dailyLoanOverdueItem.getLateBy();		
		assertEquals(expectedLateBy, returnedLateBy);				
	}
	
	@Test
	public void testThreeDayLateReturnWithOneDayHolidayInBetweenAndReturnDayIsHoliday() {
		Date dueDate = getDate("01/01/2001");
		Date checkInDate = getDate("04/01/2001");	
		Branch branch = new Branch();
		
		Set<LocalDate> holidays = new HashSet();		
		holidays.add(new LocalDate(getDate("03/01/2001")));
		holidays.add(new LocalDate(getDate("04/01/2001")));	
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		mockDateCalculator.setStartDate(new LocalDate(dueDate));
		expect(dailyLoanOverdueItem.getDateCalculator()).andReturn(mockDateCalculator).times(1);
		replay(dailyLoanOverdueItem);
		
		int expectedLateBy = 1;
		dailyLoanOverdueItem.setDueDateTime(dueDate);
		dailyLoanOverdueItem.setCheckInDateTime(checkInDate);
		dailyLoanOverdueItem.setBranch(branch);
		int returnedLateBy = dailyLoanOverdueItem.getLateBy();		
		assertEquals(expectedLateBy, returnedLateBy);				
	}

	@Test
	public void testDateCalculatorBehavior(){
		JodaWorkingWeek workingWeek = new JodaWorkingWeek();
		boolean working = true;
		boolean notWorking = false;
		
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.MONDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.TUESDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.WEDNESDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.THURSDAY);		
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.FRIDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.SATURDAY);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(working, DateTimeConstants.SUNDAY);
		mockDateCalculator.setWorkingWeek(workingWeek);
		
		Set<LocalDate> holidays = new HashSet();		
		//holidays.add(new LocalDate(getDate("01/01/2001")));
		holidays.add(new LocalDate(getDate("02/01/2001")));
		//holidays.add(new LocalDate(getDate("03/01/2001")));
		holidays.add(new LocalDate(getDate("06/01/2001")));
		//holidays.add(new LocalDate(getDate("08/01/2001")));
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		
		mockDateCalculator.setStartDate(new LocalDate(getDate("01/01/2001")));		
		System.out.println("moveByDays(5) = " + mockDateCalculator.moveByDays(5).getCurrentBusinessDate());
		System.out.println("isNonWorkingDay = " + mockDateCalculator.isNonWorkingDay(mockDateCalculator.moveByDays(5).getCurrentBusinessDate()));
		
		mockDateCalculator.setStartDate(new LocalDate(getDate("01/01/2001")));	
		System.out.println("moveByBusinessDays(5) = " + mockDateCalculator.moveByBusinessDays(5).getCurrentBusinessDate());
		//dateCalculator.moveByBusinessDays(businessDays)		
	}
	
	
}

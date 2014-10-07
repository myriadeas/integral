package my.com.myriadeas.integral.circulation.service.impl;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.integral.circulation.AbstractCirculationTest;
import my.com.myriadeas.integral.circulation.service.impl.DueDateCalculationServiceImpl;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.holiday.HolidayServiceImpl;
import my.com.myriadeas.integral.holiday.LibraryCalendarServiceImpl;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;

import org.easymock.IMockBuilder;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;


public class DueDateCalculationServiceTest extends AbstractCirculationTest  {
	private BusinessCalendarCalculator mockDateCalculator;
	
	private DueDateCalculationServiceImpl dueDateCalculationService = new DueDateCalculationServiceImpl();
	
	private LibraryCalendarServiceImpl libraryCalendarService = new LibraryCalendarServiceImpl();
	
	private HolidayServiceImpl holidayService = new HolidayServiceImpl();
	
	@Before
	public void setup(){
		mockDateCalculator = getDateCalculatorNoWeekendNoHolidayFrom9amTo5pm();
	}
	
	@Test
	public void testCalculateDueDateTimeForDailyLoanExcludeHoliday(){
		Date checkOutDateTime = getDate("01/01/2001");
		Branch branch = new Branch();
		PatronItemEligibility glpatritemelig = new PatronItemEligibility();
		glpatritemelig.setLoanPeriod(10);
		
		IMockBuilder<DueDateCalculationServiceImpl> dueDateCalculationServiceMock = createMockBuilder(DueDateCalculationServiceImpl.class);
		dueDateCalculationServiceMock.addMockedMethod("getDateCalculator");
		this.dueDateCalculationService = dueDateCalculationServiceMock.createMock();
		
		Set<LocalDate> holidays = new HashSet();				
		holidays.add(new LocalDate(getDate("11/01/2001")));
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		mockDateCalculator.setStartDate(new LocalDate(checkOutDateTime));
		expect(dueDateCalculationService.getDateCalculator(branch, checkOutDateTime)).andReturn(mockDateCalculator).times(1);
		replay(dueDateCalculationService);	
				
		Date expectedDueDateTime = getDate("12/01/2001");
		Date dueDateTime = dueDateCalculationService.calculateDueDateTimeForDailyLoanExcludeHoliday(checkOutDateTime, glpatritemelig, branch);
		assertEquals(expectedDueDateTime, dueDateTime);
	}
	
	@Test
	public void testCalculateDueDateTimeForDailyLoanIncludeHoliday() throws Exception{		
		int loanPeriod = 7;
		Branch branch = new Branch();
		PatronItemEligibility glpatritemelig = new PatronItemEligibility();
		glpatritemelig.setLoanPeriod(loanPeriod);
		Date checkOutDateTime = getDate("01/01/2001");
		Date expectedDueDate = getDate("09/01/2001");
		
		IMockBuilder<DueDateCalculationServiceImpl> dueDateCalculationServiceMock = createMockBuilder(DueDateCalculationServiceImpl.class);
		dueDateCalculationServiceMock.addMockedMethod("getDateCalculator");
		this.dueDateCalculationService = dueDateCalculationServiceMock.createMock();
		
		Set<LocalDate> holidays = new HashSet();				
		holidays.add(new LocalDate(getDate("03/01/2001")));
		HolidayCalendar<LocalDate> holidayCalendar = new DefaultHolidayCalendar<LocalDate>();
		holidayCalendar.setHolidays(holidays);	
		mockDateCalculator.setHolidayCalendar(holidayCalendar);
		
		mockDateCalculator.setStartDate(new LocalDate(checkOutDateTime));
		expect(dueDateCalculationService.getDateCalculator(branch, checkOutDateTime)).andReturn(mockDateCalculator).times(1);
		replay(dueDateCalculationService);	
		Date returnedDueDate = dueDateCalculationService.calculateDueDateTimeForDailyLoanIncludeHoliday(checkOutDateTime, glpatritemelig, branch);
		assertEquals(expectedDueDate, returnedDueDate);
		
	}
	
	@Test
	public void testCalculateDueDateTimeForHourlyLoan_DueDateFallInBusinessHour(){
		IMockBuilder<DueDateCalculationServiceImpl> dueDateCalculationServiceMock = createMockBuilder(DueDateCalculationServiceImpl.class);
		dueDateCalculationServiceMock.addMockedMethod("isInBusinessHour");
		this.dueDateCalculationService = dueDateCalculationServiceMock.createMock();
				
		DateTime checkOutDateTime = new DateTime(getDatetime("01/01/2001 01:01:02"));
		DateTime expectedDueDateTime = new DateTime(getDatetime("01/01/2001 03:01:02"));
		Branch branch = new Branch();
		PatronItemEligibility glpatritemelig = new PatronItemEligibility();
		glpatritemelig.setLoanPeriod(2);
		
		expect(dueDateCalculationService.isInBusinessHour(expectedDueDateTime, branch)).andReturn(true).times(1);
		replay(dueDateCalculationService);	
				
		Date dueDateTime = dueDateCalculationService.calculateDueDateTimeForHourlyLoan(checkOutDateTime.toDate(), glpatritemelig, branch);
		assertEquals(expectedDueDateTime, new DateTime(dueDateTime));
	}
	
	@Test
	public void testCalculateDueDateTimeForHourlyLoan_DueDateNotFallInBusinessHour(){
		IMockBuilder<DueDateCalculationServiceImpl> dueDateCalculationServiceMock = createMockBuilder(DueDateCalculationServiceImpl.class);
		dueDateCalculationServiceMock.addMockedMethod("isInBusinessHour");
		this.dueDateCalculationService = dueDateCalculationServiceMock.createMock();
				
		DateTime checkOutDateTime = new DateTime(getDatetime("01/01/2001 01:01:02"));
		DateTime tempDueDateTime = new DateTime(getDatetime("01/01/2001 03:01:02"));
		DateTime expectedDueDateTime = new DateTime(getDatetime("01/01/2001 03:03:02"));
		Branch branch = new Branch();
		PatronItemEligibility glpatritemelig = new PatronItemEligibility();
		glpatritemelig.setLoanPeriod(2);
		
		expect(dueDateCalculationService.isInBusinessHour(tempDueDateTime, branch)).andReturn(false).times(1);
		expect(dueDateCalculationService.isInBusinessHour(tempDueDateTime.plusMinutes(1), branch)).andReturn(false).times(1);
		expect(dueDateCalculationService.isInBusinessHour(tempDueDateTime.plusMinutes(2), branch)).andReturn(true).times(1);
		replay(dueDateCalculationService);	
				
		Date dueDateTime = dueDateCalculationService.calculateDueDateTimeForHourlyLoan(checkOutDateTime.toDate(), glpatritemelig, branch);
		assertEquals(expectedDueDateTime, new DateTime(dueDateTime));
	}
	
	

}

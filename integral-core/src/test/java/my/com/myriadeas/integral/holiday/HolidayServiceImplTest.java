package my.com.myriadeas.integral.holiday;

import static org.easymock.EasyMock.createMockBuilder;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Holiday;

import org.easymock.IMockBuilder;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HolidayServiceImplTest {

	//@Test
	public void testGetHolidaysByHoliday() {
		HolidayServiceImpl holidayService = new HolidayServiceImpl();
		Holiday holiday = new Holiday();
		int startDayOfMonth = 0;
		int endDayOfMonth = 0;
		int startMonth = 0;
		int endMonth = 0;
		int startYear = 0;
		int endYear = 0;
		/*-
		 * 
			startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
			2012	2012	1	1	1	1	1
			2012	2012	1	1	1	2	2
									
									
									
			2012	2012	1	5	1	1	122
			2013	2013	1	5	1	1	121
									
			2012	2013	1	1	1	1	367
			2013	2014	1	1	1	1	366


		 */

		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2012	     2012	    1			1			1				1			 1       
		 */
		Set<LocalDate> expected = new HashSet<LocalDate>();
		int expectedDays = 1;
		LocalDate localDate = new LocalDate(2012, 1, 1);
		expected.add(localDate);
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 1;
		endMonth = 1;
		startYear = 2012;
		endYear = 2012;

		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		Set<LocalDate> actual = holidayService.populateHolidays(holiday);
		System.out.println(actual);
		assertEquals(expected, actual);
		assertEquals(expectedDays, actual.size());
		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2012	     2012	    1			1			1				2			 2     
		 */
		expected = new HashSet<LocalDate>();
		expectedDays = 2;
		localDate = new LocalDate(2012, 1, 1);
		expected.add(localDate);
		localDate = new LocalDate(2012, 1, 2);
		expected.add(localDate);
		startDayOfMonth = 1;
		endDayOfMonth = 2;
		startMonth = 1;
		endMonth = 1;
		startYear = 2012;
		endYear = 2012;
		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		actual = holidayService.populateHolidays(holiday);
		assertEquals(
				"start month equals end month, startDayOfMonth less than endDayOfMonth",
				expected, actual);
		System.out.println(actual);
		assertEquals(expectedDays, actual.size());
		/*- startMonth | endMonth | startDayOfMonth | endDayOfMonth
		 * 1		  | 1		 |      2          |     1         
		 */
		expected = new HashSet<LocalDate>();
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 2;
		endMonth = 1;
		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		try {
			actual = holidayService.populateHolidays(holiday);
			fail("should throw exception. empty holiday");
		} catch (EmptyHolidayException e) {
		}

		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2012	     2012	    1			5			1				1			 122       
		 */
		expected = new HashSet<LocalDate>();
		expectedDays = 122;
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 1;
		endMonth = 5;
		startYear = 2012;
		endYear = 2012;

		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		actual = holidayService.populateHolidays(holiday);
		assertEquals(expectedDays, actual.size());
		System.out.println(actual);

		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2013	     2013	    1			5			1				1			 121      
		 */
		expected = new HashSet<LocalDate>();
		expectedDays = 121;
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 1;
		endMonth = 5;
		startYear = 2013;
		endYear = 2013;

		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		actual = holidayService.populateHolidays(holiday);
		assertEquals(expectedDays, actual.size());

		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2012	     2013	    1			1			1				1			 367       
		 */
		expected = new HashSet<LocalDate>();
		expectedDays = 367;
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 1;
		endMonth = 1;
		startYear = 2012;
		endYear = 2013;

		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		actual = holidayService.populateHolidays(holiday);
		assertEquals(expectedDays, actual.size());

		/*- startYear	endYear	startMonth	endMonth	startDayOfMonth	endDayOfMonth	size
		 *    2013	     2014	    1			1			1				1			 366       
		 */
		expected = new HashSet<LocalDate>();
		expectedDays = 366;
		startDayOfMonth = 1;
		endDayOfMonth = 1;
		startMonth = 1;
		endMonth = 1;
		startYear = 2013;
		endYear = 2014;

		holiday.setEndDayOfMonth(endDayOfMonth);
		holiday.setStartDayOfMonth(startDayOfMonth);
		holiday.setEndMonth(endMonth);
		holiday.setStartMonth(startMonth);
		holidayService.setEarlyBoundary(new LocalDate(startYear, 1, 1));
		//holidayService.setLateBoundary(new LocalDate(endYear, 1, 1));
		actual = holidayService.populateHolidays(holiday);
		assertEquals(expectedDays, actual.size());

	}

	//@Test
	public void testSetupHolidayByListOfHolidays() {
		IMockBuilder<HolidayServiceImpl> holidayServiceMock = createMockBuilder(HolidayServiceImpl.class);
		holidayServiceMock.addMockedMethod("populateHolidays", Holiday.class);
		HolidayServiceImpl holidayService = holidayServiceMock.createMock();
		List<Holiday> holidays = new ArrayList<Holiday>();
		Branch glbrnc = new Branch("first branch", "first branch");
		Holiday holiday = new Holiday();
		holiday.setBranch(glbrnc);
		holidays.add(holiday);
		glbrnc = new Branch("second branch", "second branch");
		holiday = new Holiday();
		holiday.setBranch(glbrnc);
		holidays.add(holiday);

		Set<LocalDate> returnOfGetHolidays = new HashSet<LocalDate>();
		returnOfGetHolidays.add(new LocalDate(2013, 1, 1));
		returnOfGetHolidays.add(new LocalDate(2013, 1, 2));
		expect(holidayService.populateHolidays(holidays.get(0))).andReturn(
				returnOfGetHolidays).times(1);
		expect(holidayService.populateHolidays(holidays.get(1))).andReturn(
				returnOfGetHolidays).times(1);
		replay(holidayService);
		holidayService.setupHoliday(holidays);
		assertEquals("Setup 2 branches", 3, holidayService.getHolidays().size());
		assertEquals("Setup 2 branch only with 2 holiday", 2, holidayService
				.getHolidays().get(holidays.get(0).getBranch()).size());
		assertEquals("Setup 2 branch only with 2 holiday", 2, holidayService
				.getHolidays().get(holidays.get(1).getBranch()).size());
	}

	@Test
	public void testHolidayJson() throws JsonProcessingException {
		Holiday holiday = new Holiday("NY", "New Year", 1, 1, 1, 1);
		Branch glbrnc = new Branch("Branch1", "first branch");
		holiday.setBranch(glbrnc);
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(holiday));
		
		List<Branch> branches = new ArrayList<Branch>();
		branches.add(glbrnc);
		glbrnc = new Branch("Branch2", "second branch");
		branches.add(glbrnc);
		glbrnc = new Branch("Branch3", "third branch");
		branches.add(glbrnc);
		System.out.println(mapper.writeValueAsString(branches));
		
		List<Holiday> holidays = new ArrayList<Holiday>();
		holidays.add(holiday);
		
		holiday = new Holiday("CNY", "Chinese New Year", 1, 1, 5, 6);
		holiday.setBranch(glbrnc);
		holidays.add(holiday);
		System.out.println(mapper.writeValueAsString(holidays));
		
	}
	
	@Test
	public void testPopulateHolidays(){
		IMockBuilder<HolidayServiceImpl> holidayServiceMock = createMockBuilder(HolidayServiceImpl.class);
		holidayServiceMock.addMockedMethod("populateHolidays", Set.class, LocalDate.class, LocalDate.class);
		
		HolidayServiceImpl holidayService = new HolidayServiceImpl();
		holidayService = holidayServiceMock.createMock();	
		
		testHolidayIsBeforeEarlyBoundary(holidayService);
		reset(holidayService);	
		testHolidayIsOnEarlyBoundary(holidayService);
		reset(holidayService);	
		testHolidayIsAfterEarlyBoundary(holidayService);
	}
	
	
	private void testHolidayIsAfterEarlyBoundary(HolidayServiceImpl holidayService){
		holidayService.setEarlyBoundary(new LocalDate(2014, 1, 1));
		
		LocalDate startDate = new LocalDate(2014, 1, 2);
		LocalDate endDate = new LocalDate(2014, 1, 2);	
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Set<LocalDate> mockHolidays = new HashSet<LocalDate>();
		mockHolidays.add(new LocalDate(2014, 1, 2));
		expect(holidayService.populateHolidays(holidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		endDate = new LocalDate(2015, 1, 2);
		startDate = new LocalDate(2015, 1, 2);
		expect(holidayService.populateHolidays(mockHolidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		replay(holidayService);
			
		Holiday holiday = new Holiday();
		holiday.setStartDayOfMonth(2);
		holiday.setStartMonth(1);
		holiday.setEndDayOfMonth(2);
		holiday.setEndMonth(1);
		
		Set<LocalDate> expectedHoliday = new HashSet<LocalDate>();
		expectedHoliday.add(new LocalDate(2014, 1, 2));
		Set<LocalDate> returnedHolidays = holidayService.populateHolidays(holiday);
		assertEquals(expectedHoliday, returnedHolidays);
	}
	
	private void testHolidayIsOnEarlyBoundary(HolidayServiceImpl holidayService){
		holidayService.setEarlyBoundary(new LocalDate(2014, 1, 1));
		
		LocalDate startDate = new LocalDate(2014, 1, 1);
		LocalDate endDate = new LocalDate(2014, 1, 1);	
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Set<LocalDate> mockHolidays = new HashSet<LocalDate>();
		mockHolidays.add(new LocalDate(2014, 1, 1));
		expect(holidayService.populateHolidays(holidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		endDate = new LocalDate(2015, 1, 1);
		startDate = new LocalDate(2015, 1, 1);
		mockHolidays.add(new LocalDate(2015, 1, 1));
		expect(holidayService.populateHolidays(mockHolidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		replay(holidayService);
			
		Holiday holiday = new Holiday();
		holiday.setStartDayOfMonth(1);
		holiday.setStartMonth(1);
		holiday.setEndDayOfMonth(1);
		holiday.setEndMonth(1);
		
		Set<LocalDate> expectedHoliday = new HashSet<LocalDate>();
		expectedHoliday.add(new LocalDate(2014, 1, 1));
		expectedHoliday.add(new LocalDate(2015, 1, 1));
		Set<LocalDate> returnedHolidays = holidayService.populateHolidays(holiday);
		assertEquals(expectedHoliday, returnedHolidays);
	}
	
	private void testHolidayIsBeforeEarlyBoundary(HolidayServiceImpl holidayService){
		holidayService.setEarlyBoundary(new LocalDate(2014, 1, 10));
		
		
		LocalDate endDate = new LocalDate(2014, 1, 1);
		LocalDate startDate = new LocalDate(2014, 1, 1);
		
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Set<LocalDate> mockHolidays = new HashSet<LocalDate>();
		expect(holidayService.populateHolidays(holidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		endDate = new LocalDate(2015, 1, 1);
		startDate = new LocalDate(2015, 1, 1);
		mockHolidays.add(new LocalDate(2015, 1, 1));
		expect(holidayService.populateHolidays(mockHolidays, startDate, endDate)).andReturn(mockHolidays).times(1);
		replay(holidayService);
			
		Holiday holiday = new Holiday();
		holiday.setStartDayOfMonth(1);
		holiday.setStartMonth(1);
		holiday.setEndDayOfMonth(1);
		holiday.setEndMonth(1);
		
		Set<LocalDate> expectedHoliday = new HashSet<LocalDate>();
		expectedHoliday.add(new LocalDate(2015, 1, 1));
		Set<LocalDate> returnedHolidays = holidayService.populateHolidays(holiday);
		assertEquals(expectedHoliday, returnedHolidays);
	}
	
	@Test
	public void testAddHolidayInBetween(){
		IMockBuilder<HolidayServiceImpl> holidayServiceMock = createMockBuilder(HolidayServiceImpl.class);
		holidayServiceMock.addMockedMethod("addToHolidays");
		
		HolidayServiceImpl holidayService = new HolidayServiceImpl();
		holidayService = holidayServiceMock.createMock();		
				
		testStartDateSameAsEndDate(holidayService);		
		reset(holidayService);		
		testNoDaysInBetweenStartDateAndEndDate(holidayService);
		reset(holidayService);
		testOneDayInBetweenStartDateAndEndDate(holidayService);
		reset(holidayService);
		testTwoDaysInBetweenStartDateAndEndDate(holidayService);
		reset(holidayService);
		testStartDateBeforeEndDate(holidayService);
		
		
	}
	
	private void testStartDateSameAsEndDate(HolidayServiceImpl holidayService){
		Set<LocalDate> holidays = new HashSet<LocalDate>();		
		LocalDate startDate = new LocalDate(2014, 1, 1);
		LocalDate endDate = new LocalDate(2014, 1, 1);		
		Set<LocalDate> expectedHolidays = new HashSet<LocalDate>();
		Set<LocalDate> returnedHolidays = holidayService.addHolidayInBetween(holidays, startDate, endDate);
		assertEquals(expectedHolidays, returnedHolidays);
		
		
	}
	
	private void testNoDaysInBetweenStartDateAndEndDate(HolidayServiceImpl holidayService){
		Set<LocalDate> holidays = new HashSet<LocalDate>();		
		LocalDate startDate = new LocalDate(2014, 1, 1);
		LocalDate endDate = new LocalDate(2014, 1, 2);		
		Set<LocalDate> expectedHolidays = new HashSet<LocalDate>();
		Set<LocalDate> returnedHolidays = holidayService.addHolidayInBetween(holidays, startDate, endDate);
		assertEquals(expectedHolidays, returnedHolidays);		
	}
	
	
	private void testOneDayInBetweenStartDateAndEndDate(HolidayServiceImpl holidayService){
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Set<LocalDate> mockHolidays = new HashSet<LocalDate>();
		mockHolidays.add(new LocalDate(2014, 1, 2));
		expect(holidayService.addToHolidays(holidays, new LocalDate(2014, 1, 2))).andReturn(mockHolidays).times(1);
		replay(holidayService);
		
		LocalDate startDate = new LocalDate(2014, 1, 1);
		LocalDate endDate = new LocalDate(2014, 1, 3);		
		Set<LocalDate> expectedHolidays = new HashSet<LocalDate>();
		expectedHolidays.add(new LocalDate(2014, 1, 2));
		Set<LocalDate> returnedHolidays = holidayService.addHolidayInBetween(holidays, startDate, endDate);
		assertEquals(expectedHolidays, returnedHolidays);
	}
	
	private void testTwoDaysInBetweenStartDateAndEndDate(HolidayServiceImpl holidayService){
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Set<LocalDate> mockHolidays = new HashSet<LocalDate>();
		mockHolidays.add(new LocalDate(2014, 1, 2));
		expect(holidayService.addToHolidays(holidays, new LocalDate(2014, 1, 2))).andReturn(mockHolidays).times(1);
		mockHolidays.add(new LocalDate(2014, 1, 3));
		expect(holidayService.addToHolidays(mockHolidays, new LocalDate(2014, 1, 3))).andReturn(mockHolidays).times(1);
		replay(holidayService);
		
		LocalDate startDate = new LocalDate(2014, 1, 1);
		LocalDate endDate = new LocalDate(2014, 1, 4);		
		Set<LocalDate> expectedHolidays = new HashSet<LocalDate>();
		expectedHolidays.add(new LocalDate(2014, 1, 2));
		expectedHolidays.add(new LocalDate(2014, 1, 3));
		Set<LocalDate> returnedHolidays = holidayService.addHolidayInBetween(holidays, startDate, endDate);
		assertEquals(expectedHolidays, returnedHolidays);
	}
	
	private void testStartDateBeforeEndDate(HolidayServiceImpl holidayService){
		Set<LocalDate> holidays = new HashSet<LocalDate>();		
		LocalDate startDate = new LocalDate(2014, 1, 2);
		LocalDate endDate = new LocalDate(2014, 1, 1);		
		Set<LocalDate> expectedHolidays = new HashSet<LocalDate>();
		Set<LocalDate> returnedHolidays = holidayService.addHolidayInBetween(holidays, startDate, endDate);
		assertEquals(expectedHolidays, returnedHolidays);		
	}
	
	
	@Test
	public void testIsWithinBoundary(){
		HolidayServiceImpl holidayService = new HolidayServiceImpl();
		holidayService.setEarlyBoundary(new LocalDate(2014, 1, 10));
		
		LocalDate date = new LocalDate(2014, 1, 1);
		assertFalse(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2013, 12, 1);
		assertFalse(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2014, 1, 10);
		assertTrue(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2014, 1, 11);
		assertTrue(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2014, 2, 11);
		assertTrue(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2015, 1, 9);
		assertTrue(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2015, 1, 10);
		assertTrue(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2015, 1, 11);
		assertFalse(holidayService.isWithinBoundary(date));
		
		date = new LocalDate(2015, 2, 15);
		assertFalse(holidayService.isWithinBoundary(date));
	}
}

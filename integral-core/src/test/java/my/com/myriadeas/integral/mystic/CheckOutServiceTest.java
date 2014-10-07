package my.com.myriadeas.integral.mystic;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractTransactionalJUnit4SpringContextTests;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Holiday;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.HolidayRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.OfficerRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronItemEligibilityRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.populator.BranchData;
import my.com.myriadeas.integral.data.populator.ConditionData;
import my.com.myriadeas.integral.data.populator.ItemCategoryData;
import my.com.myriadeas.integral.data.populator.LocationData;
import my.com.myriadeas.integral.data.populator.MaterialData;
import my.com.myriadeas.integral.data.populator.OfficerData;
import my.com.myriadeas.integral.data.populator.PatronCategoryData;
import my.com.myriadeas.integral.data.populator.PatronItemEligibilityData;
import my.com.myriadeas.integral.data.populator.SmdData;
import my.com.myriadeas.integral.eligibility.services.impl.PatronItemEligibilityLookupImpl;
import my.com.myriadeas.integral.holiday.HolidayServiceImpl;
import my.com.myriadeas.integral.holiday.LibraryCalendarServiceImpl;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckOutServiceTest extends
		IntegralAbstractTransactionalJUnit4SpringContextTests implements
		BranchData, PatronCategoryData, ItemCategoryData, LocationData,
		SmdData, MaterialData, ConditionData, PatronItemEligibilityData, OfficerData {

	@Autowired
	@Qualifier("circulationServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:start2", context = "circulationServiceRoute")
	protected ProducerTemplate template;

	@Autowired
	private PatronItemEligibilityLookupImpl patronItemEligibilityLookup;

	@Autowired
	private LibraryCalendarServiceImpl libraryCalendarService;

	@Autowired
	private HolidayServiceImpl holidayService;

	@Autowired
	private HolidayRepositoryImpl hoplidayRepo;

	@Autowired
	private CirculationTransactionRepositoryImpl circulationTransactionRepo;

	@Autowired
	private PatronItemEligibilityRepositoryImpl patronItemEligRepo;

	@Autowired
	private OfficerRepositoryImpl officerRepo;
	
	@Autowired
	private PatronRepositoryImpl patronRepo;
	
	@Autowired
	private ItemRepositoryImpl itemRepo;
	
	@Autowired
	private HolidayRepositoryImpl holidayRepo;

	@Test
	public void testMarker() {
	}

	@Test
	public void test01() {
		/*-
		As a librarian, 
		I want to define how long the patron can borrow the daily or hourly loan item based on who is borrowing and what are they borrowing 
		so that patron is allowed to borrow not important or not popular item longer.

		Scenario1: Checkout hourly loan item
		Give patron id \0000000001
		Icat \Red spot
		Pcat \Lecturer
		Loan periods \4 hours
		Current date time \01/02/2014 10:00am
		When librarian scan item barcode
		Then due date time is 01/02/2014 2:00pm

		 */
		Patron patron = new Patron("0000000001", "0000000001", "0000000001",
				getDate("02/02/2014"), MAIN_BRANCH, LECTURER_CATEGORY);
		patron.setMembershipDate(getDate("02/02/2000"));
		patronRepo.save(patron);
		Item item = new Item("item0001", RED_SPOT, LEVEL_ONE_MAIN_BRANCH, MAT1,
				BOOK, ItemStatus.AVAILABLE, GOOD_CONDITION);
		itemRepo.save(item);

		PatronItemEligibility patronItemElig = new PatronItemEligibility(
				"LECTURER_RS", "Lecturer Red Spot Eligibility",
				LoanUnit.HOURLY, 4);
		patronItemElig.setMaxFines(new BigDecimal("10.00"));
		patronItemElig.setCriteria("-pc LECTURER -ic RS");
		patronItemElig.setWeight(1000);
		patronItemEligRepo.save(patronItemElig);
		patronItemEligibilityLookup.reload();

		Date expectedDueDateTime = getDatetime("01/02/2014 14:00:00");
		Date dueDateTime = item.calculateDueDateTime(patron,
				getDatetime("01/02/2014 10:00:00"));
		assertEquals(expectedDueDateTime, dueDateTime);

	}

	@Test
	public void test02() {
		/*-
		As a librarian, 
		I want to define how long the patron can borrow the daily or hourly loan item based on who is borrowing and what are they borrowing 
		so that patron is allowed to borrow not important or not popular item longer.

		Scenario2: Checkout daily loan item
		Give patron id \0000000001
		Icat \Open shelf
		Pcat \Lecturer
		Loan periods \4 days
		Current date time \01/02/2014 
		When librarian scan item barcode
		Then due date is 05/02/2014

		 */
		Patron patron = new Patron("0000000001", "0000000001", "0000000001",
				getDate("02/02/2014"), MAIN_BRANCH, LECTURER_CATEGORY);
		patron.setMembershipDate(getDate("02/02/2000"));
		patronRepo.save(patron);
		Item item = new Item("item0001", OPEN_SHELF, LEVEL_ONE_MAIN_BRANCH,
				MAT1, BOOK, ItemStatus.AVAILABLE, GOOD_CONDITION);
		itemRepo.save(item);

		PatronItemEligibility patronItemElig = new PatronItemEligibility(
				"LECTURER_OS", "Lecturer Open Shelf Eligibility",
				LoanUnit.DAILY, 4);
		patronItemElig.setMaxFines(new BigDecimal("10.00"));
		patronItemElig.setCriteria("-pc LECTURER -ic OS");
		patronItemElig.setWeight(1000);
		patronItemEligRepo.save(patronItemElig);
		patronItemEligibilityLookup.reload();

		Date expectedDueDate = getDate("05/02/2014");
		Date dueDateTime = item.calculateDueDateTime(patron,
				getDate("01/02/2014"));
		assertEquals(expectedDueDate, dueDateTime);

	}

	@Test
	public void test03() {
		/*-
		As a librarian,
		I do not want due date to be fell on holiday 
		because library is closed on holiday
		
		Scenario1: Checkout daily loan item
		Give patron id \0000000001
		Icat \Open shelf
		Pcat \Lecturer
		Loan periods \4 days
		Current date time \01/02/2014 
		
		Holiday \05/02/2014
		When librarian scan item barcode
		Then due date is 06/02/2014


		 */
		Patron patron = new Patron("0000000001", "0000000001", "0000000001",
				getDate("02/02/2014"), MAIN_BRANCH, LECTURER_CATEGORY);
		patron.setMembershipDate(getDate("02/02/2000"));
		patronRepo.save(patron);
		Item item = new Item("item0001", OPEN_SHELF, LEVEL_ONE_MAIN_BRANCH,
				MAT1, BOOK, ItemStatus.AVAILABLE, GOOD_CONDITION);
		itemRepo.save(item);

		PatronItemEligibility patronItemElig = new PatronItemEligibility(
				"LECTURER_OS_Daily",
				"Lecturer Open Shelf Daily Loan Eligibility", LoanUnit.DAILY, 4);
		patronItemElig.setMaxFines(new BigDecimal("10.00"));
		patronItemElig.setCriteria("-pc LECTURER -ic OS");
		patronItemElig.setWeight(1000);
		patronItemEligRepo.save(patronItemElig);

		patronItemEligibilityLookup.reload();

		/*
		 * Set<LocalDate> holidays = new HashSet<LocalDate>(); holidays.add(new
		 * LocalDate(2012, 2, 5)); holidayService.getHolidays(item.getBranch());
		 * holidayService.reload();
		 */

		Date checkOutDate = getDate("01/02/2014");
		Holiday holiday = setHoliday(item.getBranch(),
				new LocalDate(2014, 2, 5), "holiday1");
		holidayService.setEarlyBoundary(new LocalDate(checkOutDate));
		holidayService.reload();
		libraryCalendarService.reload();

		Date expectedDueDate = getDate("06/02/2014");
		Date dueDateTime = item.calculateDueDateTime(patron, checkOutDate);
		assertEquals(expectedDueDate, dueDateTime);

	}

	private Holiday setHoliday(Branch branch, LocalDate date, String holidayCode) {
		Holiday holiday = new Holiday();
		holiday.setBranch(branch);
		holiday.setCode(holidayCode);
		holiday.setStartMonth(date.getMonthOfYear());
		holiday.setStartDayOfMonth(date.getDayOfMonth());
		holiday.setEndMonth(date.getMonthOfYear());
		holiday.setEndDayOfMonth(date.getDayOfMonth());
		holidayRepo.save(holiday);
		return holiday;
	}

	@Test
	public void testCheckInCheckOutRenew() {

		Patron patron = new Patron("0000000001", "0000000001", "0000000001",
				getDate("02/02/2014"), MAIN_BRANCH, LECTURER_CATEGORY);
		patron.setMembershipDate(getDate("02/02/2000"));
		patronRepo.save(patron);
		Item item = new Item("item0001", RED_SPOT, LEVEL_ONE_MAIN_BRANCH, MAT1,
				BOOK, ItemStatus.AVAILABLE, GOOD_CONDITION);
		itemRepo.save(item);

		PatronItemEligibility patronItemElig = new PatronItemEligibility(
				"LECTURER_RS", "Lecturer Red Spot Eligibility",
				LoanUnit.HOURLY, 4);
		patronItemElig.setMaxFines(new BigDecimal("10.00"));
		patronItemElig.setCriteria("-pc LECTURER -ic RS");
		patronItemElig.setWeight(1000);
		patronItemEligRepo.save(patronItemElig);
		patronItemEligibilityLookup.reload();

		Officer officer = new Officer("OFFICER1", "X", "Terence", MAIN_BRANCH);
		officerRepo.save(officer);
		Date checkOutDate = getDate("01/02/2014");
		Date expectedDueDateTime = getDatetime("01/02/2014 14:00:00");
		CirculationTransaction circulationTransaction = item.checkOut(officer,
				patron, checkOutDate);

		System.out.println(circulationTransactionRepo
				.findById(circulationTransaction.getId()));
		circulationTransaction = item.checkIn(officer, checkOutDate);

	}

}

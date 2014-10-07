package my.com.myriadeas.integral.mystic;

import java.math.BigDecimal;
import java.util.Date;

import my.com.myriadeas.integral.cataloguing.config.IntegralCataloguingConfigDev;
import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.circulation.command.CheckOutEventCommand;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.circulation.request.CheckOutRequest;
import my.com.myriadeas.integral.circulation.response.CheckOutResponse;
import my.com.myriadeas.integral.circulation.service.impl.IntegralAbstractCamelTestSupportTests;
import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
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
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.CamelContext;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		IntegralCataloguingConfigDev.class, IntegralCirculationConfigDev.class })
@ActiveProfiles(SpringEnvironmentUtil.DEV)
public class CheckOutRouteTest extends IntegralAbstractCamelTestSupportTests
		implements BranchData, PatronCategoryData, ItemCategoryData,
		LocationData, SmdData, MaterialData, ConditionData,
		PatronItemEligibilityData, OfficerData {

	@Autowired
	@Qualifier("circulationServiceRoute")
	protected CamelContext camelContext;

	@Produce(uri = "direct:start2", context = "circulationServiceRoute")
	protected ProducerTemplate template;

	@Autowired
	private PatronItemEligibilityLookupImpl patronItemEligibilityLookup;

	@Autowired
	private PatronItemEligibilityRepositoryImpl patronItemEligibilityRepo;

	@Autowired
	private PatronRepositoryImpl patronRepo;

	@Autowired
	private ItemRepositoryImpl itemRepo;

	@Before
	public void setup() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testMarker() throws Exception {
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
				getDate("02/02/2020"), MAIN_BRANCH, LECTURER_CATEGORY);
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
		patronItemElig.setMaxLoanAllowed(2);
		patronItemEligibilityRepo.save(patronItemElig);
		patronItemEligibilityLookup.reload();

		Date expectedDueDateTime = getDatetime("01/02/2014 14:00:00");
		Date checkOutDate = getDatetime("01/02/2014 10:00:00");
		CheckOutRequest request = new CheckOutRequest();
		request.setPatronIdentifier(patron.getUsername());
		request.setItemIdentifier(item.getItemIdentifier());
		request.setTransactionDate(checkOutDate);

		// MockEndpoint mockAddRecord =
		// getMockEndpoint("mock:circulation.checkout");
		CheckOutResponse response = template.requestBody(
				"direct://circulation.checkout", request,
				CheckOutResponse.class);
		// mockAddRecord.assertIsSatisfied();
		// mockAddRecord.expectedMessageCount(1);

		CirculationTransaction circulationTransaction = response
				.getCirculationDetail();
		assertEquals(expectedDueDateTime,
				circulationTransaction.getDueDateTime());

	}

}

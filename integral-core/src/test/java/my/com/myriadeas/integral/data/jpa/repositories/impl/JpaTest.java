package my.com.myriadeas.integral.data.jpa.repositories.impl;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import my.com.myriadeas.integral.circulation.LoanUnit;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigDev;
import my.com.myriadeas.integral.circulation.config.IntegralCirculationConfigTest;
import my.com.myriadeas.integral.data.jpa.domain.Address;
import my.com.myriadeas.integral.data.jpa.domain.Fine;
import my.com.myriadeas.integral.data.jpa.domain.Gender;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;
import my.com.myriadeas.integral.data.jpa.domain.PatronItemEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.OfficerRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronCategoryRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronItemEligibilityRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronRepository;
import my.com.myriadeas.integral.data.populator.BranchData;
import my.com.myriadeas.integral.data.populator.BranchDatabasePopulatorIntf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles(DEV)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegralCirculationConfigDev.class, IntegralCirculationConfigTest.class })
public class JpaTest implements BranchData {

	@Autowired
	private PatronCategoryRepository glcateRepository;

	@Autowired
	private ItemEligibilityRepositoryImpl itemEligibilityRepository;

	
	@Autowired
	private PatronItemEligibilityRepository patronItemEligibilityRepository;
	
	@Autowired
	private BranchDatabasePopulatorIntf branchPopulator;
	
	@Autowired
	private PatronRepository patronRepo;
	

	@Autowired
	private OfficerRepository officerRepo;

	@Test
	public void test() {
		assertNotNull(glcateRepository);
		PatronCategory cate = new PatronCategory("Student", "student");
		glcateRepository.save(cate);
		assertNotNull(cate.getLastModifiedDate());
		System.out.println(itemEligibilityRepository.findAll().get(0)
				.getItemCategories());

	}
	
	@Test
	public void testSaveOfficerWithAddress() {
		Officer officer = new Officer("username02", "username01", "username01", KL_BRANCH);
		Address address = new Address();
		address.setAddress1("address1");
		address.setAddress2("address2");
		officer.setHomeAddress(address);
		officerRepo.save(officer);
		assertEquals("address1", officerRepo.findByUsername(officer.getUsername()).getHomeAddress().getAddress1());
		officer.getHomeAddress().setAddress1("newAddress1");
		officerRepo.save(officer);
		assertEquals("newAddress1", officerRepo.findByUsername(officer.getUsername()).getHomeAddress().getAddress1());
	}
	
	@Test
	public void testSaveOfficerWithEnum() {
		Officer officer = new Officer("username01", "username01", "username01", KL_BRANCH);
		officer.setGender(Gender.MALE);
		officerRepo.save(officer);
	}

	@Test
	public void testUpdateFines() {
		PatronItemEligibility RED_SPOT_ITEM_ELIGIBILITY = new PatronItemEligibility(
				"TEST", "Test Eligibility", LoanUnit.DAILY, 0);
		RED_SPOT_ITEM_ELIGIBILITY.setFines(Arrays.asList(new Fine[] { new Fine(
				1, 1, new BigDecimal("0.10")) }));
		patronItemEligibilityRepository.save(RED_SPOT_ITEM_ELIGIBILITY);
		RED_SPOT_ITEM_ELIGIBILITY.getFines().get(0).setStop(200);
		RED_SPOT_ITEM_ELIGIBILITY.setFines(Arrays.asList(new Fine[] { new Fine(
				1, 1, new BigDecimal("0.10")) }));
		patronItemEligibilityRepository.save(RED_SPOT_ITEM_ELIGIBILITY);
		assertTrue(patronItemEligibilityRepository.findOne(RED_SPOT_ITEM_ELIGIBILITY.getId()).getFines().size() > 0);
		for(Fine fine : patronItemEligibilityRepository.findOne(RED_SPOT_ITEM_ELIGIBILITY.getId()).getFines()) {
			System.out.println(fine.getStop());
		}
	}

}

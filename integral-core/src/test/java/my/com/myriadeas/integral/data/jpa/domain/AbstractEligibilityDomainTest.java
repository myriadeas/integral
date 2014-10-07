package my.com.myriadeas.integral.data.jpa.domain;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.AbstractEligibilityDomain;
import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.repositories.ItemCategoryRepository;
import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;

import org.junit.Test;

import com.beust.jcommander.ParameterException;

public class AbstractEligibilityDomainTest {

	@Test
	public void testGetEligibilityRuleCommand() {
		AbstractEligibilityDomain eligibilityDomain = getTestImpl();
		String nullCriteria = null;
		eligibilityDomain.setCriteria(nullCriteria);
		assertEquals(new EligibilityRuleCommand(),
				eligibilityDomain.getEligibilityRuleCommand());
		String correctFormat = "-pc pc -ic ic -smd smd -brc brc";
		eligibilityDomain.setCriteria(correctFormat);
		EligibilityRuleCommand expected = new EligibilityRuleCommand();
		expected.setPatronCategories(getParameterValues("pc"));
		expected.setItemCategories(getParameterValues("ic"));
		expected.setSmds(getParameterValues("smd"));
		expected.setBranches(getParameterValues("brc"));
		expected.setEligibility("el");
		assertEquals(expected, eligibilityDomain.getEligibilityRuleCommand());

		String wrongFormat = "pc";
		eligibilityDomain.setCriteria(wrongFormat);
		try {
			eligibilityDomain.getEligibilityRuleCommand();
			fail("should throw parameter exception");
		} catch (ParameterException e) {

		}
	}

	private List<String> getParameterValues(String... value) {
		return Arrays.asList(value);
	}

	@Test
	public void testPopulateItemCategories() {
		AbstractEligibilityDomain eligibilityDomain = getTestImpl();
		ItemCategoryRepository itemCategoryRepository = createMock(ItemCategoryRepository.class);
		ItemCategory mockItemCategory = new ItemCategory("ic", "ic");
		expect(itemCategoryRepository.findByCode("ic")).andReturn(
				mockItemCategory).times(1);
		replay(itemCategoryRepository);
		eligibilityDomain.setItemCategoryRepository(itemCategoryRepository);
		eligibilityDomain.setCriteria("-ic ic");
		eligibilityDomain.populateItemCategories();
		List<ItemCategory> expectedItemCategories = new ArrayList<ItemCategory>();
		expectedItemCategories.add(mockItemCategory);
		assertEquals(expectedItemCategories, eligibilityDomain.itemCategories);
	}

	// TODO - Lazy to test now.... DO IT LATER
	@Test
	public void testPopulatePatronCategories() {
	}

	@Test
	public void testPopulateBranches() {
	}

	@Test
	public void testPopulateSMDs() {
	}

	private AbstractEligibilityDomain getTestImpl() {
		AbstractEligibilityDomain eligibilityDomain = new AbstractEligibilityDomain() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void loadCriteriaDomain() {
			}

			public String getCode() {
				return "el";
			}
		};
		return eligibilityDomain;
	}

}

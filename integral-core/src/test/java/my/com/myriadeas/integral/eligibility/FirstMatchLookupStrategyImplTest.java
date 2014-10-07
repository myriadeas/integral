package my.com.myriadeas.integral.eligibility;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;
import my.com.myriadeas.integral.eligibility.FirstMatchLookupStrategyImpl;
import my.com.myriadeas.integral.eligibility.LookupTable;

import org.junit.Test;

public class FirstMatchLookupStrategyImplTest {

	@Test
	public void testLookupWithSingleCriteria() {
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();

		LookupTable lookupTable = new LookupTable(ruleCommands);

		FirstMatchLookupStrategyImpl lookupStrategy = new FirstMatchLookupStrategyImpl(
				lookupTable);
		EligibilityRuleCommand command = new EligibilityRuleCommand();
		command.setEligibility("administratorPolicy");
		command.getPatronCategories().add("administrator");
		ruleCommands.add(command);

		command = new EligibilityRuleCommand();
		command.setEligibility("librarianPolicy");
		command.getPatronCategories().add("cataloguingOfficer");
		command.getPatronCategories().add("circulationOfficer");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("generalPolicy");
		command.getPatronCategories().add("ANY");
		ruleCommands.add(command);

		/*-
		 *     -pc administrator          					    administratorPolicy
		 *     -pc cataloguingOfficer -pc circulationOffier     librarianPolicy
		 *     -pc ANY                    					    generalPolicy
		 */
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("pc", "administrator");
		String expected = "administratorPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "cataloguingOfficer");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "circulationOfficer");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "patron");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "lecturer");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
	}

	@Test
	public void testLookupWithTwoCriteria() {
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		LookupTable lookupTable = new LookupTable(ruleCommands);
		FirstMatchLookupStrategyImpl lookupStrategy = new FirstMatchLookupStrategyImpl(
				lookupTable);
		EligibilityRuleCommand command = new EligibilityRuleCommand();
		command.setEligibility("administratorPolicy");
		command.getPatronCategories().add("administrator");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("librarianPolicy");
		command.getPatronCategories().add("cataloguingOfficer");
		command.getPatronCategories().add("circulationOfficer");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("patronPolicy");
		command.getPatronCategories().add("lecturer");
		command.getPatronCategories().add("student");
		command.getItemCategories().add("RS");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("generalPolicy");
		command.getPatronCategories().add("ANY");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);

		/*-
		 *     -pc administrator -ic ANY        					   administratorPolicy
		 *     -pc cataloguingOfficer -pc circulationOffier -ic ANY    librarianPolicy
		 *     -pc lecturer -pc student -ic RS                         patronPolicy
		 *     -pc ANY -ic ANY                    					   generalPolicy
		 */

		/*-
		 * Test
		 *  administrator	RS	administratorPolicy
		    administrator	OS	administratorPolicy
			cataloguingOfficer	RS	librarianPolicy
			cataloguingOfficer	OS	librarianPolicy
			circulationOfficer	RS	librarianPolicy
			circulationOfficer	OS	librarianPolicy
			lecturer	RS	patronPolicy
			lecturer	OS	generalPolicy
			student	RS	patronPolicy
			student	OS	generalPolicy

		 */
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("pc", "administrator");
		criterias.put("ic", "OS");
		String expected = "administratorPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias.put("pc", "administrator");
		criterias.put("ic", "RS");
		expected = "administratorPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "cataloguingOfficer");
		criterias.put("ic", "RS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias = new HashMap<String, String>();
		criterias.put("pc", "cataloguingOfficer");
		criterias.put("ic", "OS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "circulationOfficer");
		criterias.put("ic", "RS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias = new HashMap<String, String>();
		criterias.put("pc", "circulationOfficer");
		criterias.put("ic", "OS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		/*-
		 * 
		 *  lecturer	RS	patronPolicy
		 *	lecturer	OS	generalPolicy
		 */

		criterias = new HashMap<String, String>();
		criterias.put("pc", "lecturer");
		criterias.put("ic", "RS");
		expected = "patronPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias = new HashMap<String, String>();
		criterias.put("pc", "lecturer");
		criterias.put("ic", "OS");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		/*-
		 * 
		 *  student	RS	patronPolicy
		 *  student	OS	generalPolicy
		 */

		criterias = new HashMap<String, String>();
		criterias.put("pc", "student");
		criterias.put("ic", "RS");
		expected = "patronPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias = new HashMap<String, String>();
		criterias.put("pc", "student");
		criterias.put("ic", "OS");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
	}

	@Test
	public void testLookupWithThreeCriteriaAndCommandTwoCriteria() {
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		LookupTable lookupTable = new LookupTable(ruleCommands);
		FirstMatchLookupStrategyImpl lookupStrategy = new FirstMatchLookupStrategyImpl(
				lookupTable);
		EligibilityRuleCommand command = new EligibilityRuleCommand();
		command.setEligibility("administratorPolicy");
		command.getPatronCategories().add("administrator");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("librarianPolicy");
		command.getPatronCategories().add("cataloguingOfficer");
		command.getPatronCategories().add("circulationOfficer");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("patronPolicy");
		command.getPatronCategories().add("lecturer");
		command.getPatronCategories().add("student");
		command.getItemCategories().add("RS");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("generalPolicy");
		command.getPatronCategories().add("ANY");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);

		/*-
		 *     -pc administrator -ic ANY        					   administratorPolicy
		 *     -pc cataloguingOfficer -pc circulationOffier -ic ANY    librarianPolicy
		 *     -pc lecturer -pc student -ic RS                         patronPolicy
		 *     -pc ANY -ic ANY                    					   generalPolicy
		 */

		/*-
		 * Test
		 *  administrator	RS	administratorPolicy
		    administrator	OS	administratorPolicy
			cataloguingOfficer	RS	librarianPolicy
			cataloguingOfficer	OS	librarianPolicy
			circulationOfficer	RS	librarianPolicy
			circulationOfficer	OS	librarianPolicy
			lecturer	RS	patronPolicy
			lecturer	OS	generalPolicy
			student	RS	patronPolicy
			student	OS	generalPolicy
			ANY     ANY generalPolicy
		 */
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("pc", "administrator");
		criterias.put("ic", "OS");
		criterias.put("smd", "B");
		String expected = "administratorPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias.put("pc", "administrator");
		criterias.put("ic", "RS");
		expected = "administratorPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias.put("pc", "cataloguingOfficer");
		criterias.put("ic", "RS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias.put("pc", "cataloguingOfficer");
		criterias.put("ic", "OS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias.put("pc", "circulationOfficer");
		criterias.put("ic", "RS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias.put("pc", "circulationOfficer");
		criterias.put("ic", "OS");
		expected = "librarianPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		/*-
		 * 
		 *  lecturer	RS	patronPolicy
		 *	lecturer	OS	generalPolicy
		 */

		criterias.put("pc", "lecturer");
		criterias.put("ic", "RS");
		expected = "patronPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		criterias.put("pc", "lecturer");
		criterias.put("ic", "OS");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

		/*-
		 * 
		 *  student	RS	patronPolicy
		 *  student	OS	generalPolicy
		 */

		criterias.put("pc", "student");
		criterias.put("ic", "RS");
		expected = "patronPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
		criterias.put("pc", "student");
		criterias.put("ic", "OS");
		expected = "generalPolicy";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());
	}

	@Test
	public void testLookupWithThreeCriteriaAndTwoCommands() {
		List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
		LookupTable lookupTable = new LookupTable(ruleCommands);
		FirstMatchLookupStrategyImpl lookupStrategy = new FirstMatchLookupStrategyImpl(
				lookupTable);
		EligibilityRuleCommand command = new EligibilityRuleCommand();
		command.setEligibility("OS_LECTURER");
		command.getPatronCategories().add("lecturer");
		command.getItemCategories().add("OS");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("RS");
		command.getPatronCategories().add("ANY");
		command.getItemCategories().add("RS");
		ruleCommands.add(command);
		command = new EligibilityRuleCommand();
		command.setEligibility("GENERAL");
		command.getPatronCategories().add("ANY");
		command.getItemCategories().add("ANY");
		ruleCommands.add(command);
		Map<String, String> criterias = new HashMap<String, String>();
		criterias.put("pc", "STAFF");
		criterias.put("ic", "OS");
		criterias.put("smd", "B");
		String expected = "GENERAL";
		assertEquals(expected, lookupStrategy.lookup(criterias)
				.getEligibility());

	}
}

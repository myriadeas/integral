package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import my.com.myriadeas.integral.eligibility.exception.EligibilityRuleCommandNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class FirstMatchLookupStrategyImpl implements LookupStrategy {

	private static final Logger logger = LoggerFactory
			.getLogger(FirstMatchLookupStrategyImpl.class);

	private LookupTable tables;

	public LookupTable getTables() {
		return tables;
	}

	public void setTables(LookupTable tables) {
		this.tables = tables;
	}

	public FirstMatchLookupStrategyImpl(LookupTable tables) {
		Assert.notNull(tables);
		this.tables = tables;
	}

	@Override
	public EligibilityRuleCommand lookup(Map<String, String> criterias) {
		Assert.notNull(criterias);
		Assert.notEmpty(tables.getRuleCommands());
		EligibilityRuleCommand currentCommand = null;
		logger.debug("criterias={}", criterias);
		Map<String, List<EligibilityRuleCommand>> criteriaMatchedRuleCommands = new LinkedHashMap<String, List<EligibilityRuleCommand>>();
		List<EligibilityRuleCommand> criteriaMatchedRuleCommand = null;
		for (Entry<String, String> criteria : criterias.entrySet()) {
			criteriaMatchedRuleCommands.put(criteria.getKey(),
					getMatchedRuleCommands(criteria, tables.getRuleCommands()));
			criteriaMatchedRuleCommand = criteriaMatchedRuleCommands
					.get(criteria.getKey());
		}

		logger.debug("criteria matched rule commands={}",
				criteriaMatchedRuleCommands);

		boolean matched = false;

		for (EligibilityRuleCommand ruleCommand : criteriaMatchedRuleCommand) {

			for (Entry<String, List<EligibilityRuleCommand>> entry : criteriaMatchedRuleCommands
					.entrySet()) {
				logger.debug("Entry value={}", entry.getValue());
				logger.debug("rule command={}", ruleCommand);
				if (!entry.getValue().contains(ruleCommand)) {
					matched = false;
					break;
				} else {
					matched = true;
				}
			}
			if (matched) {
				currentCommand = ruleCommand;
				break;
			}
		}

		logger.debug("current command={}", currentCommand);
		if (matched && currentCommand != null) {
			logger.debug("matched command={}", currentCommand);
			return currentCommand;
		} else {
			throw new EligibilityRuleCommandNotFoundException("Eligibility Not Found");
		}
	}

	protected List<EligibilityRuleCommand> getMatchedRuleCommands(
			Entry<String, String> criteria,
			List<EligibilityRuleCommand> ruleCommands) {
		List<EligibilityRuleCommand> matchedRuleCommand = new ArrayList<EligibilityRuleCommand>();
		for (EligibilityRuleCommand command : ruleCommands) {
			List<String> rules = null;
			if (criteria.getKey().equals("pc")) {
				rules = command.getPatronCategories();
			}
			if (criteria.getKey().equals("ic")) {
				rules = command.getItemCategories();
			}
			if (criteria.getKey().equals("smd")) {
				rules = command.getSmds();
			}
			if (criteria.getKey().equals("location")) {
				rules = command.getBranches();
			}
			logger.debug("rules={}", rules);
			if (rules.size() == 0 || rules.contains("ANY")) {
				matchedRuleCommand.add(command);
			} else if (rules.contains(criteria.getValue())) {
				matchedRuleCommand.add(command);
			}
		}
		logger.debug("criteria={}, matched rule commands={}", criteria,
				matchedRuleCommand);
		return matchedRuleCommand;
	}
}

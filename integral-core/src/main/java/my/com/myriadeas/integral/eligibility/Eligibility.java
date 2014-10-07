package my.com.myriadeas.integral.eligibility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

public class Eligibility<T> implements EligibilityCriteria {

	private EligibilityRuleCommand command;

	private T domain;

	private Map<String, Boolean> criterias;

	// Prevent empty constructor
	Eligibility() {

	}

	public Eligibility(EligibilityRuleCommand command, T domain) {
		Assert.notNull(command);
		Assert.notNull(domain);
		this.command = command;
		this.domain = domain;
		if (criterias == null) {
			criterias = new HashMap<String, Boolean>();
			criterias.put(PATRON_CATEGORY,
					command.getPatronCategories().size() > 0);
			criterias
					.put(ITEM_CATEGORY, command.getItemCategories().size() > 0);
			criterias.put(SMD, command.getSmds().size() > 0);
			criterias.put(BRANCH, command.getBranches().size() > 0);
		}
	}

	public EligibilityRuleCommand getCommand() {
		return command;
	}

	public T getDomain() {
		return domain;
	}

	public Map<String, Boolean> getCriterias() {
		return criterias;
	}

	public Boolean isPatronCategory() {
		return criterias.get(PATRON_CATEGORY);
	}

	public Boolean isItemCategory() {
		return criterias.get(ITEM_CATEGORY);
	}

	public Boolean isSmd() {
		return criterias.get(SMD);
	}

	public Boolean isBranch() {
		return criterias.get(BRANCH);
	}

	@Override
	public String toString() {
		return "Eligibility [command=" + command + ", domain=" + domain
				+ ", criterias=" + criterias + "]";
	}
	
	

}

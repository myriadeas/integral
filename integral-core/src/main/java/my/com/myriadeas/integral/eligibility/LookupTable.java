package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

public class LookupTable {
	
	private List<EligibilityRuleCommand> ruleCommands= new ArrayList<EligibilityRuleCommand>();

	public LookupTable() {
		
	}
	
	public LookupTable(List<EligibilityRuleCommand> ruleCommands) {
		Assert.notNull(ruleCommands);
		this.ruleCommands = ruleCommands;
	}
	
	public List<EligibilityRuleCommand> getRuleCommands() {
		return ruleCommands;
	}

	@Override
	public String toString() {
		return "LookupTable [ruleCommands=" + ruleCommands + "]";
	}
	
}

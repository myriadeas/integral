package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.beust.jcommander.JCommander;

public class LookupTableFactoryBean implements FactoryBean<LookupTable> {

	private LookupTable instance;

	private List<String> commands;
	
	public List<String> getCommands() {
		return commands;
	}

	public void setCommands(List<String> commands) {
		this.commands = commands;
	}

	@Override
	public LookupTable getObject() throws Exception {
		if (instance == null) {
			List<EligibilityRuleCommand> ruleCommands = new ArrayList<EligibilityRuleCommand>();
			for(String command : commands) {
				EligibilityRuleCommand ruleCommand = new EligibilityRuleCommand();
				String[] argv = command.split(" ");
				new JCommander(ruleCommand, argv);
				ruleCommands.add(ruleCommand);
			}
			instance = new LookupTable(ruleCommands);
		}
		return instance;
	}

	@Override
	public Class<?> getObjectType() {
		return LookupTable.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}

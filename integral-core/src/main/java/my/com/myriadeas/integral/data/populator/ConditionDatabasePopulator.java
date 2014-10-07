package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Condition;
import my.com.myriadeas.integral.data.jpa.repositories.ConditionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ConditionDatabasePopulator")
public class ConditionDatabasePopulator extends AbstractDatabasePopulator implements ConditionDatabasePopulatorIntf {

	@Autowired
	private ConditionRepository conditionRepository;

	public void init() {
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(GOOD_CONDITION);
		conditions.add(TORN);
		conditions.add(MISSING_PAGE);
		conditions.add(DOG_EARED);
		conditionRepository.save(conditions);
	}


}

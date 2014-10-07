package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.State;
import my.com.myriadeas.integral.data.jpa.repositories.impl.StateRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "stateDatabasePopulator")
public class StateDatabasePopulator extends AbstractDatabasePopulator implements StateDatabasePopulatorIntf {

	@Autowired
	private StateRepositoryImpl stateRepository;

	public void init() {
		List<State> states = new ArrayList<State>();
		states.add(KUALA_LUMPUR);
		states.add(SELANGOR);
		states.add(PENANG);
		stateRepository.save(states);
	}

}

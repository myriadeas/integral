package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;
import my.com.myriadeas.integral.data.jpa.repositories.impl.WorkingDayRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "workingDayDatabasePopulator")
public class WorkingDayDatabasePopulator extends AbstractDatabasePopulator
		implements WorkingDayDatabasePopulatorIntf {

	@Autowired
	private WorkingDayRepositoryImpl workingDayRepository;

	public void init() {
		List<WorkingDay> workingdays = new ArrayList<WorkingDay>();
		SUNDAY.setBranch(MAIN_BRANCH);
		SUNDAY.setCode("MAIN_1");
		SUNDAY.setDayOfWeek(1);
		SUNDAY.setWorking(false);
		workingdays.add(SUNDAY);
		workingDayRepository.save(workingdays);

	}

}

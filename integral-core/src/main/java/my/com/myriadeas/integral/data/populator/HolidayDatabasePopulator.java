package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Holiday;
import my.com.myriadeas.integral.data.jpa.repositories.impl.HolidayRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "holidayDatabasePopulator")
public class HolidayDatabasePopulator extends AbstractDatabasePopulator
		implements HolidayDatabasePopulatorIntf {

	@Autowired
	private HolidayRepositoryImpl holidayRepository;

	public void init() {
		List<Holiday> holidays = new ArrayList<Holiday>();
		NEW_YEAR.setBranch(MAIN_BRANCH);
		holidays.add(NEW_YEAR);
		CHINESE_NEW_YEAR.setBranch(MAIN_BRANCH);
		holidays.add(CHINESE_NEW_YEAR);
		holidayRepository.save(holidays);

	}

}

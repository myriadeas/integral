package my.com.myriadeas.integral.holiday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import my.com.myriadeas.WeekBusinessHour;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;
import my.com.myriadeas.integral.data.jpa.repositories.WorkingDayRepository;
import my.com.myriadeas.integral.data.populator.WorkingDayDatabasePopulatorIntf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("WeekBusinessHourService")
public class WeekBusinessHourServiceImpl implements
		WeekBusinessHourService<Branch> {

	private static final Logger logger = LoggerFactory
			.getLogger(WeekBusinessHourServiceImpl.class);

	private static Map<Branch, WeekBusinessHour> cachedWeekBusinessHours;

	@Autowired
	private WorkingDayRepository workingDayRepository;
	
	//@Autowired
	//private WorkingDayDatabasePopulatorIntf workingDayDatabasePopulator;


	private WeekBusinessHour weekBusinessHour;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();

	}

	protected void init() {
		setupWeekBusinessHours(workingDayRepository.findAll());
	}

	protected void setupWeekBusinessHours(List<WorkingDay> glworkingdays) {
		logger.debug("Entering setupWeekBusinessHours={}", glworkingdays);
		cachedWeekBusinessHours = new HashMap<Branch, WeekBusinessHour>();

		Map<Branch, Map<Integer, String>> branchWeekBusinessHours = new HashMap<Branch, Map<Integer, String>>();

		for (WorkingDay glworkingDay : glworkingdays) {
			if (!branchWeekBusinessHours
					.containsKey(glworkingDay.getBranch())) {
				Map<Integer, String> weekBusinessHours = new HashMap<Integer, String>();
				branchWeekBusinessHours.put(glworkingDay.getBranch(),
						weekBusinessHours);
				
			}
			logger.debug("glworkingDay.getDayOfWeek() = " + glworkingDay.getDayOfWeek());
			logger.debug("glworkingDay.getWorkingHours() = " + glworkingDay.getWorkingHours());
			branchWeekBusinessHours.get(glworkingDay.getBranch()).put(
					glworkingDay.getDayOfWeek(),
					glworkingDay.getWorkingHours());
		}

		for (Entry<Branch, Map<Integer, String>> entry : branchWeekBusinessHours
				.entrySet()) {
			Branch glbrnc = entry.getKey();
			Map<Integer, String> mapWeekBusinessHour = entry.getValue();
			WeekBusinessHour weekBusinessHour = new DefaultWeekBusinessHour();
			if (!mapWeekBusinessHour.isEmpty()){
				weekBusinessHour = new WeekBusinessHour(
					mapWeekBusinessHour);
			}
			cachedWeekBusinessHours.put(glbrnc, weekBusinessHour);
		}
		
		logger.debug("Leaving setupWeekBusinessHours. cachedWeekBusinessHours={}", cachedWeekBusinessHours);
	}

	@Override
	public Map<Branch, WeekBusinessHour> getWeekBusinessHour()
			throws WeekBusinessHourEmptyException {
		if (cachedWeekBusinessHours == null) {
			init();
		}
		if (cachedWeekBusinessHours.isEmpty()) {
			throw new WeekBusinessHourEmptyException("business hour is empty");
		}
		return cachedWeekBusinessHours;
	}

	@Override
	public WeekBusinessHour getWeekBusinessHour(Branch identity)
			throws WeekBusinessHourNotFoundException {
		WeekBusinessHour weekBusinessHour = getWeekBusinessHour().get(identity);
		return weekBusinessHour;
	}

	@Override
	public void setDefaultWeekBusinessHour(WeekBusinessHour weekBusinessHour) {
		this.weekBusinessHour = weekBusinessHour;
	}
}

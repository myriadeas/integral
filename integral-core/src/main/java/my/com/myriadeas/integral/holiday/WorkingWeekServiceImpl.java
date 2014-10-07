package my.com.myriadeas.integral.holiday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;
import my.com.myriadeas.integral.data.jpa.repositories.WorkingDayRepository;
import my.com.myriadeas.integral.data.populator.WorkingDayDatabasePopulatorIntf;
import net.objectlab.kit.datecalc.common.WorkingWeek;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("workingWeekService")
public class WorkingWeekServiceImpl implements WorkingWeekService<Branch> {

	private static final Logger logger = LoggerFactory
			.getLogger(WorkingWeekServiceImpl.class);

	private static Map<Branch, JodaWorkingWeek> cachedWorkingWeeks;

	@Autowired
	private WorkingDayRepository glworkingdayRepository;
	
	//@Autowired
	//private WorkingDayDatabasePopulatorIntf workingDayDatabasePopulator;


	private JodaWorkingWeek defaultWorkingWeek = new FullWorkingWeek();

	@Override
	public void afterPropertiesSet() throws Exception {
		init();

	}

	protected void init() {
		setupWorkingWeeks(glworkingdayRepository.findAll());
	}

	protected void setupWorkingWeeks(List<WorkingDay> glworkingdays) {
		logger.debug("Entering setupWorkingWeeks={}", glworkingdays);
		if (cachedWorkingWeeks == null) {
			logger.debug("initialize cachedWorkingWeeks");
			cachedWorkingWeeks = new HashMap<Branch, JodaWorkingWeek>();
		}
		for (WorkingDay glworkingday : glworkingdays) {
			if (!cachedWorkingWeeks.containsKey(glworkingday.getBranch())) {
				cachedWorkingWeeks.put(glworkingday.getBranch(),
						this.defaultWorkingWeek);
			}
			JodaWorkingWeek newWorkingWeek = populateWorkingWeek(
					cachedWorkingWeeks.get(glworkingday.getBranch()),
					glworkingday);
			cachedWorkingWeeks.put(glworkingday.getBranch(), newWorkingWeek);
		}
		
		logger.debug("cachedWorkingWeeks={}", cachedWorkingWeeks);
	}

	protected JodaWorkingWeek populateWorkingWeek(JodaWorkingWeek workingWeek,
			WorkingDay glworkingday) {
		logger.debug(
				"Entering populateWorkingWeek(workingWeek={}, glworkingday={})",
				workingWeek, glworkingday);
		workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(
				glworkingday.getWorking(), glworkingday.getDayOfWeek());
		logger.debug("Leaving populateWorkingWeek()workingWeek={})",
				workingWeek);
		return workingWeek;
	}

	protected void destroy() {

	}

	@Override
	public Map<Branch, JodaWorkingWeek> getWorkingWeek()
			throws WorkingWeekEmptyException {
		if (cachedWorkingWeeks == null) {
			init();
		}
		if (cachedWorkingWeeks.isEmpty()) {
			throw new WorkingWeekEmptyException("working week is empty");
		}
		return cachedWorkingWeeks;
	}

	@Override
	public JodaWorkingWeek getWorkingWeek(Branch identity)
			throws WorkingWeekNotFoundException, WorkingWeekEmptyException {
		if (!getWorkingWeek().containsKey(identity)) {
			throw new WorkingWeekNotFoundException("Identity = " + identity
					+ "'s working week not found");
		}
		return getWorkingWeek().get(identity);
	}

	@Override
	public void setDateRule(DateRule dateRule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefaultWorkingWeek(JodaWorkingWeek workingWeek) {
		this.defaultWorkingWeek = workingWeek;
	}

}

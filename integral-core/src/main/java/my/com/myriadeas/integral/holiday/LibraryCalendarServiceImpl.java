package my.com.myriadeas.integral.holiday;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import my.com.myriadeas.BusinessCalendarCalculator;
import my.com.myriadeas.BusinessHour;
import my.com.myriadeas.BusinessHourCalendarFactory;
import my.com.myriadeas.WeekBusinessHour;
import my.com.myriadeas.integral.bean.ReloadableBean;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.repositories.HolidayRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.HolidayRepositoryImpl;
import my.com.myriadeas.integral.data.populator.HolidayDatabasePopulatorIntf;
import my.com.myriadeas.integral.data.populator.WorkingDayDatabasePopulatorIntf;
import net.objectlab.kit.datecalc.common.DateCalculator;
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayCalendar;
import net.objectlab.kit.datecalc.common.HolidayHandlerType;
import net.objectlab.kit.datecalc.common.WorkingWeek;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("libraryCalendarService")
public class LibraryCalendarServiceImpl implements
		LibraryCalendarService<Branch>, ReloadableBean{

	private static final Logger logger = LoggerFactory
			.getLogger(LibraryCalendarService.class);

	private static Map<Branch, DateCalculator<LocalDate>> cachedLibraryCalendar;

	

	@Autowired
	private HolidayService<Branch> holidayService;

	@Autowired
	private WorkingWeekService<Branch> workingWeekService;

	@Autowired
	private WeekBusinessHourService<Branch> weekBusinessHourService;
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	protected void init() {
		Map<Branch, Set<LocalDate>> holidaysMap = new HashMap<Branch, Set<LocalDate>>();
		Map<Branch, JodaWorkingWeek> workingWeekMap = new HashMap<Branch, JodaWorkingWeek>();
		Map<Branch, WeekBusinessHour> weekBusinessHourMap = new HashMap<Branch, WeekBusinessHour>();
		
		try {
			holidaysMap = holidayService.getHolidays();			
		} catch (EmptyHolidayException e) {}
		
		try {
			workingWeekMap = workingWeekService.getWorkingWeek();
		} catch (WorkingWeekEmptyException e){			
		}
		
		try {
			weekBusinessHourMap = weekBusinessHourService.getWeekBusinessHour();			
		} catch (WeekBusinessHourEmptyException e){			
		}
		
		
		
		setupLibraryCalendarWithHolidays(holidaysMap,
				holidayService.getEarlyBoundary(),
				holidayService.getLateBoundary(),
				workingWeekMap,
				weekBusinessHourMap);

	}

	protected void setupLibraryCalendarWithHolidays(
			Map<Branch, Set<LocalDate>> allHolidays, LocalDate earlyBoundary,
			LocalDate lateBoundary, Map<Branch, JodaWorkingWeek> allWorkingWeeks,
			Map<Branch, WeekBusinessHour> allWeekBusinessHours) {
		logger.debug(
				"Entering setupLibraryCalendarWithHolidays(allHolidays={}, earlyBoundary={}, "
						+ "lateBoundary={}, allWorkingWeeks={})", new Object[] {
						allHolidays, earlyBoundary, lateBoundary,
						allWorkingWeeks });
		if (cachedLibraryCalendar == null) {
			cachedLibraryCalendar = new HashMap<Branch, DateCalculator<LocalDate>>();
		}
		
		for (Entry<Branch, Set<LocalDate>> branchHolidays : allHolidays
				.entrySet()) {
			
			JodaWorkingWeek workingWeek = allWorkingWeeks
					.containsKey(branchHolidays.getKey()) ? allWorkingWeeks
					.get(branchHolidays.getKey()) : new FullWorkingWeek();
			WeekBusinessHour weekBusinessHour = allWeekBusinessHours
					.containsKey(branchHolidays.getKey()) ? allWeekBusinessHours
					.get(branchHolidays.getKey()) : new DefaultWeekBusinessHour();
			cachedLibraryCalendar.put(
					branchHolidays.getKey(),
					populateDateCalculator(branchHolidays.getKey(),
							branchHolidays.getValue(), earlyBoundary,
							lateBoundary, 
							workingWeek, 
							weekBusinessHour,
							new LocalDate()));
			
		}

		logger.debug(
				"Leaving setupLibraryCalendarWithHolidays(). cachedLibraryCalendar={}",
				cachedLibraryCalendar);

	}

	protected BusinessCalendarCalculator populateDateCalculator(
			Branch identity, final Set<LocalDate> holidays,
			LocalDate earlyBoundary, LocalDate lateBoundary,
			JodaWorkingWeek workingWeek, WeekBusinessHour weekBusinessHour, LocalDate startDate) {

		logger.debug(
				"Entering populateDateCalculator(branch={}, holidays={}, "
						+ "earlyBoundary={}, lateBoundary={}," +
						"workingWeek={}, weekBusinessHour={})", new Object[] {
								identity, holidays, earlyBoundary, lateBoundary,
								workingWeek, weekBusinessHour});
		
		final HolidayCalendar<LocalDate> calendar = new DefaultHolidayCalendar<LocalDate>(
				holidays, earlyBoundary, lateBoundary);
		/*
		BusinessHourCalendarFactory.getDefaultInstance().registerHolidays(
				identity.getCode(), calendar);
		
		logger.debug("Successfully registered holidays");
		
		BusinessCalendarCalculator cal = BusinessHourCalendarFactory
				.getDefaultInstance().getBusinessCalendarCalculator(
						identity.getCode(), HolidayHandlerType.FORWARD,
						workingweek, weekBusinessHour);
		
		*
		*/
		BusinessHourCalendarFactory businessHourCalendarFactory = new BusinessHourCalendarFactory();
		BusinessCalendarCalculator cal = businessHourCalendarFactory.getBusinessCalendarCalculator(
				identity.getCode(), calendar, HolidayHandlerType.FORWARD, workingWeek, weekBusinessHour, startDate);
		
		
		logger.debug(
				"Leaving populateDateCalculator(). BusinessCalendarCalculator={}",
				cal);
		return cal;
	}

	/*-
	protected DateCalculator populateDateCalculator(Glbrnc identity,
			final Set<LocalDate> holidays, LocalDate earlyBoundary,
			LocalDate lateBoundary, WorkingWeek workingweek) {
		
		final HolidayCalendar<LocalDate> calendar = new DefaultHolidayCalendar<LocalDate>(
				holidays, earlyBoundary, lateBoundary);
		LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays(
				identity.getGl71brnc(), calendar);
		DateCalculator<LocalDate> cal = LocalDateKitCalculatorsFactory
				.getDefaultInstance().getDateCalculator(identity.getGl71brnc(),
						HolidayHandlerType.FORWARD);
		
		cal.setWorkingWeek(workingweek);
		return cal;
	}*/

	@Override
	public Map<Branch, DateCalculator<LocalDate>> getDateCalculator() {
		if (cachedLibraryCalendar == null) {
			init();
		}
		return cachedLibraryCalendar;
	}

	@Override
	public BusinessCalendarCalculator getDateCalculator(Branch brnch)
			throws DateCalculatorNotFoundException {
		return getDateCalculator(brnch, new LocalDate());
	}

	public BusinessCalendarCalculator getDateCalculator(Branch brnch, LocalDate startDate)
			throws DateCalculatorNotFoundException {
		
		holidayService.setEarlyBoundary(startDate);
		
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		Map<Branch, JodaWorkingWeek> workingWeekMap = new HashMap<Branch, JodaWorkingWeek>();
		WeekBusinessHour weekBusinessHour = null;
		try {
			holidays = holidayService.getHolidays(brnch);			
		} catch (EmptyHolidayException e) {
			logger.warn("Empty holiday. 365 working?");
		}
		
		try {
			workingWeekMap = workingWeekService.getWorkingWeek();			
		} catch (WorkingWeekEmptyException e){
			logger.warn("Empty working week. Work everyday?");
		}
		
		try {
			weekBusinessHour = weekBusinessHourService.getWeekBusinessHour(brnch);
		} catch (WeekBusinessHourEmptyException e){	
			logger.warn("Holiday not found for branch= {}. No holiday or forget to setup?", brnch);
			weekBusinessHour = new DefaultWeekBusinessHour();
		}
		
		BusinessCalendarCalculator dateCalculator = this
				.populateDateCalculator(
						brnch,
						holidays,
						holidayService.getEarlyBoundary(),
						holidayService.getLateBoundary(),
						workingWeekMap.containsKey(brnch) ? workingWeekService
								.getWorkingWeek().get(brnch)
								: new FullWorkingWeek(),
						weekBusinessHour,
						startDate);
		//dateCalculator.setStartDate(startDate);
		return dateCalculator;
	}

	@Override
	public void setHolidayService(HolidayService<Branch> holidayService) {
		this.holidayService = holidayService;

	}

	@Override
	public void setWorkingWeekService(
			WorkingWeekService<Branch> workingWeekService) {
		this.workingWeekService = workingWeekService;
	}

	@Override
	public void reload() {
		init();		
	}

	/*
	 * @Override public WorkingWeekService<Glbrnc> getWorkingWeekService() {
	 * return workingWeekService; }
	 */

}

package my.com.myriadeas.integral.holiday;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import my.com.myriadeas.integral.bean.ReloadableBean;
import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Holiday;
import my.com.myriadeas.integral.data.jpa.repositories.impl.HolidayRepositoryImpl;
import my.com.myriadeas.integral.data.populator.HolidayDatabasePopulatorIntf;
import my.com.myriadeas.integral.data.populator.WorkingDayDatabasePopulatorIntf;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("holidayService")
public class HolidayServiceImpl implements HolidayService<Branch>, ReloadableBean {

	protected static Map<Branch, Set<LocalDate>> cachedHolidays;

	private static final Logger logger = LoggerFactory
			.getLogger(HolidayServiceImpl.class);

	
	private HolidayRepositoryImpl holidayRepo;

	private LocalDate earlyBoundary = new LocalDate();

	private LocalDate lateBoundary = earlyBoundary.plusYears(1);
	
	//@Autowired
	//private HolidayDatabasePopulatorIntf holidayDatabasePopulator;
	
	
	
	@Autowired
	public void setGlholidayRepository(HolidayRepositoryImpl holidayRepo){
		this.holidayRepo = holidayRepo;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	protected void init() {
		setupHoliday(holidayRepo.findAll());
	}

	protected void destroy() {
		cachedHolidays = null;
	}

	protected void setupHoliday(List<Holiday> glholidays)
			throws EmptyHolidayException {
		logger.debug("Entering setupHoliday(glholidays={})", glholidays);
		if (cachedHolidays == null) {
			logger.debug("initialize cachedHolidays");
			cachedHolidays = new HashMap<Branch, Set<LocalDate>>();
		}
		for (Holiday glholiday : glholidays) {
			
			if (!cachedHolidays.containsKey(glholiday.getBranch())) {
				cachedHolidays.put(glholiday.getBranch(),
						new HashSet<LocalDate>());
			}
			cachedHolidays.get(glholiday.getBranch()).addAll(
					populateHolidays(glholiday));
		}

		logger.debug("cached holidays={}", cachedHolidays);
		logger.debug("Leaving setupHoliday()");
	}

	/**
	 * TODO - change of year will create a problem when there is any holiday is repeatable 
	 * but not fixed on the same date like Chinese New Year
	 * @param glholiday
	 * @return
	 * @throws EmptyHolidayException
	 */
	protected Set<LocalDate> populateHolidays(Holiday glholiday)
			throws EmptyHolidayException {

		logger.debug("Entering populateHolidays(glholiday={})", glholiday);
		Set<LocalDate> holidays = new HashSet<LocalDate>();
		
		int year = earlyBoundary.getYear();		
		while (year <= lateBoundary.getYear()) {
			LocalDate startDate = new LocalDate(year,
					glholiday.getStartMonth(),
					glholiday.getStartDayOfMonth());
			LocalDate endDate = new LocalDate(year,
					glholiday.getEndMonth(), glholiday.getEndDayOfMonth());
			if (endDate.isBefore(startDate)) {
				logger.error("End date is before start date");
				throw new EmptyHolidayException("End date" + endDate
						+ " is before start date " + startDate);
			}
			
			holidays = populateHolidays(holidays, startDate, endDate);
			year++;
		}
		
		logger.debug("Leaving populateHolidays(). holidays={}", holidays);
		return holidays;
	}
	
	protected Set<LocalDate> populateHolidays(Set<LocalDate> holidays, LocalDate startDate, LocalDate endDate){
		// add first date
		holidays = addToHolidays(holidays, startDate);
		
		// add in between
		holidays = addHolidayInBetween(holidays, startDate, endDate);
		
		// add last day
		holidays = addToHolidays(holidays, endDate);
		
		return holidays;
	}
	
	protected Set<LocalDate> addHolidayInBetween(Set<LocalDate> holidays, LocalDate startDate, LocalDate endDate){
		int days = 1;
		
		while (startDate.plusDays(days).isBefore(endDate)) {
			holidays = addToHolidays(holidays, startDate.plusDays(days));
			days++;
		}
		
		return holidays;
	}
	
	protected Set<LocalDate> addToHolidays(Set<LocalDate> holidays, LocalDate date){
		if (isWithinBoundary(date)){
			holidays.add(date);
		}
		return holidays;
	}
	
	protected boolean isWithinBoundary(LocalDate date){
		if ((date.isEqual(earlyBoundary) || date.isAfter(earlyBoundary)) &&
				(date.isBefore(lateBoundary) || date.isEqual(lateBoundary))){
			return true;
		}
		return false;
	}

	@Override
	public Map<Branch, Set<LocalDate>> getHolidays()
			throws EmptyHolidayException {
		if (cachedHolidays == null) {
			init();
		}
		validate();
		return cachedHolidays;
	}

	@Override
	public Set<LocalDate> getHolidays(Branch glbrnc)
			throws HolidaysNotFoundException, EmptyHolidayException {
		if (!getHolidays().containsKey(glbrnc)) {
			throw new HolidaysNotFoundException("Identity= " + glbrnc
					+ " 's holiday not found");
		}
		return cachedHolidays.get(glbrnc);
	}

	@Override
	public void setDateRule(DateRule dateRule) {
	}

	@Override
	public void setEarlyBoundary(LocalDate earlyBoundary) {
		this.earlyBoundary = earlyBoundary;
		this.lateBoundary = earlyBoundary.plusYears(1);
	}

	/*
	@Override
	public void setLateBoundary(LocalDate lateBoundary) {
		this.lateBoundary = lateBoundary;
	}
	*/

	@Override
	public LocalDate getEarlyBoundary() {
		return this.earlyBoundary;
	}

	@Override
	public LocalDate getLateBoundary() {
		return this.lateBoundary;
	}

	private void validate() throws EmptyHolidayException {
		if (cachedHolidays.isEmpty()) {
			throw new EmptyHolidayException("Holiday is empty");
		}
	}

	@Override
	public void reload() {
		init();		
	}
}

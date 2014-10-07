package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.data.jpa.repositories.impl.HolidayRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the GLHOLIDAY database table.
 * 
 */
@Entity
@Configurable
/*
 * @ScriptAssert.List({
 * 
 * @ScriptAssert(lang = "javascript", script =
 * "_this.gl30startMonth > _this.gl30endMonth", message =
 * "glholiday.startMonthGreaterThanEndMonth.error"),
 * 
 * @ScriptAssert(lang = "javascript", script =
 * "if(_this.gl30startMonth == _this.gl30endMonth){}", message =
 * "glholiday.startDayOfMonthGreaterThanEndDayOfMonth.error") })
 */
public class Holiday extends AbstractLookupDomain {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Max(31)
	@Min(1)
	private Integer startDayOfMonth;

	@NotNull
	@Max(31)
	@Min(1)
	private Integer endDayOfMonth;

	@NotNull
	@Max(12)
	@Min(1)
	private Integer startMonth;

	@NotNull
	@Max(12)
	@Min(1)
	private Integer endMonth;

	@ManyToOne(optional = false)
	private Branch branch;

	@JsonIgnore
	@Transient
	private HolidayRepositoryImpl holidayRepo;

	public Holiday() {
	}

	public Holiday(String code, String description, Integer startDayOfMonth,
			Integer endDayOfMonth, Integer startMonth, Integer endMonth) {
		super(code, description);
		this.startDayOfMonth = startDayOfMonth;
		this.endDayOfMonth = endDayOfMonth;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
	}

	@JsonIgnore
	@Autowired
	public void setHolidayRepo(HolidayRepositoryImpl holidayRepo) {
		this.holidayRepo = holidayRepo;
	}

	public Integer getStartDayOfMonth() {
		return startDayOfMonth;
	}

	public void setStartDayOfMonth(Integer startDayOfMonth) {
		this.startDayOfMonth = startDayOfMonth;
	}

	public Integer getEndDayOfMonth() {
		return endDayOfMonth;
	}

	public void setEndDayOfMonth(Integer endDayOfMonth) {
		this.endDayOfMonth = endDayOfMonth;
	}

	public Integer getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(Integer startMonth) {
		this.startMonth = startMonth;
	}

	public Integer getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(Integer endMonth) {
		this.endMonth = endMonth;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
}
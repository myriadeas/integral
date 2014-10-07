package my.com.myriadeas.integral.data.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The persistent class for the GLHOLIDAY database table.
 * 
 */
@Entity
@Configurable
public class WorkingDay extends AbstractLookupDomain {

	private static final Logger logger = LoggerFactory
			.getLogger(WorkingDay.class);

	private static final long serialVersionUID = 1L;

	@Max(7)
	@Min(1)
	private Integer dayOfWeek;

	@NotNull
	private Boolean working;

	@ManyToOne(optional = false)
	private Branch branch;

	private String workingHours;

	public WorkingDay() {
	}
	
	public WorkingDay(String code, String description) {
		super(code, description);
	}


	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Boolean getWorking() {
		return working;
	}

	public void setWorking(Boolean working) {
		this.working = working;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

}
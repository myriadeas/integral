package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.WorkingDay;
import my.com.myriadeas.integral.data.jpa.repositories.WorkingDayRepository;

import org.springframework.data.repository.query.Param;

public interface WorkingDayRepositoryImpl extends WorkingDayRepository {
	
	public List<WorkingDay> findByCode(@Param("code") String code);
	
	public List<WorkingDay> findByBranch(@Param("branch") Branch branch);
	
	public List<WorkingDay> findByBranchAndWorking(@Param("branch") Branch branch,
			@Param("working") boolean working);
	
	public List<WorkingDay> findByBranchAndDayOfWeek(@Param("branch") Branch branch,
			@Param("dayOfWeek") int dayOfWeek);

}
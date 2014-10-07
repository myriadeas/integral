package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.domain.Holiday;

import org.springframework.data.repository.query.Param;

public interface HolidayRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.HolidayRepository{
	public List<Holiday> findByBranch(@Param("branch") Branch branch);
}
package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Department;
import my.com.myriadeas.integral.data.jpa.repositories.DepartmentRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepositoryImpl extends DepartmentRepository {

	public List<Department> findByCodeContaining(@Param("search") String code,
			Pageable pageable);

	public List<Department> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);

}
package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Course;
import my.com.myriadeas.integral.data.jpa.repositories.CourseRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface CourseRepositoryImpl extends CourseRepository {

	public List<Course> findByCodeContaining(
			@Param("search") String code, Pageable pageable);

	public List<Course> findByDescriptionContaining(
			@Param("search") String description, Pageable pageable);
}
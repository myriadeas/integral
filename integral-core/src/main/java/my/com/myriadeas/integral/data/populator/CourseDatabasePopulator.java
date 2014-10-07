package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Course;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CourseRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "CourseDatabasePopulator")
public class CourseDatabasePopulator extends AbstractDatabasePopulator implements CourseDatabasePopulatorIntf {

	@Autowired
	private CourseRepositoryImpl courseRepository;

	public void init() {
		List<Course> courses = new ArrayList<Course>();
		courses.add(COMPUTER_SCIENCE);
		courses.add(COMPUTER_ENGINEERING);
		courseRepository.save(courses);
	}


}

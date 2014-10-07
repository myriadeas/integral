package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Course;

public interface CourseData {
	Course COMPUTER_ENGINEERING = new Course("SCC", "Computer Engineering");
	Course COMPUTER_SCIENCE = new Course("SCK", "Computer Science");
}

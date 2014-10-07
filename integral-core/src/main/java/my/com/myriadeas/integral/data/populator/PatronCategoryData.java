package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.PatronCategory;

public interface PatronCategoryData {
	PatronCategory STUDENT_CATEGORY = new PatronCategory("STUDENT", "Student");
	PatronCategory LECTURER_CATEGORY = new PatronCategory("LECTURER", "Lecturer");
	PatronCategory STAFF_CATEGORY = new PatronCategory("STAFF", "Staff");
}

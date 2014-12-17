package my.com.myriadeas.integral.circulation2.data.populator;

import my.com.myriadeas.integral.circulation2.domain.model.PatronCategory;

public interface PatronCategoryData {
	PatronCategory LECTURER = new PatronCategory("LEC", "Lecturer");
	PatronCategory STUDENT = new PatronCategory("STU", "Student");
}

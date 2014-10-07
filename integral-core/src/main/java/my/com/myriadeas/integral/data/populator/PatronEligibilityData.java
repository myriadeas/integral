package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;

public interface PatronEligibilityData {
	PatronEligibility STAFF_ELIGIBILITY = new PatronEligibility("STAFF", "Staff Policy");
	PatronEligibility STUDENT_ELIGIBILITY = new PatronEligibility("STUDENT", "Student Policy");
	PatronEligibility LECTURER_ELIGIBILITY = new PatronEligibility("LECTURER",
			"Lecturer Policy");
	
}

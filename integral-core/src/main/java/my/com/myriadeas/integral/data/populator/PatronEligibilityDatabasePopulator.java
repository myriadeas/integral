package my.com.myriadeas.integral.data.populator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.PatronEligibility;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronEligibilityRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "patronEligibilityDatabasePopulator")
public class PatronEligibilityDatabasePopulator extends AbstractDatabasePopulator implements
		PatronEligibilityDatabasePopulatorIntf {

	@Autowired
	private PatronEligibilityRepositoryImpl patronEligibilityRepository;

	public void init() {
		List<PatronEligibility> patronEligibilities = new ArrayList<PatronEligibility>();
		patronEligibilities.add(STAFF_ELIGIBILITY);
		STAFF_ELIGIBILITY.setWeight(200);
		STAFF_ELIGIBILITY.setCriteria("-pc STAFF");
		
		STUDENT_ELIGIBILITY.setMaxLoanAllowed(4);
		STUDENT_ELIGIBILITY.setAllowOverdue(false);
		STUDENT_ELIGIBILITY.setAllowReserve(false);
		STUDENT_ELIGIBILITY.setFinesLimit(new BigDecimal(10));	
		STUDENT_ELIGIBILITY.setCriteria("-pc ANY");
		STUDENT_ELIGIBILITY.setWeight(100);
		patronEligibilities.add(STUDENT_ELIGIBILITY);
		
		LECTURER_ELIGIBILITY.setMaxLoanAllowed(5);
		LECTURER_ELIGIBILITY.setAllowOverdue(true);
		LECTURER_ELIGIBILITY.setAllowReserve(true);
		LECTURER_ELIGIBILITY.setFinesLimit(new BigDecimal(20));
		LECTURER_ELIGIBILITY.setMaxReservationAllowed(5);
		patronEligibilities.add(LECTURER_ELIGIBILITY);
		LECTURER_ELIGIBILITY.setCriteria("-pc LECTURER");
		LECTURER_ELIGIBILITY.setWeight(1000);
		
		patronEligibilityRepository.save(patronEligibilities);
	}


}

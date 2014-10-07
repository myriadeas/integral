package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Designation;
import my.com.myriadeas.integral.data.jpa.repositories.impl.DesignationRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "designationDatabasePopulator")
public class DesignationDatabasePopulator extends AbstractDatabasePopulator implements DesignationDatabasePopulatorIntf {

	@Autowired
	private DesignationRepositoryImpl designationRepository;

	public void init() {
		List<Designation> designations = new ArrayList<Designation>();
		designations.add(ACCOUNTANT);
		designations.add(CHAIRMAN);
		designations.add(CHIEF_ACCOUNTANT);
		designationRepository.save(designations);
	}


}

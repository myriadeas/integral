package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Department;
import my.com.myriadeas.integral.data.jpa.repositories.impl.DepartmentRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "departmentDatabasePopulator")
public class DepartmentDatabasePopulator extends AbstractDatabasePopulator implements DepartmentDatabasePopulatorIntf {

	//@Autowired
	//private OfficerRepository officerRepository;
	
	@Autowired
	TownDatabasePopulatorIntf townDatabasePopulator;
	
	@Autowired
	OfficerDatabasePopulatorIntf officerDatabasePopulator;
	
	@Autowired
	private DepartmentRepositoryImpl departmentRepository;


	public void init() {
		List<Department> departments = new ArrayList<Department>();
		departments.add(SERVICES_DEPT);
		departments.add(FINANCE_DEPT);
		departments.add(MANAGEMENT_DEPT);
		departmentRepository.save(departments);
	}

}

package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Address;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.repositories.OfficerRepository;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronCategoryRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "officerDatabasePopulator")
public class OfficerDatabasePopulator extends AbstractDatabasePopulator
		implements OfficerDatabasePopulatorIntf {

	@Autowired
	OfficerRepository officerRepository;

	@Autowired
	BranchRepositoryImpl branchRepository;

	@Autowired
	PatronCategoryRepositoryImpl patronCategoryRepository;

	@Autowired
	BranchDatabasePopulatorIntf branchDatabasePopulator;

	@Autowired
	PatronCategoryDatabasePopulatorIntf patronCategoryDatabasePopulator;

	@Autowired
	RoleDatabasePopulatorIntf glroleDatabasePopulator;

	public void init() {
		List<Officer> officers = new ArrayList<Officer>();
		officers.add(ARLINA);
		ARLINA.getRoles().add(ROLE_SUPERUSER);
		officers.add(CHIAKAIYONG);
		officers.add(HUTINGUNG);
		officers.add(LIMSYENIE);
		officers.add(SIEWMEEYEE);
		officers.add(CHIAWEIWEI);
		officers.add(WONGSWEEKUAN);
		officerRepository.save(officers);
	}
}

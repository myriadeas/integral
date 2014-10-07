package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Address;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronCategoryRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "patronDatabasePopulator")
public class PatronDatabasePopulator extends AbstractDatabasePopulator
		implements PatronDatabasePopulatorIntf {

	@Autowired
	PatronRepositoryImpl patronRepository;

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

	@Autowired
	UserGroupDatabasePopulatorIntf userGroupDatabasePopulator;

	@Autowired
	TownDatabasePopulator townDatabasePopulator;
	

	@Autowired
	StateDatabasePopulator stateDatabasePopulator;

	public void init() {
		List<Patron> patrons = new ArrayList<Patron>();
		patrons.add(ARLINA);
		ARLINA.getRoles().add(ROLE_SUPERUSER);
		ARLINA.getUserGroups().add(CIRCULATION_GROUP);
		patrons.add(CHIAKAIYONG);
		CHIAKAIYONG.setHomeAddress(getRandomAddress());
		patrons.add(HUTINGUNG);
		patrons.add(LIMSYENIE);
		patrons.add(SIEWMEEYEE);
		patrons.add(CHIAWEIWEI);
		patrons.add(WONGSWEEKUAN);
		patronRepository.save(patrons);
	}

	private Address getRandomAddress() {
		Address address = new Address();
		address.setAddress1("No 55, SS2/10");
		address.setAddress2("");
		address.setAddress3("");
		address.setPostcode("46500");
		address.setTown(KL);
		address.setState(KUALA_LUMPUR);
		return address;
	}

}

package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Branch;
import my.com.myriadeas.integral.data.jpa.repositories.impl.BranchRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "branchDatabasePopulator")
public class BranchDatabasePopulator extends AbstractDatabasePopulator implements BranchDatabasePopulatorIntf {

	@Autowired
	private BranchRepositoryImpl branchRepository;

	public void init() {
		List<Branch> branches = new ArrayList<Branch>();
		branches.add(PJ_BRANCH);
		branches.add(MAIN_BRANCH);
		branches.add(KL_BRANCH);
		branchRepository.save(branches);
	}


}

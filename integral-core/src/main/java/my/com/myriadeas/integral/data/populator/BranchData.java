package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Branch;

public interface BranchData {
	Branch PJ_BRANCH = new Branch("PJ", "Petaling Jaya Library");
	Branch MAIN_BRANCH = new Branch("MAIN", "Main Library");
	Branch KL_BRANCH = new Branch("KL", "Kuala Lumpur Library");
}

package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Location;

public interface LocationData extends BranchData {
	Location LEVEL_ONE_PJ_BRANCH = new Location("LVL1", "Level One", PJ_BRANCH);
	Location LEVEL_TWO_PJ_BRANCH = new Location("LVL2", "Level Two", PJ_BRANCH);
	Location LEVEL_ONE_MAIN_BRANCH = new Location("LVL1", "Level ONE", MAIN_BRANCH);
	Location LEVEL_ONE_KL_BRANCH = new Location("LVL1", "Level ONE", KL_BRANCH);
}

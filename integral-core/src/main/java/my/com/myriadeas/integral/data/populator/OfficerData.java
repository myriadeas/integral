package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Officer;

public interface OfficerData extends BranchData, RoleData {
	Officer ARLINA = new Officer("arlina", "arlina", "Arlina binti Shah",
			KL_BRANCH);
	Officer CHIAKAIYONG = new Officer("chiakaiyong", "chiakaiyong",
			"Chia Kai Yong", PJ_BRANCH);
	Officer HUTINGUNG = new Officer("hutingung", "hutingung", "Hu Ting Ung",
			PJ_BRANCH);
	Officer LIMSYENIE = new Officer("limsyenie", "limsyenie", "Lim Sye Nie",
			MAIN_BRANCH);
	Officer SIEWMEEYEE = new Officer("siewmeeyee", "siewmeeyee",
			"Siew Mee Yee", MAIN_BRANCH);
	Officer CHIAWEIWEI = new Officer("chiaweiwei", "chiaweiwei",
			"Chia Wei Wei", MAIN_BRANCH);
	Officer WONGSWEEKUAN = new Officer("wongsweekuan", "wongsweekuan",
			"Wong Swee Kuan", MAIN_BRANCH);
}

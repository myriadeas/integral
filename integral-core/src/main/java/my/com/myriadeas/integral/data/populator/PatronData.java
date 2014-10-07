package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.joda.time.DateTime;

public interface PatronData extends BranchData, PatronCategoryData, RoleData, UserGroupData {
	Patron ARLINA = new Patron("arlina", "arlina", "Arlina binti Shah",
			new DateTime(2020, 1, 1, 1, 1).toDate(), KL_BRANCH, STAFF_CATEGORY);
	Patron CHIAKAIYONG = new Patron("chiakaiyong", "chiakaiyong",
			"Chia Kai Yong", new DateTime(2020, 1, 1, 1, 1).toDate(),
			PJ_BRANCH, LECTURER_CATEGORY);
	Patron HUTINGUNG = new Patron("hutingung", "hutingung", "Hu Ting Ung",
			new DateTime(2020, 1, 1, 1, 1).toDate(), PJ_BRANCH,
			STUDENT_CATEGORY);
	Patron LIMSYENIE = new Patron("limsyenie", "limsyenie", "Lim Sye Nie",
			new DateTime(2020, 1, 1, 1, 1).toDate(), MAIN_BRANCH,
			STAFF_CATEGORY);
	Patron SIEWMEEYEE = new Patron("siewmeeyee", "siewmeeyee", "Siew Mee Yee",
			new DateTime(2020, 1, 1, 1, 1).toDate(), MAIN_BRANCH,
			LECTURER_CATEGORY);
	Patron CHIAWEIWEI = new Patron("chiaweiwei", "chiaweiwei", "Chia Wei Wei",
			new DateTime(2020, 1, 1, 1, 1).toDate(), MAIN_BRANCH,
			LECTURER_CATEGORY);
	Patron WONGSWEEKUAN = new Patron("wongsweekuan", "wongsweekuan",
			"Wong Swee Kuan", new DateTime(2020, 1, 1, 1, 1).toDate(),
			MAIN_BRANCH, LECTURER_CATEGORY);
}

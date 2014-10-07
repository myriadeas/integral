package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Department;

public interface DepartmentData extends OfficerData, TownData {
	Department SERVICES_DEPT = new Department("BPK", "Services", ARLINA, PJ);
	Department FINANCE_DEPT = new Department("KEW", "Finance", SIEWMEEYEE, PJ);
	Department MANAGEMENT_DEPT = new Department("MAN", "Management", CHIAKAIYONG, PJ);
}

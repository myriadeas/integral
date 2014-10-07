package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Designation;

public interface DesignationData {
	Designation ACCOUNTANT = new Designation("ACC", "Accountant");
	Designation CHAIRMAN = new Designation("CMN", "Chairman");
	Designation CHIEF_ACCOUNTANT = new Designation("CACC", "Chief Accountant");
}

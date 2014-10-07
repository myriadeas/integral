package my.com.myriadeas.integral.holiday;

import net.objectlab.kit.datecalc.common.WorkingWeek;

public class FullNonWorkingWeek extends WorkingWeek {

	private static byte workingDays = (byte) 0;

	public FullNonWorkingWeek() {
		super(workingDays);
	}
}

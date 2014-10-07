package my.com.myriadeas.integral.holiday;

import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

public class FullWorkingWeek extends JodaWorkingWeek {
	private static final byte MONDAY = 1;

	private static final byte TUESDAY = 2;

	private static final byte WEDNESDAY = 4;

	private static final byte THURSDAY = 8;

	private static final byte FRIDAY = 16;

	private static final byte SATURDAY = 32;

	private static final byte SUNDAY = 64;

	private static final byte DEFAULT_WORKING_DAYS = (byte) (MONDAY + TUESDAY
			+ WEDNESDAY + THURSDAY + FRIDAY + SATURDAY + SUNDAY);

	private static byte workingDays = DEFAULT_WORKING_DAYS;

	public FullWorkingWeek() {
		super(workingDays);
	}
}

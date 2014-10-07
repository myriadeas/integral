package my.com.myriadeas.integral.holiday;

import java.util.Map;

import net.objectlab.kit.datecalc.common.WorkingWeek;
import net.objectlab.kit.datecalc.joda.JodaWorkingWeek;

import org.springframework.beans.factory.InitializingBean;

public interface WorkingWeekService<E> extends InitializingBean{

	public Map<E,JodaWorkingWeek> getWorkingWeek() throws WorkingWeekEmptyException;
	
	public WorkingWeek getWorkingWeek(E identity) throws WorkingWeekNotFoundException;
	
	public void setDateRule(DateRule dateRule);
	
	public void setDefaultWorkingWeek(JodaWorkingWeek workingWeek);
}

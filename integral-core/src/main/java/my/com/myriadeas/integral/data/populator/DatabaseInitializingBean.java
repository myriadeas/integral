package my.com.myriadeas.integral.data.populator;

import org.springframework.beans.factory.InitializingBean;

public interface DatabaseInitializingBean extends InitializingBean {

	public void init();
}

package my.com.myriadeas.integral.rest.config;

import javax.sql.DataSource;

/**
 * Interface class to define must-configured bean
 * 
 * @author hutingung
 * 
 */
public interface Config{

	public DataSource dataSource();
}

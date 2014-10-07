package my.com.myriadeas.integral.data.populator.config;

import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(SpringEnvironmentUtil.DEV)
@Configuration
@ComponentScan(basePackages = "my.com.myriadeas.integral.data.populator", excludeFilters = { @Filter(Configuration.class) })
public class DatabasePopulatorConfigDev {

}

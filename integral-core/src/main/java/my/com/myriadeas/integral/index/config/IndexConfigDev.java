package my.com.myriadeas.integral.index.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.config.SolrInfrastructureConfigDev;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Import(value = { JpaInfrastructureConfigDev.class})
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@Configuration
@Profile(DEV)
public class IndexConfigDev extends IndexCommonConfig{

}

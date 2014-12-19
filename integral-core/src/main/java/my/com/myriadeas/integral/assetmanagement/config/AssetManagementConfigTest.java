package my.com.myriadeas.integral.assetmanagement.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigTest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigTest.class })
@PropertySource(name = "properties", value = { "classpath:config-test.properties" })
@Configuration
@Profile(TEST)
@EnableTransactionManagement
public class AssetManagementConfigTest extends AssetManagementCommonConfig {

}

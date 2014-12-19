package my.com.myriadeas.integral.assetmanagement.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigStaging;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigStaging.class })
@PropertySource(name = "properties", value = { "classpath:config-staging.properties" })
@Configuration
@Profile(STAGING)
@EnableTransactionManagement
public class AssetManagementConfigStaging extends AssetManagementCommonConfig {

}

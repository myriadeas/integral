package my.com.myriadeas.integral.assetmanagement.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.DEV;
import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.STAGING;

import javax.sql.DataSource;

import my.com.myriadeas.integral.assetmanagement.AssetManagementConstant;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigDev;
import my.com.myriadeas.integral.config.JpaInfrastructureConfigStaging;
import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.core.publisher.PublisherImpl;
import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import(value = { JpaInfrastructureConfigDev.class })
@PropertySource(name = "properties", value = { "classpath:config-dev.properties" })
@Configuration
@Profile(DEV)
@EnableTransactionManagement
public class AssetManagementConfigDev extends AssetManagementCommonConfig {

}
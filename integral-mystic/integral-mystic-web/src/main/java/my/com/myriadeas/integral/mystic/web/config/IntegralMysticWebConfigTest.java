package my.com.myriadeas.integral.mystic.web.config;

import static my.com.myriadeas.spring.core.util.SpringEnvironmentUtil.TEST;
import my.com.myriadeas.integral.mystic.web.route.config.IntegralMysticRouteConfigTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile(TEST)
@EnableJpaRepositories(basePackages = "my.com.myriadeas.integral.data.jpa.repositories.impl")
// TODO must put here
//@Import(value = { IntegralMysticRouteConfigDev.class})
@Import(value = { IntegralMysticRouteConfigTest.class})
@ImportResource(value={"classpath:META-INF/spring/securityContext.xml"})
//@ImportResource(value={"classpath:casSecurityContext.xml"})
public class IntegralMysticWebConfigTest implements IntegralMysticWebConfig {

}

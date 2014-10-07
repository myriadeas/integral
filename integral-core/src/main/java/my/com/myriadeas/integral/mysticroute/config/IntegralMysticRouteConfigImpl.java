package my.com.myriadeas.integral.mysticroute.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value = { "classpath:META-INF/spring/integralServiceRouteContext.xml" })
public class IntegralMysticRouteConfigImpl implements IntegralMysticRouteConfig {

}

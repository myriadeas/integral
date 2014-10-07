/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package my.com.myriadeas.integral.rest.config;

import javax.sql.DataSource;

import my.com.myriadeas.spring.core.util.SpringEnvironmentUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource(name = "properties", value = {
		"classpath:my/com/myriadeas/sample/config/config-ext.properties",
		"classpath:my/com/myriadeas/sample/config/config.properties" })
@Profile(SpringEnvironmentUtil.PROD)
public class ProdConfig extends MainConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		return pspc;
	}

	@Override
	public DataSource dataSource() {
		// TODO Auto-generated method stub
		return null;
	}
}

/*
 * Copyright 2007 The JA-SIG Collaborative. All rights reserved. See license
 * distributed with this file and available online at
 * http://www.ja-sig.org/products/cas/overview/license/
 */
package my.com.myriadeas.cas.adaptors.jdbc.integral;

import java.util.List;
import java.util.Map;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class that given a table, username field and password field will query a
 * database table with the provided encryption technique to see if the user
 * exists. This class defaults to a PasswordTranslator of
 * PlainTextPasswordTranslator.
 * 
 * @author Scott Battaglia
 * @author Dmitriy Kopylenko
 * @version $Revision: 19533 $ $Date: 2009-12-14 23:33:36 -0500 (Mon, 14 Dec
 *          2009) $
 * @since 3.0
 */

public class SearchModeSearchDatabaseAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler implements
		InitializingBean {

	private final Logger logger = LoggerFactory
			.getLogger(SearchModeSearchDatabaseAuthenticationHandler.class);

	private String sql;

	private PasswordEncoder passwordEncoder;

	protected final boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		logger.debug(
				"Entering authenticateUsernamePasswordInternal(credentials={})",
				credentials);

		final String transformedUsername = getPrincipalNameTransformer()
				.transform(credentials.getUsername());

		boolean isAuthenticate = false;

		List<Map<String, Object>> userList = getJdbcTemplate().queryForList(
				sql, transformedUsername, transformedUsername);
		
		if (!userList.isEmpty()) {
			for (Map<String, Object> userMap : userList) {
				if (isPasswordMatched(credentials.getPassword(),
						(String) userMap.get("password"))) {

					return true;
				}
			}
		}

		logger.debug(
				"Leaving authenticateUsernamePasswordInternal(). isAuthenticate={}",
				isAuthenticate);
		return isAuthenticate;
	}

	public void afterPropertiesSet() throws Exception {

		this.sql = "select o.password as password, " + "'officer' as user "
				+ "from officer o " + "where o.username = ? " + "union "
				+ "select p.password as password, " + "'patron' as user "
				+ "from patron p " + "where p.username = ?";

	}

	protected boolean isPasswordMatched(String credentialsPassword,
			String userPassword) {
		passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder.matches(credentialsPassword, userPassword);
	}

}

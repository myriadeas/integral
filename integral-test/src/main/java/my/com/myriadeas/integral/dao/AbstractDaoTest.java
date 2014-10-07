package my.com.myriadeas.integral.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:META-INF/spring/jdbc-dev-context.xml" })
public abstract class AbstractDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	protected static EmbeddedDatabase _db;

	@Before
	public void setUp() throws Exception {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setName("dataSource").setType(EmbeddedDatabaseType.HSQL);
		for (String script : getScripts()) {
			builder.addScript("classpath:data/integral/" + script);
		}
		_db = builder.build();
	}

	@After
	public void tearDown() throws Exception {
		_db.shutdown();
	}

	/**
	 * Marker method that always run to make sure Spring's Configuration working
	 * properly
	 */

	@Test
	public void testMarker() {
	}

	/**
	 * Implementation class to load scripts
	 * 
	 * @return
	 */
	public abstract String[] getScripts();

	public Object findRecord(String tableName, String key, String value,
			RowMapper<?> rowMapper) {
		String query = "SELECT * FROM " + tableName + " WHERE " + key + " = ? ";
		@SuppressWarnings("deprecation")
		Object object = simpleJdbcTemplate.queryForObject(query, rowMapper,
				new Object[] { value });
		return object;
	}

}

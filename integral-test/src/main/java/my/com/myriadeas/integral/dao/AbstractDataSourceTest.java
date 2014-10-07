package my.com.myriadeas.integral.dao;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDataSourceTest {

	@Autowired
	private org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean _db;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		_db.getDatabase().shutdown();
	}

}

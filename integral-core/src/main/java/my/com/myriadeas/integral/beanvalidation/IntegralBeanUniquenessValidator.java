package my.com.myriadeas.integral.beanvalidation;

import java.util.Date;
import java.util.Map;

import javax.sql.DataSource;

import my.com.myriadeas.integral.beanvalidation.exception.UniquenessConstraintException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service("integralBeanUniquenessValidator")
public class IntegralBeanUniquenessValidator {

	private static final Logger logger = LoggerFactory
			.getLogger(IntegralBeanUniquenessValidator.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public IntegralBeanUniquenessValidator() {
		// TODO Auto-generated constructor stub
	}

	public UniquenessConstraintResponse validate(
			UniquenessConstraintRequest request) {
		try {
			String getUniquenessSQLStatement = getUniquenessSQLStatement(request);
			logger.info("Uniquess SQL Statement={}", getUniquenessSQLStatement);
			Integer count = jdbcTemplate.queryForObject(
					getUniquenessSQLStatement, Integer.class);
			UniquenessConstraintResponse response = new UniquenessConstraintResponse();
			response.setValid(count == 0);
			return response;
		} catch (DataAccessException e) {
			throw new UniquenessConstraintException("Fail to validate", e);
		} catch (IllegalArgumentException e) {
			throw new UniquenessConstraintException("Fail to validate", e);
		}
	}

	public String getUniquenessSQLStatement(UniquenessConstraintRequest request) {
		Assert.notNull(request);
		boolean first = true;
		String whereClause = "";
		for (Map.Entry<String, Object> entry : request.getColumns().entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (!first) {
				whereClause = whereClause + " AND ";
			}

			if (value instanceof String || value instanceof Date) {
				whereClause = whereClause + key + " = '" + value + "'";
			} else {
				whereClause = whereClause + key + " = " + value;
			}
		}
		first = false;

		String sqlStatement = "select count(*) from " + request.getTable()
				+ " where " + whereClause;
		return sqlStatement;
	}
}

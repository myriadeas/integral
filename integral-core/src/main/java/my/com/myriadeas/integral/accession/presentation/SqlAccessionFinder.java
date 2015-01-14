package my.com.myriadeas.integral.accession.presentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.com.myriadeas.integral.accession.presentation.AccessionSearchCriteria.AccessionSearchOrder;
import my.com.myriadeas.integral.assetmanagement.domain.model.ItemStatus;
import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

@Service(value = "sqlAccessionFinder")
public class SqlAccessionFinder implements AccessionFinder {

	private static final int MAX_ITEMS_PER_PAGE = 50;

	@Resource
	private NamedParameterJdbcOperations jdbcTemplate;

	@Override
	public PaginatedResult<AccessionListItemDto> findAccessions(
			AccessionSearchCriteria criteria) {

		int accessionsCount = countAccessions(criteria);
		if (accessionsCount <= 0) {
			return new PaginatedResult<AccessionListItemDto>(
					criteria.getPageNumber(), criteria.getItemsPerPage());
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		String sqlQuery = createSqlSelectQuery(criteria, parameters);
		List<AccessionListItemDto> resourceDescriptors = jdbcTemplate.query(
				sqlQuery, parameters, new AccessionListItemRowMapper());
		return new PaginatedResult<AccessionListItemDto>(resourceDescriptors,
				criteria.getPageNumber(), criteria.getItemsPerPage(),
				accessionsCount);

	}

	private int countAccessions(AccessionSearchCriteria criteria) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		query.append("SELECT count(*) FROM Item r ");
		appendWhereClause(query, criteria, parameters);
		return jdbcTemplate.queryForList(query.toString(), parameters).size();
	}

	private String createSqlSelectQuery(AccessionSearchCriteria criteria,
			Map<String, Object> parameters) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT r.resourceDescriptorIdentifier, r.itemIdentifier, r.itemStatus FROM Item r ");
		appendWhereClause(query, criteria, parameters);
		appendOrderByClause(query, criteria);
		appendOffsetLimitClause(query, criteria, parameters);
		return query.toString();
	}

	private void appendOffsetLimitClause(StringBuilder query,
			AccessionSearchCriteria criteria, Map<String, Object> parameters) {
		int offset = criteria.getItemsPerPage()
				* (criteria.getPageNumber() - 1);
		int limit = Math.min(criteria.getItemsPerPage(), MAX_ITEMS_PER_PAGE);
		query.append("OFFSET :offset LIMIT :limit ");
		parameters.put("offset", offset);
		parameters.put("limit", limit);

	}

	private void appendWhereClause(StringBuilder query,
			AccessionSearchCriteria criteria, Map<String, Object> parameters) {
		List<String> constraints = new ArrayList<String>();
		addConstraints(constraints, criteria, parameters);

		if (!constraints.isEmpty()) {
			query.append("WHERE ");
			query.append(StringUtils.join(constraints, " AND "));
			query.append(' ');
		}
	}

	private void addConstraints(List<String> constraints,
			AccessionSearchCriteria criteria, Map<String, Object> parameters) {
		if (!StringUtils.isBlank(criteria.getContainsText())) {
			constraints.add("LOWER(r.marc) LIKE :searchedText");
			parameters.put("marc", "%"
					+ criteria.getContainsText().toLowerCase() + "%");
		}
		if (criteria.getStatus() != null) {
			constraints.add("r.itemStatus = :status");
			parameters.put("status", criteria.getStatus());
		}
		if (criteria.hasSpecificResourceDescriptorIdsFilter()) {
			constraints
					.add("r.resourceDescriptorIdentifier IN (:resourceDescriptorId)");
			parameters.put("resourceDescriptorId",
					criteria.getSpecificResourceDescriptorIds());
		}
		if (criteria.hasSpecificAccessionIdsFilter()) {
			constraints.add("r.itemIdentifier IN (:accessionId)");
			parameters.put("accessionId", criteria.getSpecificAccessionIds());
		}
	}

	private void appendOrderByClause(StringBuilder query,
			AccessionSearchCriteria criteria) {
		if (criteria.getOrderBy() != null) {
			query.append("ORDER BY ");
			query.append(getOrderByColumnName(criteria.getOrderBy()));
			query.append(criteria.isAscending() ? " ASC " : " DESC ");
		}
	}

	private String getOrderByColumnName(AccessionSearchOrder orderBy) {
		if (AccessionSearchOrder.resourceDescriptorIdentifier.equals(orderBy)) {
			return "r.resourceDescriptorIdentifier";
		} else if (AccessionSearchOrder.itemIdentifier.equals(orderBy)) {
			return "r.itemIdentifier";
		} else if (AccessionSearchOrder.itemStatus.equals(orderBy)) {
			return "r.itemStatus";
		} else {
			throw new IllegalArgumentException(
					"unknow order by in AccessionSearchCriteria");
		}
	}

	private static class AccessionListItemRowMapper implements
			RowMapper<AccessionListItemDto> {

		@Override
		public AccessionListItemDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			AccessionListItemDto dto = new AccessionListItemDto();
			dto.setResourceDescriptorId(rs
					.getString("resourceDescriptorIdentifier"));
			dto.setAccessionId(rs.getString("itemIdentifier"));
			dto.setStatus(ItemStatus.values()[rs.getInt("itemStatus")]);
			return dto;
		}
	}

}

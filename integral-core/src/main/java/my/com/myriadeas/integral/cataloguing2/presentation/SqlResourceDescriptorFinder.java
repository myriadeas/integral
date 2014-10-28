package my.com.myriadeas.integral.cataloguing2.presentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import my.com.myriadeas.integral.cataloguing2.cqrs.query.PaginatedResult;
import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptorStatus;
import my.com.myriadeas.integral.cataloguing2.presentation.ResourceDescriptorSearchCriteria.ResourceDescriptorSearchOrder;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

@Service(value = "sqlResourceDescriptorFinder")
public class SqlResourceDescriptorFinder implements ResourceDescriptorFinder {

	private static final int MAX_ITEMS_PER_PAGE = 50;

	@Resource
	private NamedParameterJdbcOperations jdbcTemplate;

	@Override
	public PaginatedResult<ResourceDescriptorListItemDto> findResourceDescriptors(
			ResourceDescriptorSearchCriteria criteria) {

		int resourceDescriptorsCount = countResourceDescriptors(criteria);
		if (resourceDescriptorsCount <= 0) {
			return new PaginatedResult<ResourceDescriptorListItemDto>(
					criteria.getPageNumber(), criteria.getItemsPerPage());
		}
		Map<String, Object> parameters = new HashMap<String, Object>();
		String sqlQuery = createSqlSelectQuery(criteria, parameters);
		List<ResourceDescriptorListItemDto> resourceDescriptors = jdbcTemplate
				.query(sqlQuery, parameters,
						new ResourceDescriptorListItemRowMapper());
		return new PaginatedResult<ResourceDescriptorListItemDto>(
				resourceDescriptors, criteria.getPageNumber(),
				criteria.getItemsPerPage(), resourceDescriptorsCount);

	}

	private int countResourceDescriptors(
			ResourceDescriptorSearchCriteria criteria) {
		StringBuilder query = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		query.append("SELECT count(*) FROM ResourceDescriptor r ");
		appendWhereClause(query, criteria, parameters);
		return jdbcTemplate.queryForList(query.toString(), parameters).size();
	}

	private String createSqlSelectQuery(
			ResourceDescriptorSearchCriteria criteria,
			Map<String, Object> parameters) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT r.resourceDescriptorId, r.marc, r.status FROM ResourceDescriptor r ");
		appendWhereClause(query, criteria, parameters);
		appendOrderByClause(query, criteria);
		appendOffsetLimitClause(query, criteria, parameters);
		return query.toString();
	}

	private void appendOffsetLimitClause(StringBuilder query,
			ResourceDescriptorSearchCriteria criteria,
			Map<String, Object> parameters) {
		int offset = criteria.getItemsPerPage()
				* (criteria.getPageNumber() - 1);
		int limit = Math.min(criteria.getItemsPerPage(), MAX_ITEMS_PER_PAGE);
		query.append("OFFSET :offset LIMIT :limit ");
		parameters.put("offset", offset);
		parameters.put("limit", limit);

	}

	private void appendWhereClause(StringBuilder query,
			ResourceDescriptorSearchCriteria criteria,
			Map<String, Object> parameters) {
		List<String> constraints = new ArrayList<String>();
		addConstraints(constraints, criteria, parameters);

		if (!constraints.isEmpty()) {
			query.append("WHERE ");
			query.append(StringUtils.join(constraints, " AND "));
			query.append(' ');
		}
	}

	private void addConstraints(List<String> constraints,
			ResourceDescriptorSearchCriteria criteria,
			Map<String, Object> parameters) {
		if (!StringUtils.isBlank(criteria.getContainsText())) {
			constraints.add("LOWER(r.marc) LIKE :searchedText");
			parameters.put("marc", "%"
					+ criteria.getContainsText().toLowerCase() + "%");
		}
		if (criteria.getStatus() != null) {
			constraints.add("r.status = :status");
			parameters.put("status", criteria.getStatus());
		}
		if (criteria.hasSpecificResourceDescriptorIdsFilter()) {
			constraints.add("r.resourceDescriptorId IN (:resourceDescriptorId)");
			parameters.put("resourceDescriptorId",
					criteria.getSpecificResourceDescriptorIds());
		}
	}

	private void appendOrderByClause(StringBuilder query,
			ResourceDescriptorSearchCriteria criteria) {
		if (criteria.getOrderBy() != null) {
			query.append("ORDER BY ");
			query.append(getOrderByColumnName(criteria.getOrderBy()));
			query.append(criteria.isAscending() ? " ASC " : " DESC ");
		}
	}

	private String getOrderByColumnName(ResourceDescriptorSearchOrder orderBy) {
		if (ResourceDescriptorSearchOrder.RESOURCE_DESCRIPTOR_ID
				.equals(orderBy)) {
			return "r.resourceDescriptorId";
		} else if (ResourceDescriptorSearchOrder.STATUS.equals(orderBy)) {
			return "r.status";
		} else {
			throw new IllegalArgumentException(
					"unknow order by in ProductSearchCriteria");
		}
	}

	private static class ResourceDescriptorListItemRowMapper implements
			RowMapper<ResourceDescriptorListItemDto> {

		@Override
		public ResourceDescriptorListItemDto mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			ResourceDescriptorListItemDto dto = new ResourceDescriptorListItemDto();
			dto.setResourceDescriptorId(rs.getString("resourceDescriptorId"));
			dto.setMarc(rs.getString("marc"));
			dto.setStatus(ResourceDescriptorStatus.values()[rs.getInt("status")]);
			return dto;
		}
	}
}

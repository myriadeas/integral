package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

public interface AccessionFinder {

	PaginatedResult<AccessionListItemDto> findAccessions(
			AccessionSearchCriteria searchCriteria);

}

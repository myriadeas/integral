package my.com.myriadeas.integral.accession.presentation;

import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

public interface AccessionFinder {

	PaginatedResult<AccessionListItemDto> findAccessions(
			AccessionSearchCriteria searchCriteria);

}

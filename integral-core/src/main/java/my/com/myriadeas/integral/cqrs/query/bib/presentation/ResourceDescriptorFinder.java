package my.com.myriadeas.integral.cqrs.query.bib.presentation;

import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

public interface ResourceDescriptorFinder {

	PaginatedResult<ResourceDescriptorListItemDto> findDetails(
			ResourceDescriptorSearchCriteria searchCriteria);

}

package my.com.myriadeas.integral.cqrs.query.accession.presentation;

import my.com.myriadeas.integral.cqrs.query.PaginatedResult;

public interface ResourceDescriptorFinder {

	PaginatedResult<ResourceDescriptorListItemDto> findResourceDescriptors(
			ResourceDescriptorSearchCriteria searchCriteria);

}

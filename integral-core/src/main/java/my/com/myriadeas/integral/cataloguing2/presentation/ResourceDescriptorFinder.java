package my.com.myriadeas.integral.cataloguing2.presentation;

import my.com.myriadeas.integral.cataloguing2.cqrs.query.PaginatedResult;

public interface ResourceDescriptorFinder {

	PaginatedResult<ResourceDescriptorListItemDto> findResourceDescriptors(
			ResourceDescriptorSearchCriteria searchCriteria);

}

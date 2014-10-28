package my.com.myriadeas.integral.cataloguing2.infrastructure;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;

import org.springframework.data.repository.query.Param;

public interface ResourceDescriptorRepositoryImpl extends
		ResourceDescriptorRepository {

	public ResourceDescriptor findById(@Param("search") Long id);

	public ResourceDescriptor findByResourceDescriptorId(
			@Param("search") String resourceDescriptorId);

}
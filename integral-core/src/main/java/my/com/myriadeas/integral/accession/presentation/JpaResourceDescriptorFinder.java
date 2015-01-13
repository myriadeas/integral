package my.com.myriadeas.integral.accession.presentation;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;
import my.com.myriadeas.integral.cataloguing2.infrastructure.ResourceDescriptorRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "jpaResourceDescriptorFinder")
public class JpaResourceDescriptorFinder {

	@Autowired
	private ResourceDescriptorRepositoryImpl resourceDescriptorRepo;

	public ResourceDescriptorListItemDto findResourceDescriptorById(Long id) {

		ResourceDescriptor resourceDescriptor = resourceDescriptorRepo
				.findById(id);
		ResourceDescriptorListItemDto resourceDescriptorListItemDto = new ResourceDescriptorListItemDto();
		resourceDescriptorListItemDto
				.setResourceDescriptorId(resourceDescriptor
						.getResourceDescriptorId());
		resourceDescriptorListItemDto.setMarc(resourceDescriptor.getMarc());
		resourceDescriptorListItemDto.setStatus(resourceDescriptor.getStatus());
		return resourceDescriptorListItemDto;

	}
}

package my.com.myriadeas.integral.cataloguing2.infrastructure;

import my.com.myriadeas.integral.cataloguing2.domain.model.ResourceDescriptor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ResourceDescriptorRepository extends
		JpaRepository<ResourceDescriptor, Long> {

}
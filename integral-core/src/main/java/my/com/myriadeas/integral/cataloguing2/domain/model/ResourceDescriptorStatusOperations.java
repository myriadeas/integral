package my.com.myriadeas.integral.cataloguing2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface ResourceDescriptorStatusOperations {

	ResourceDescriptorStatus finalize(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events);

	ResourceDescriptorStatus revise(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events);

	ResourceDescriptorStatus delete(ResourceDescriptor rd,
			Map<String, DomainEvent> events);

	ResourceDescriptorStatus update(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events);

}

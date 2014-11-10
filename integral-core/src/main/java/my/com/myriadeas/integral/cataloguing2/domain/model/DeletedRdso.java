package my.com.myriadeas.integral.cataloguing2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.cataloguing2.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class DeletedRdso implements ResourceDescriptorStatusOperations {

	@Override
	public ResourceDescriptorStatus finalize(ResourceDescriptor rd,
			String marc, Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("finalize",
				ResourceDescriptorStatus.DELETED);
	}

	@Override
	public ResourceDescriptorStatus revise(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("revise",
				ResourceDescriptorStatus.DELETED);
	}

	@Override
	public ResourceDescriptorStatus delete(ResourceDescriptor rd,
			Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("delete",
				ResourceDescriptorStatus.DELETED);
	}

	@Override
	public ResourceDescriptorStatus update(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("update",
				ResourceDescriptorStatus.DELETED);
	}

}

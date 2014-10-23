package my.com.myriadeas.integral.cataloguing2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.cataloguing2.exception.UnsupportedStatusTransitionException;
import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class RevisedRdso implements ResourceDescriptorStatusOperations {

	@Override
	public ResourceDescriptorStatus finalize(ResourceDescriptor rd,
			String marc, Map<String, DomainEvent> events) {
		rd.setMarc(marc);
		DomainEvent event = new ResourceDescriptorFinalized(
				rd.getResourceDescriptorId());
		events.put("resourceDescriptorFinalized", event);
		return ResourceDescriptorStatus.COMPLETED;
	}

	@Override
	public ResourceDescriptorStatus revise(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("revise",
				ResourceDescriptorStatus.REVISED);
	}

	@Override
	public ResourceDescriptorStatus delete(ResourceDescriptor rd,
			Map<String, DomainEvent> events) {
		throw new UnsupportedStatusTransitionException("delete",
				ResourceDescriptorStatus.REVISED);
	}

	@Override
	public ResourceDescriptorStatus update(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		rd.setMarc(marc);
		DomainEvent event = new ResourceDescriptorUpdated(
				rd.getResourceDescriptorId());
		events.put("resourceDescriptorUpdated", event);
		return ResourceDescriptorStatus.REVISED;
	}

	@Override
	public ResourceDescriptorStatus sendToDelete(ResourceDescriptor rd,
			Map<String, DomainEvent> events) {
		DomainEvent event = new ResourceDescriptorAwaitingDeletion(
				rd.getResourceDescriptorId());
		events.put("resourceDescriptorSentToDelete", event);
		return ResourceDescriptorStatus._AWAITING_DELETION;
	}

}

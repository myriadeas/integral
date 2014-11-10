package my.com.myriadeas.integral.cataloguing2.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public enum ResourceDescriptorStatus implements
		ResourceDescriptorStatusOperations {

	DRAFT(new DraftRdso()),

	COMPLETED(new CompletedRdso()),

	REVISED(new RevisedRdso()),

	DELETED(new DeletedRdso());

	private final ResourceDescriptorStatusOperations operations;

	ResourceDescriptorStatus(ResourceDescriptorStatusOperations operations) {
		this.operations = operations;
	}

	ResourceDescriptorStatusOperations getOperations() {
		return this.operations;
	}

	@Override
	public ResourceDescriptorStatus finalize(ResourceDescriptor rd,
			String marc, Map<String, DomainEvent> events) {
		return this.operations.finalize(rd, marc, events);
	}

	@Override
	public ResourceDescriptorStatus revise(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		return this.operations.revise(rd, marc, events);
	}

	@Override
	public ResourceDescriptorStatus delete(ResourceDescriptor rd,
			Map<String, DomainEvent> events) {
		return this.operations.delete(rd, events);
	}

	@Override
	public ResourceDescriptorStatus update(ResourceDescriptor rd, String marc,
			Map<String, DomainEvent> events) {
		return this.operations.update(rd, marc, events);
	}

}
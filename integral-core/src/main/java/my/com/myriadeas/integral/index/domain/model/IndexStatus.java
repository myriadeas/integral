package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public enum IndexStatus implements IndexStatusOperations {

	NEW(new NewIso()), INDEX(new IndexIso()), UNINDEX(new UnindexIso());

	private final IndexStatusOperations operations;

	IndexStatus(IndexStatusOperations operations) {
		this.operations = operations;
	}

	IndexStatusOperations getOperations() {
		return this.operations;
	}

	@Override
	public IndexStatus index(IndexRecord record, String marc,
			Map<String, DomainEvent> events) {
		return this.operations.index(record, marc, events);
	}

	@Override
	public IndexStatus unindex(IndexRecord record,
			Map<String, DomainEvent> events) {
		return this.operations.unindex(record, events);
	}
}

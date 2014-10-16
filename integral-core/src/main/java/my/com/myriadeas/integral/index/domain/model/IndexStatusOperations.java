package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public interface IndexStatusOperations {

	public IndexStatus index(IndexRecord record, String marc,
			Map<String, DomainEvent> events);

	public IndexStatus unindex(IndexRecord record,
			Map<String, DomainEvent> events);
}

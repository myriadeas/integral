package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class IndexIso extends AbstractIndexStatusOperations implements
		IndexStatusOperations {

	@Override
	public IndexStatus unindex(IndexRecord record,
			Map<String, DomainEvent> events) {
		this.repository.delete(record.getVuFindMarc());
		DomainEvent event = new IndexRecordUnindexed(
				record.getResourceDescriptorId());
		events.put("indexRecordUnindexed", event);
		return IndexStatus.UNINDEX;
	}

}

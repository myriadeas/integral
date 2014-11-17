package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.index.domain.service.IndexerTransformationException;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class NewIso extends AbstractIndexStatusOperations implements
		IndexStatusOperations {

	@Override
	public IndexStatus index(IndexRecord record, String marc,
			Map<String, DomainEvent> events) {
		record.setMarc(marc);
		try {
			this.repository.save(record.getVuFindMarc());
		} catch (IndexerTransformationException e) {
			throw new IndexException("Failed to transform marc record", e);
		}
		DomainEvent event = new IndexRecordIndexed(record.getMarc(),
				record.getResourceDescriptorId());
		events.put("indexRecordIndexed", event);
		return IndexStatus.INDEX;
	}
}

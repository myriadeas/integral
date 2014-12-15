package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.index.domain.service.IndexerTransformationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class NewIso extends AbstractIndexStatusOperations implements
		IndexStatusOperations {

	private static final Logger logger = LoggerFactory.getLogger(NewIso.class);

	@Override
	public IndexStatus index(IndexRecord record, String marc,
			Map<String, DomainEvent> events) {
		logger.debug("Entering index(record={}, marc={}, events={})",
				new Object[] { record, marc, events });
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

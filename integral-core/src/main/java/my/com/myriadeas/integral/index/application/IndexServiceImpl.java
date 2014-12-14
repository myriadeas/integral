package my.com.myriadeas.integral.index.application;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.publisher.Publisher;
import my.com.myriadeas.integral.index.domain.model.IndexRecord;
import my.com.myriadeas.integral.index.domain.model.IndexRecordRepository;
import my.com.myriadeas.integral.index.infrastructures.jpa.IndexRecordRepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

	private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);
	
	private IndexRecordRepository repository;

	private Publisher publisher;

	@Override
	@Transactional
	public void index(IndexCommand command) {
		logger.debug("Entering index=({})", command);
		IndexRecord indexRecord = repository.findByResourceDescriptorId(command
				.getResourceDescriptorId());
		if (indexRecord == null) {
			logger.debug("No existing indexRecord");
			indexRecord = new IndexRecord(command.getMarc(),
					command.getResourceDescriptorId());
		}
		logger.debug("indexRecord={}", indexRecord);
		Map<String, DomainEvent> events = indexRecord.index(command.getMarc());
		repository.save(indexRecord);
		publisher.publish(events);
	}

	@Override
	@Transactional
	public void unindex(UnindexCommand command) {
		IndexRecord indexRecord = repository.findByResourceDescriptorId(command
				.getResourceDescriptorId());
		Assert.notNull(indexRecord);
		Map<String, DomainEvent> events = indexRecord.unindex();
		repository.save(indexRecord);
		publisher.publish(events);
	}

	@Autowired
	public void setIndexRecordRepository(IndexRecordRepositoryImpl repository) {
		this.repository = repository;
	}

	@Autowired
	@Qualifier("indexPublisher")
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}

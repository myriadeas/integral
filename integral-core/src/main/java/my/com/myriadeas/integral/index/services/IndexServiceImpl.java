package my.com.myriadeas.integral.index.services;

import my.com.myriadeas.integral.index.domain.model.IndexRecord;
import my.com.myriadeas.integral.index.infrastructures.IndexRecordRepository;

import org.springframework.util.Assert;

public class IndexServiceImpl implements IndexService {

	private IndexRecordRepository repository;

	@Override
	public void index(String marc, String resourceDescriptorId) {
		IndexRecord indexRecord = repository
				.findByResourceDescriptorId(resourceDescriptorId);
		if (indexRecord == null) {
			indexRecord = new IndexRecord(marc, resourceDescriptorId);
			repository.save(indexRecord);
		}
		indexRecord.index(marc);
		repository.save(indexRecord);
	}

	@Override
	public void unindex(String resourceDescriptorId) {
		IndexRecord indexRecord = repository
				.findByResourceDescriptorId(resourceDescriptorId);
		Assert.notNull(indexRecord);
		indexRecord.unindex();
		repository.save(indexRecord);
	}

}

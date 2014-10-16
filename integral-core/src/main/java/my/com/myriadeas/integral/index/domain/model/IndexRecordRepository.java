package my.com.myriadeas.integral.index.domain.model;

public interface IndexRecordRepository {

	public IndexRecord findByResourceDescriptorId(String resourceDescriptorId);

	public IndexRecord save(IndexRecord indexRecord);
}

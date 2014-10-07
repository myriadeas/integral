package my.com.myriadeas.integral.index.domain.model;


public interface IndexStatusOperations {
	public IndexStatus index(IndexRecord record);

	public IndexStatus unindex(IndexRecord record);
}

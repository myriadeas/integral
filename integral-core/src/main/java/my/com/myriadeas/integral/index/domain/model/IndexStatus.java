package my.com.myriadeas.integral.index.domain.model;


public enum IndexStatus implements IndexStatusOperations {

	NEW(new NewIso()), INDEX(new IndexIso()), UNINDEX(new UnindexIso());

	private final IndexStatusOperations operations;

	IndexStatus(IndexStatusOperations operations) {
		this.operations = operations;
	}

	@Override
	public IndexStatus index(IndexRecord record) {
		// TODO Auto-generated method stub
		return this.operations.index(record);
	}

	@Override
	public IndexStatus unindex(IndexRecord record) {
		// TODO Auto-generated method stub
		return this.operations.unindex(record);
	}
}

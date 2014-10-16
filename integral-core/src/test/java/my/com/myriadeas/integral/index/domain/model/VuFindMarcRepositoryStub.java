package my.com.myriadeas.integral.index.domain.model;


public class VuFindMarcRepositoryStub implements VuFindMarcRepository {

	private int count = 0;

	public long count() {
		return count;
	}

	@Override
	public void delete(VuFindMarc entity) {
		count--;
	}

	@Override
	public VuFindMarc save(VuFindMarc vuFindMarc) {
		count++;
		return vuFindMarc;
	}

}

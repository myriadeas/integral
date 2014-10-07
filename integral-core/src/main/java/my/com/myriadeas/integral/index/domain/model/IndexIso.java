package my.com.myriadeas.integral.index.domain.model;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class IndexIso extends AbstractIso implements IndexStatusOperations {

	@Override
	public IndexStatus index(IndexRecord record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndexStatus unindex(IndexRecord record) {
		this.repository.delete(record.getVuFindMarc());
		return IndexStatus.UNINDEX;
	}

}

package my.com.myriadeas.integral.index.domain.model;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class UnindexIso extends AbstractIso implements IndexStatusOperations {

	@Override
	public IndexStatus index(IndexRecord record) {
		this.repository.save(record.getVuFindMarc());
		return IndexStatus.INDEX;
	}

	@Override
	public IndexStatus unindex(IndexRecord record) {
		// TODO Auto-generated method stub
		return null;
	}

}

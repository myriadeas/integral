package my.com.myriadeas.integral.index.domain.model;

import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hierachical State Machine. An abstract class for common status. If there is
 * not implementation override, default not perform any action. Just return the
 * state. Refer to http://gameprogrammingpatterns.com/state.html
 * 
 * @author hutingung
 * 
 */
public abstract class AbstractIndexStatusOperations implements
		IndexStatusOperations {

	protected VuFindMarcRepository repository;

	@Autowired
	public void setRepository(VuFindMarcRepository repository) {
		this.repository = repository;
	}

	public VuFindMarcRepository getRepository() {
		return this.repository;
	}

	@Override
	public IndexStatus index(IndexRecord record, String marc,
			Map<String, DomainEvent> events) {
		return IndexStatus.INDEX;
	}

	@Override
	public IndexStatus unindex(IndexRecord record,
			Map<String, DomainEvent> events) {
		return IndexStatus.UNINDEX;
	}
}

package my.com.myriadeas.integral.index.domain.model;

import my.com.myriadeas.integral.index.infrastructures.VuFindMarcRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public abstract class AbstractIso {

	protected VuFindMarcRepository repository;

	@Autowired
	public void setRepository(VuFindMarcRepository repository) {
		this.repository = repository;
	}

	public VuFindMarcRepository getRepository() {
		return this.repository;
	}

}

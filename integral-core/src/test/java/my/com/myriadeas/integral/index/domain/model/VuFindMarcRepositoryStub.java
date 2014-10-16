package my.com.myriadeas.integral.index.domain.model;

import my.com.myriadeas.integral.index.infrastructures.VuFindMarcRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class VuFindMarcRepositoryStub implements VuFindMarcRepository {

	private int count = 0;

	@Override
	public long count() {
		return count;
	}

	@Override
	public Iterable<VuFindMarc> findAll(Sort sort) {
		return null;
	}

	@Override
	public Page<VuFindMarc> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public <S extends VuFindMarc> S save(S entity) {
		count++;
		return entity;
	}

	@Override
	public <S extends VuFindMarc> Iterable<S> save(Iterable<S> entities) {
		return null;
	}

	@Override
	public VuFindMarc findOne(String id) {
		return null;
	}

	@Override
	public boolean exists(String id) {
		return false;
	}

	@Override
	public Iterable<VuFindMarc> findAll() {
		return null;
	}

	@Override
	public Iterable<VuFindMarc> findAll(Iterable<String> ids) {
		return null;
	}

	@Override
	public void delete(String id) {
		count--;
	}

	@Override
	public void delete(VuFindMarc entity) {
		count--;
	}

	@Override
	public void delete(Iterable<? extends VuFindMarc> entities) {

	}

	@Override
	public void deleteAll() {
	}

}

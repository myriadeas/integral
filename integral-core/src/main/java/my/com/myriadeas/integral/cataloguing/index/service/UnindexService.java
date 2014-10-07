package my.com.myriadeas.integral.cataloguing.index.service;

import my.com.myriadeas.integral.cataloguing.exception.IndexFailureException;

public interface UnindexService {

	public void unindex(String materialNo) throws IndexFailureException;

}

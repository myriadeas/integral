package my.com.myriadeas.integral.cataloguing2.application.service;

import my.com.myriadeas.integral.cataloguing2.domain.service.ConverterImpl;
import my.com.myriadeas.integral.cataloguing2.domain.service.VerifierImpl;

import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "resourceDescriptorReadService")
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDescriptorReadServiceImpl implements
		ResourceDescriptorReadService {

	private Logger logger = LoggerFactory
			.getLogger(ResourceDescriptorReadServiceImpl.class);

	@Autowired
	private VerifierImpl verifier;

	@Autowired
	private ConverterImpl converter;

	public String verifyRecord(Record record) {
		return verifier.verify(record);
	}

	public String convertRecord(Record record) {
		return converter.convert(record);
	}

}

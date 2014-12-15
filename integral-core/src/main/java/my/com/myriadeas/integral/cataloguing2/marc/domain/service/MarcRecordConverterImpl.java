package my.com.myriadeas.integral.cataloguing2.marc.domain.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import my.com.myriadeas.integral.cataloguing2.marc.model.CollectionType;
import my.com.myriadeas.integral.cataloguing2.marc.model.RecordType;

import org.marc4j.MarcXmlWriter;
import org.marc4j.marc.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.xml.sax.SAXException;

@Service("marcRecordConverterImpl")
public class MarcRecordConverterImpl implements MarcRecordConverter {

	private static final Logger logger = LoggerFactory
			.getLogger(MarcRecordConverterImpl.class);

	private static final String MARC21XML_XSD = "/MARC21slim.xsd";

	private static final String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	private static final String DEFAULT_ENCODING = "UTF8";

	private final Unmarshaller unmarshaller;

	public MarcRecordConverterImpl() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext
					.newInstance("my.com.myriadeas.integral.cataloguing2.marc.model");
			unmarshaller = jaxbContext.createUnmarshaller();
			Schema schema = SchemaFactory.newInstance(XML_SCHEMA).newSchema(
					this.getClass().getResource(MARC21XML_XSD));
			unmarshaller.setSchema(schema);
		} catch (JAXBException e) {
			throw new MarcRecordConverterInitializationException(
					"Fail to load JAXB Instance", e);
		} catch (SAXException e) {
			throw new MarcRecordConverterInitializationException(
					"Fail to load Schema Instance", e);
		}

	}

	@Override
	public RecordType convert(Record record)
			throws RecordToRecordTypeConvertException {
		logger.debug("Entering convert(record={})", record);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MarcXmlWriter writer = new MarcXmlWriter(out, DEFAULT_ENCODING);
		writer.write(record);
		writer.close();
		InputStream xmlInput = new ByteArrayInputStream(out.toByteArray());
		try {
			Object result = unmarshaller.unmarshal(xmlInput);
			@SuppressWarnings({ "unchecked" })
			JAXBElement<CollectionType> collection = ((JAXBElement<CollectionType>) result);
			Assert.notNull(collection);
			Assert.notNull(collection.getValue());
			Assert.notEmpty(collection.getValue().getRecord());
			return collection.getValue().getRecord().get(0);
		} catch (JAXBException e) {
			throw new RecordToRecordTypeConvertException(
					"Fail to convert record because of JAXBException", e);
		}
	}
}

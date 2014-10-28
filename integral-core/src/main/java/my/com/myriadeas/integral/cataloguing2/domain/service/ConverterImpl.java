package my.com.myriadeas.integral.cataloguing2.domain.service;

import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsConverter;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.RecordToJson;

import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value = "resourceDescriptorConverter")
public class ConverterImpl implements Converter {

	private JsConverter jsConverter = new JsConverter();

	@Value("${marc.convertion.scripts}")
	private String[] convertionScripts;

	@Override
	public String convert(Record record) {
		return jsConverter.convert(this.convertionScripts,
				RecordToJson.convert(record));
	}

}

package my.com.myriadeas.integral.index.domain.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.core.domain.model.Entity;
import my.com.myriadeas.integral.index.domain.service.Indexer;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@javax.persistence.Entity
public class IndexRecord implements Entity {

	public static final String TAG_CONTROL_NUMBER = "001";

	private static final String DEFAULT_ENCODING = "UTF8";

	private MarcFactory marcFactory;

	private Indexer indexer;

	private IndexStatus status = IndexStatus.NEW;

	private String resourceDescriptorId;

	private String marc;

	IndexRecord(String marc, String resourceDescriptorId, IndexStatus status) {
		this(marc, resourceDescriptorId);
		this.status = status;
	}

	public IndexRecord(String marc, String resourceDescriptorId) {
		this.marc = marc;
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public Map<String, DomainEvent> index(String marc) {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.index(this, marc, events));
		return events;
	}

	public Map<String, DomainEvent> unindex() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.unindex(this, events));
		return events;
	}

	@Autowired
	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}

	@Autowired
	public void setMarcFactory(MarcFactory marcFactory) {
		this.marcFactory = marcFactory;
	}

	public IndexStatus getStatus() {
		return this.status;
	}

	Record getMarcRecord() {
		try {
			InputStream is = new ByteArrayInputStream(
					this.marc.getBytes(DEFAULT_ENCODING));
			MarcReader reader = new MarcStreamReader(is, DEFAULT_ENCODING);
			Record record = null;
			while (reader.hasNext()) {
				record = reader.next();
				record.addVariableField(marcFactory.newControlField(
						TAG_CONTROL_NUMBER, this.resourceDescriptorId));
				return record;
			}
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	VuFindMarc getVuFindMarc() {
		VuFindMarc vuFindMarc = indexer.mapVufindMarc(this.getMarcRecord());
		return vuFindMarc;
	}

	String getMarc() {
		return this.marc;
	}

	void setMarc(String marc) {
		this.marc = marc;
	}

	String getResourceDescriptorId() {
		return this.resourceDescriptorId;
	}

	private void setStatus(IndexStatus status) {
		this.status = status;
	}

}

package my.com.myriadeas.integral.index.domain.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.index.domain.service.Indexer;

import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class IndexRecord {

	private static final String DEFAULT_ENCODING = "UTF8";

	private static final MarcFactory factory = MarcFactory.newInstance();

	private Indexer indexer;

	private IndexStatus status = IndexStatus.NEW;

	private String resourceDescriptorId;

	private String marc;

	public IndexRecord(String marc, String resourceDescriptorId) {
		this.marc = marc;
		this.resourceDescriptorId = resourceDescriptorId;
	}

	public String getMarc() {
		return this.marc;
	}

	public Record getMarcRecord() {
		try {
			InputStream is = new ByteArrayInputStream(
					this.marc.getBytes(DEFAULT_ENCODING));
			MarcReader reader = new MarcStreamReader(is, DEFAULT_ENCODING);
			Record record = null;
			while (reader.hasNext()) {
				record = reader.next();
				record.addVariableField(factory.newControlField("001",
						this.resourceDescriptorId));
				return record;
			}
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public VuFindMarc getVuFindMarc() {
		VuFindMarc vuFindMarc = indexer.mapVufindMarc(this.getMarcRecord());
		return vuFindMarc;
	}

	public void index(String marc) {
		this.marc = marc;
		setStatus(status.index(this));
	}

	public void unindex() {
		setStatus(status.unindex(this));
	}

	@Autowired
	public void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}

	public Indexer getIndexer() {
		return this.indexer;
	}

	private void setStatus(IndexStatus status) {
		this.status = status;
	}
}

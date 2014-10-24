package my.com.myriadeas.integral.cataloguing2.domain.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
import my.com.myriadeas.integral.data.jpa.domain.AbstractDomain;

import org.apache.commons.lang.Validate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.marc4j.MarcJsonWriter;
import org.marc4j.MarcReader;
import org.marc4j.MarcStreamReader;
import org.marc4j.MarcStreamWriter;
import org.marc4j.MarcWriter;
import org.marc4j.marc.ControlField;
import org.marc4j.marc.DataField;
import org.marc4j.marc.MarcFactory;
import org.marc4j.marc.Record;
import org.marc4j.marc.Subfield;
import org.marc4j.marc.impl.RecordImpl;
import org.marc4j.marc.impl.SubfieldImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class ResourceDescriptor extends AbstractDomain {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(ResourceDescriptor.class);

	private static final String DEFAULT_ENCODING = "UTF8";

	@Column(unique = true)
	@Length(max = 20)
	private String resourceDescriptorId;

	@Lob
	@Column(length = 10000)
	@NotBlank
	private String marc;

	private ResourceDescriptorStatus status = ResourceDescriptorStatus.DRAFT;

	@Transient
	private MarcFactory factory = MarcFactory.newInstance();

	public ResourceDescriptor() {

	}

	public ResourceDescriptor(String marc) {
		super();
		this.marc = marc;
	}

	public ResourceDescriptor(Record record) {
		super();
		this.marc = getMarcString(record);
	}

	public ResourceDescriptor(String resourceDescriptorId, String marc,
			ResourceDescriptorStatus status) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
		this.marc = marc;
		this.status = status;
	}

	public ResourceDescriptor(String resourceDescriptorId, String marc) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
		this.marc = marc;
	}

	public ResourceDescriptor(String resourceDescriptorId, Record record) {
		super();
		this.resourceDescriptorId = resourceDescriptorId;
		this.marc = getMarcString(record);
	}

	public String getResourceDescriptorId() {
		return resourceDescriptorId;
	}

	@PreUpdate
	@PostPersist
	public void postPersist() {
		this.resourceDescriptorId = String.format("%010d", this.getId());
		this.setMarc(reconstructMarcRecord());
	}

	protected String reconstructMarcRecord() {
		Record record = this.getMarcRecord();
		record = moveLocControlNumber(record);
		record.setId(Long.parseLong(this.getResourceDescriptorId()));
		record = setMaterialNoToControlField001(record);
		return this.getMarcString(record);
	}

	private Record moveLocControlNumber(Record record) {
		if (getControlField(record, "001") != null
				&& getControlField(record, "001").length() > 0) {
			if (getTagSubfieldData(record, "010", 'a').length() == 0) {
				return switchControlField(record, "001",
						this.resourceDescriptorId);
			}
		}
		return record;
	}

	private String getControlField(Record record, String tag) {
		List<ControlField> fields = record.getControlFields();
		for (ControlField field : fields) {
			if (field.getTag().equals(tag)) {
				return field.getData().trim();
			}
		}
		return "";
	}

	private Record setMaterialNoToControlField001(Record record) {
		List<ControlField> fields = record.getControlFields();
		for (ControlField field : fields) {
			if (field.getTag().equals("001")) {
				field.setData(this.resourceDescriptorId);
				return record;
			}
		}

		record.addVariableField(factory.newControlField("001",
				this.resourceDescriptorId));
		return record;
	}

	private Record switchControlField(Record record, String tag, String recordId) {
		List<ControlField> fields = record.getControlFields();
		for (ControlField field : fields) {
			if (field.getTag().equals(tag)) {
				DataField dataField = factory.newDataField();
				dataField.setTag("010");
				dataField.setIndicator1(' ');
				dataField.setIndicator2(' ');
				Subfield subfield = new SubfieldImpl();
				subfield.setCode('a');
				subfield.setData(getControlField(record, "001"));
				dataField.addSubfield(subfield);
				record.addVariableField(dataField);
				field.setData(recordId);
				return record;
			}
		}
		return record;
	}

	private String getTagSubfieldData(Record record, String tag, char subfield) {
		List<DataField> fields = record.getDataFields();
		for (DataField field : fields) {
			if (field.getTag().equals(tag)) {
				return field.getSubfield('a').toString().substring(2).trim();
			}
		}
		return "";
	}

	private String getMarcString(Record record) {
		Validate.notNull(record, "Record is required");
		ByteArrayOutputStream baosMarc = new ByteArrayOutputStream();
		MarcWriter marcWriter = new MarcStreamWriter(baosMarc, DEFAULT_ENCODING);
		marcWriter.write(record);
		marcWriter.close();
		try {
			return baosMarc.toString(DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getJsonString() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		MarcJsonWriter writer = new MarcJsonWriter(baos);
		writer.write(this.getMarcRecord());
		return baos.toString();
	}

	public Record getMarcRecord() {
		try {
			InputStream is = new ByteArrayInputStream(
					this.marc.getBytes(DEFAULT_ENCODING));
			MarcReader reader = new MarcStreamReader(is, DEFAULT_ENCODING);
			Record record = null;
			while (reader.hasNext()) {
				record = reader.next();
				return record;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new RecordImpl();
	}

	public void setMarc(String marc) {
		this.marc = marc;
	}

	public Map<String, DomainEvent> finalize(String marc) {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.finalize(this, marc, events));
		return events;
	}

	public Map<String, DomainEvent> revise(String marc) {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.revise(this, marc, events));
		return events;
	}

	public Map<String, DomainEvent> delete() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.delete(this, events));
		return events;
	}

	public Map<String, DomainEvent> update(String marc) {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.update(this, marc, events));
		logger.debug("Resource Descriptor#" + this.getResourceDescriptorId()
				+ ": is updated with its status: " + this.status);
		return events;
	}

	public Map<String, DomainEvent> sendTodelete() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		setStatus(status.sendToDelete(this, events));
		return events;
	}

	public void setStatus(ResourceDescriptorStatus status) {
		if (status != null && status != this.status) {
			logger.debug("Resource Descriptor#"
					+ this.getResourceDescriptorId()
					+ ": changing status from " + this.status + " to " + status);
			this.status = status;
		}
	}

}

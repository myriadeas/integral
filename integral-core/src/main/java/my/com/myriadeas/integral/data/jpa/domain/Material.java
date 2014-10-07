package my.com.myriadeas.integral.data.jpa.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PostPersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import my.com.myriadeas.integral.cataloguing.CatalogType;
import my.com.myriadeas.integral.cataloguing.index.service.IntegralIndexer;
import my.com.myriadeas.integral.cataloguing.marc4j.utility.JsVerifier;
import my.com.myriadeas.integral.circulation.CirculationEvents;
import my.com.myriadeas.integral.circulation.command.ReserveEventCommand;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.enumeration.CataloguingStatusEnum;
import my.com.myriadeas.integral.publisher.Publisher;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

/**
 * The persistent class for the Material database table.
 * 
 */
@Configurable
@Entity
public class Material extends AbstractDomain implements CatalogType {
	private static final Logger logger = LoggerFactory
			.getLogger(Material.class);

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_ENCODING = "UTF8";

	@Column(unique = true)
	@Length(max = 20)
	@NotBlank
	private String materialNo;

	/*
	 * http://stackoverflow.com/questions/4213782/hibernate-postgresql-hsqldb-text-column-incompatibility-problem
	 * Must annotate with length - I think it will default to Clob. If not, will get HSQLDB exception
	 */
	@NotBlank
	@Lob
	@Column(length=10000)
	private String marc;

	@Lob
	@Column(length=1000)
	private String title;

	@NotBlank
	private String status;

	@NotBlank
	private String type;

	@Autowired
	@Transient
	private IntegralIndexer integralIndexer;

	@Transient
	private MarcFactory factory = MarcFactory.newInstance();

	@Transient
	private JsVerifier jsVerifier = new JsVerifier();

	@Transient
	@Value("${marc.verification.scripts}")
	private String[] verificationScripts;

	@Transient
	private Publisher publisher;
	@Transient
	private ReservationTransactionFactory reservationTransactionFactory;
	@Transient
	private Officer reserveOfficer;
	@Transient
	private Patron reserver;
	@Transient
	private Date reserveDateTime;
	@Transient
	private Branch reserverPickUpBranch;
	@Transient
	private ItemRepositoryImpl itemRepo;

	@Transient
	private transient String isbn;

	@Transient
	private transient String author;

	@Transient
	private transient String edition;

	public Material() {
	}

	public Material(MaterialBuilder builder) {
		super();
		this.materialNo = builder.materialNo;
		this.type = builder.type;
		this.status = builder.status;
		if (builder.marc != null) {
			this.marc = builder.marc;
		} else if (builder.record != null) {
			this.marc = this.getMarcString(builder.record);
		}
	}

	public static class MaterialBuilder {

		private String materialNo = String.valueOf(System.currentTimeMillis());
		private String marc;
		private String status = CataloguingStatusEnum.INDEXED;
		private String type = CATALOGUING;
		private Record record;

		public MaterialBuilder(String marc) {
			this.marc = marc;
		}

		public MaterialBuilder(Record record) {
			this.record = record;
		}

		public MaterialBuilder materialNo(String materialNo) {
			this.materialNo = materialNo;
			return this;
		}

		public MaterialBuilder status(String status) {
			this.status = status;
			return this;
		}

		public MaterialBuilder type(String type) {
			this.type = type;
			return this;
		}

		public Material build() {
			return new Material(this);
		}
	}

	@PreUpdate
	@PostPersist
	public void postPersist() {
		this.setMaterialNo(String.format("%010d", this.getId()));
		this.title = getTitleFromVufindMarc();
		this.setMarc(reconstructMarcRecord());
	}

	@Autowired
	public void setReservationTransactionFactory(
			ReservationTransactionFactory reservationTransactionFactory) {
		this.reservationTransactionFactory = reservationTransactionFactory;
	}

	@Autowired
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getMaterialNo() {
		return materialNo;
	}

	public void setMaterialNo(String materialNo) {
		this.materialNo = materialNo;
	}

	public String getMarc() {
		return marc;
	}

	public void setMarc(String marc) {
		this.marc = marc;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Officer getReserveOfficer() {
		return reserveOfficer;
	}

	public void setReserveOfficer(Officer reserveOfficer) {
		this.reserveOfficer = reserveOfficer;
	}

	public Patron getReserver() {
		return reserver;
	}

	public void setReserver(Patron reserver) {
		this.reserver = reserver;
	}

	public Date getReserveDateTime() {
		return reserveDateTime;
	}

	public void setReserveDateTime(Date reserveDateTime) {
		this.reserveDateTime = reserveDateTime;
	}

	public Branch getReserverPickUpBranch() {
		return reserverPickUpBranch;
	}

	public void setReserverPickUpBranch(Branch reserverPickUpBranch) {
		this.reserverPickUpBranch = reserverPickUpBranch;
	}

	@Autowired
	public void setItemRepo(ItemRepositoryImpl itemRepo) {
		this.itemRepo = itemRepo;
	}

	public boolean isValid() {
		return jsVerifier.verify(verificationScripts, this.getJsonString());
	}

	public Boolean isPendingIndex() {
		return isStatus(CataloguingStatusEnum._PENDING_INDEX);
	}

	public Boolean isPendingDelete() {
		return isStatus(CataloguingStatusEnum._PENDING_DELETE);
	}

	private Boolean isStatus(String status) {
		return this.status.equalsIgnoreCase(status);
	}

	public String getIsbn() {
		LinkedHashSet<String> isbn = getIsbnFromVufindMarc();
		if (isbn != null){
			if (isbn.size() > 0) {
				StringBuilder stringBuilder = new StringBuilder();
	
				for (String n : isbn) {
					stringBuilder.append(n).append(",");
				}
	
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
	
				return stringBuilder.toString();
			}
		}
		return "";
	}

	public String getAuthor() {
		return getAuthorFromVufindMarc();
	}

	public String getEdition() {
		return getEditionFromVufindMarc();
	}

	protected String getTitleFromVufindMarc() {
		Assert.notNull(this.getMarc(), "Marc is null, unable to get title");
		return integralIndexer.mapVufindMarc(this.getMarcRecord())
				.getTitle_full();
	}

	protected LinkedHashSet<String> getIsbnFromVufindMarc() {
		Assert.notNull(this.getMarc(), "Marc is null, unable to get isbn");
		return integralIndexer.mapVufindMarc(this.getMarcRecord()).getIsbn();
	}

	protected String getAuthorFromVufindMarc() {
		Assert.notNull(this.getMarc(), "Marc is null, unable to get author");
		return integralIndexer.mapVufindMarc(this.getMarcRecord()).getAuthor();
	}

	protected String getEditionFromVufindMarc() {
		Assert.notNull(this.getMarc(), "Marc is null, unable to get edition");
		return integralIndexer.mapVufindMarc(this.getMarcRecord()).getEdition();
	}

	protected String reconstructMarcRecord() {
		Record record = this.getMarcRecord();
		record = moveLocControlNumber(record);
		record.setId(Long.parseLong(this.getMaterialNo()));
		record = setMaterialNoToControlField001(record);
		return this.getMarcString(record);
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

	public String getMarcString(Record record) {
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

	public String getBib(String tag) {
		String bib = "";

		if (Integer.valueOf(tag) < 10) {
			List<ControlField> controlFields = this.getMarcRecord()
					.getControlFields();
			for (ControlField field : controlFields) {
				if (field.getTag().equals(tag)) {
					bib = bib + field.getData();
				}
			}
		} else {
			List<DataField> fields = this.getMarcRecord().getDataFields();
			for (DataField field : fields) {
				if (field.getTag().equals(tag)) {
					List<Subfield> subfields = field.getSubfields();
					for (Subfield subfield : subfields) {
						bib = bib + subfield.toString().substring(2);
					}
				}
			}
		}
		return bib;
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

	private Record moveLocControlNumber(Record record) {
		if (getControlField(record, "001") != null
				&& getControlField(record, "001").length() > 0) {
			if (getTagSubfieldData(record, "010", 'a').length() == 0) {
				return switchControlField(record, "001", this.materialNo);
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
				field.setData(this.materialNo);
				return record;
			}
		}

		record.addVariableField(factory.newControlField("001", this.materialNo));
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

	public ReservationTransaction reserve(Officer officer, Patron patron,
			Branch pickUpBranch, Date transactionDate) {
		logger.debug("Entering reserve(officer={}, patron={}, "
				+ " pickUpBranch={})", new Object[] { officer, patron,
				pickUpBranch });
		Assert.notNull(patron);
		Assert.notNull(pickUpBranch);

		this.setReserver(patron);
		this.setReserverPickUpBranch(pickUpBranch);
		this.setReserveDateTime(transactionDate);
		this.setReserveOfficer(officer);

		ReservationTransaction reservationTransaction = reservationTransactionFactory
				.createReservationTransactionByMaterial(this);

		publisher.publish(CirculationEvents.RESERVE, new ReserveEventCommand(
				reservationTransaction));

		logger.debug("Leaving reserve(). reservationTransaction={}",
				reservationTransaction);
		return reservationTransaction;

	}

	public List<Item> getItems() {
		List<Item> itemList = itemRepo.findByMaterial(this);
		return itemList;
	}

}
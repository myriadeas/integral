package my.com.myriadeas.integral.data.jpa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.circulation.CirculationEvents;
import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.ReservationStatus;
import my.com.myriadeas.integral.circulation.command.CheckInEventCommand;
import my.com.myriadeas.integral.circulation.command.CheckOutEventCommand;
import my.com.myriadeas.integral.circulation.command.ReleaseEventCommand;
import my.com.myriadeas.integral.circulation.command.RenewEventCommand;
import my.com.myriadeas.integral.circulation.command.ReserveEventCommand;
import my.com.myriadeas.integral.circulation.exception.NoAwaitingCollectionReserverException;
import my.com.myriadeas.integral.circulation.exception.NoCirculationTransactionException;
import my.com.myriadeas.integral.circulation.exception.ReservationNotFoundException;
import my.com.myriadeas.integral.circulation.service.DueDateCalculationService;
import my.com.myriadeas.integral.circulation.validation.CheckInPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyItemValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronItemValidator;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ReservationTransactionRepositoryImpl;
import my.com.myriadeas.integral.eligibility.ItemEligibilityLookup;
import my.com.myriadeas.integral.eligibility.PatronItemEligibilityLookup;
import my.com.myriadeas.integral.publisher.Publisher;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the CTDOCM database table.
 * 
 */
@Entity
@Configurable
public class Item extends AbstractDomain {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(Item.class);

	@JsonIgnore
	@Transient
	private transient DueDateCalculationService dueDateCalculationService;
	@JsonIgnore
	@Transient
	protected transient Publisher publisher;
	@JsonIgnore
	@Transient
	private transient ItemRepositoryImpl itemRepo;
	@JsonIgnore
	@Transient
	private transient CirculationTransactionFactory circulationTransactionFactory;
	@JsonIgnore
	@Transient
	private transient ReservationTransactionFactory reservationTransactionFactory;
	@JsonIgnore
	@Transient
	private transient CheckOutPolicyItemValidator checkOutPolicyItemValidator;
	@JsonIgnore
	@Transient
	private transient CheckInPolicyItemValidator checkInPolicyItemValidator;
	@JsonIgnore
	@Transient
	private transient RenewPolicyItemValidator renewPolicyItemValidator;
	@JsonIgnore
	@Transient
	private transient RecallPolicyItemValidator recallPolicyItemValidator;
	@JsonIgnore
	@Transient
	private transient ReservePolicyItemValidator reservePolicyItemValidator;
	@JsonIgnore
	@Transient
	private transient CheckOutPolicyPatronItemValidator checkOutPolicyPatronItemValidator;
	@JsonIgnore
	@Transient
	private transient RenewPolicyPatronItemValidator renewPolicyPatronItemValidator;
	@JsonIgnore
	@Transient
	private transient RecallPolicyPatronItemValidator recallPolicyPatronItemValidator;
	@JsonIgnore
	@Transient
	private transient ReservePolicyPatronItemValidator reservePolicyPatronItemValidator;
	@JsonIgnore
	@Transient
	private transient CirculationTransactionRepositoryImpl circulationTransactionRepo;
	@JsonIgnore
	@Transient
	private transient ReservationTransactionRepositoryImpl reservationTransactionRepo;
	
	
	
	@JsonIgnore
	@Transient
	private transient PatronItemEligibilityLookup patronItemEligibilityLookup;

	@JsonIgnore
	@Transient
	private transient ItemEligibilityLookup itemEligibilityLookup;

	@JsonIgnore
	@Transient
	private transient PatronRepositoryImpl patronRepository;

	@Length(max = 12)
	@NotBlank
	@Column(unique = true)
	private String itemIdentifier;

	private Date chargeDateTime;

	@ManyToOne(optional = false)
	@NotNull
	private Condition condition;

	@Length(max = 20)
	private String copyNumber;

	private Date dueDateTime;

	@ManyToOne(optional = false)
	@NotNull
	private ItemCategory itemCategory;

	@ManyToOne(optional = false)
	@NotNull
	private Location location;

	@ManyToOne(optional = false)
	@NotNull
	private Material material;

	private BigDecimal foreignCost = new BigDecimal("0");

	private BigDecimal localCost = new BigDecimal("0");

	@ManyToOne(optional = true)
	private Patron patron;

	private Date releaseDate;

	@ManyToOne(optional = true)
	private Officer releaseOfficer;

	@ManyToOne(optional = false)
	@NotNull
	private SMD smd;

	@NotNull
	private ItemStatus itemStatus = ItemStatus.FINAL_PROCESSING;

	@Length(max = 80)
	private String volume;

	@JsonIgnore
	@Transient
	private transient Officer circulationOfficer;
	@JsonIgnore
	@Transient
	private transient Date dischargeDateTime;
	@JsonIgnore
	@Transient
	private transient Date renewDateTime;
	@JsonIgnore
	@Transient
	private transient Patron reserver;
	@JsonIgnore
	@Transient
	private transient Date reserveDateTime;
	@JsonIgnore
	@Transient
	private transient Branch reserverPickUpBranch;

	public Item() {
	}

	public Item(String itemIdentifier, ItemCategory itemCategory,
			Location location, Material material, SMD smd, ItemStatus status,
			Condition condition) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.itemCategory = itemCategory;
		this.location = location;
		this.material = material;
		this.smd = smd;
		this.itemStatus = status;
		this.condition = condition;
	}
	
	public Item(String itemIdentifier, ItemCategory itemCategory,
			Location location, Material material, SMD smd, Condition condition) {
		super();
		this.itemIdentifier = itemIdentifier;
		this.itemCategory = itemCategory;
		this.location = location;
		this.material = material;
		this.smd = smd;
		this.itemStatus = ItemStatus.FINAL_PROCESSING;
		this.condition = condition;
	}

	
	@JsonIgnore
	@Autowired
	public void setDueDateCalculationService(
			DueDateCalculationService dueDateCalculationService) {
		this.dueDateCalculationService = dueDateCalculationService;
	}
	
	@JsonIgnore
	@Autowired
	public void setCirculationTransactionRepo(
			CirculationTransactionRepositoryImpl circulationTransactionRepo) {
		this.circulationTransactionRepo = circulationTransactionRepo;
	}
	
	@JsonIgnore
	@Autowired
	public void setCirculationTransactionFactory(
			CirculationTransactionFactory circulationTransactionFactory) {
		this.circulationTransactionFactory = circulationTransactionFactory;
	}

	@JsonIgnore
	@Autowired
	public void setReservationTransactionFactory(
			ReservationTransactionFactory reservationTransactionFactory) {
		this.reservationTransactionFactory = reservationTransactionFactory;
	}

	@JsonIgnore
	@Autowired
	public void setReservationTransactionRepo(
			ReservationTransactionRepositoryImpl reservationTransactionRepo) {
		this.reservationTransactionRepo = reservationTransactionRepo;
	}

	@JsonIgnore
	@Autowired
	public void setPatronRepository(PatronRepositoryImpl patronRepository) {
		this.patronRepository = patronRepository;
	}

	@JsonIgnore
	@Autowired
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@JsonIgnore
	@Autowired
	public void setItemRepo(ItemRepositoryImpl itemRepo) {
		this.itemRepo = itemRepo;
	}

	@JsonIgnore
	@Autowired
	public void setItemEligibilityLookup(
			ItemEligibilityLookup itemEligibilityLookup) {
		this.itemEligibilityLookup = itemEligibilityLookup;
	}

	@JsonIgnore
	@Autowired
	public void setPatronItemEligibilityLookup(
			PatronItemEligibilityLookup patronItemEligibilityLookup) {
		this.patronItemEligibilityLookup = patronItemEligibilityLookup;
	}

	public ItemEligibility getItemEligibility() {
		return this.itemEligibilityLookup.lookup(this).getDomain();
	}

	public PatronItemEligibility getPatronItemEligibility(String username) {
		Patron patron = this.patronRepository.findByUsername(username);
		if (patron == null) {
			return null;
		}
		return this.getPatronItemEligibility(patron);
	}

	public PatronItemEligibility getPatronItemEligibility(Patron patron) {
		return this.patronItemEligibilityLookup.lookup(patron, this)
				.getDomain();
	}

	public String getItemIdentifier() {
		return this.itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public Date getChargeDateTime() {
		return this.chargeDateTime;
	}

	public void setChargeDateTime(Date chargeDateTime) {
		this.chargeDateTime = chargeDateTime;
	}

	public Condition getCondition() {
		return this.condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getCopyNumber() {
		return this.copyNumber;
	}

	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	public Date getDueDateTime() {
		return this.dueDateTime;
	}

	public void setDueDateTime(Date dueDateTime) {
		this.dueDateTime = dueDateTime;
	}

	public BigDecimal getForeignCost() {
		return this.foreignCost;
	}

	public void setForeignCost(BigDecimal foreignCost) {
		if (foreignCost == null) {
			foreignCost = new BigDecimal("0");
		}
		this.foreignCost = foreignCost;
	}

	public ItemCategory getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(ItemCategory itemCategory) {
		this.itemCategory = itemCategory;
	}

	public BigDecimal getLocalCost() {
		return this.localCost;
	}

	public void setLocalCost(BigDecimal localCost) {
		if (localCost == null) {
			localCost = new BigDecimal("0");
		}
		this.localCost = localCost;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@JsonIgnore
	//TODO - Cyclic dependency
	public Material getMaterial() {
		return this.material;
	}

	@JsonProperty
	public void setMaterial(Material material) {
		this.material = material;
	}

	public Patron getPatron() {
		return this.patron;
	}

	public void setPatron(Patron patron) {
		this.patron = patron;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Officer getReleaseOfficer() {
		return this.releaseOfficer;
	}

	public void setReleaseOfficer(Officer releaseOfficer) {
		this.releaseOfficer = releaseOfficer;
	}

	public SMD getSmd() {
		return this.smd;
	}

	public void setSmd(SMD smd) {
		this.smd = smd;
	}

	public ItemStatus getItemStatus() {
		return this.itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getVolume() {
		return this.volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Branch getBranch() {
		return this.location.getBranch();
	}

	public String getTitle() {
		return this.getMaterial().getTitle();
	}

	@JsonIgnore
	public Officer getCirculationOfficer() {
		return circulationOfficer;
	}

	@JsonIgnore
	public void setCirculationOfficer(Officer officer) {
		this.circulationOfficer = officer;
	}

	@JsonIgnore
	public Date getDischargeDateTime() {
		return dischargeDateTime;
	}

	@JsonIgnore
	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}

	@JsonIgnore
	public Date getRenewDateTime() {
		return renewDateTime;
	}
	
	@JsonIgnore
	public void setRenewDateTime(Date renewDateTime) {
		this.renewDateTime = renewDateTime;
	}

	@JsonIgnore
	public Patron getReserver() {
		return reserver;
	}

	@JsonIgnore
	public void setReserver(Patron reserver) {
		this.reserver = reserver;
	}

	@JsonIgnore
	public Date getReserveDateTime() {
		return reserveDateTime;
	}

	@JsonIgnore
	public void setReserveDateTime(Date reserveDateTime) {
		this.reserveDateTime = reserveDateTime;
	}

	@JsonIgnore
	public Branch getReserverPickUpBranch() {
		return reserverPickUpBranch;
	}

	@JsonIgnore
	public void setReserverPickUpBranch(Branch reserverPickUpBranch) {
		this.reserverPickUpBranch = reserverPickUpBranch;
	}

	public Boolean isPotentiallyLate() {
		return isStatus(ItemStatus._POTENTIALLY_LATE);
	}

	public Boolean isReturned() {
		return isStatus(ItemStatus._RETURNED);
	}

	public Boolean isAvailable() {
		return isStatus(ItemStatus.AVAILABLE);
	}

	public Boolean isBindery() {
		return isStatus(ItemStatus.BINDERY);
	}

	public Boolean isCirculated() {
		return isStatus(ItemStatus.CIRCULATED);
	}

	public Boolean isFinalProcessing() {
		return isStatus(ItemStatus.FINAL_PROCESSING);
	}

	public Boolean isFound() {
		return isStatus(ItemStatus.FOUND);
	}

	public Boolean isHold() {
		return isStatus(ItemStatus.HOLD);
	}

	public Boolean isLost() {
		return isStatus(ItemStatus.LOST);
	}

	public Boolean isMisplaced() {
		return isStatus(ItemStatus.MISPLACED);
	}

	public Boolean isMissing() {
		return isStatus(ItemStatus.MISSING);
	}

	public Boolean isRecalled() {
		return isStatus(ItemStatus.RECALLED);
	}

	public Boolean isSuspended() {
		return isStatus(ItemStatus.SUSPENDED);
	}

	public Boolean isWeed() {
		return isStatus(ItemStatus.WEED);
	}

	public Boolean isRenew() {
		return isStatus(ItemStatus.RENEW);
	}

	private Boolean isStatus(ItemStatus status) {
		return this.itemStatus.equals(status);
	}
	
	public Date calculateDueDateTime(Patron patron, Date transactionDate){
		Date dueDateTime = dueDateCalculationService.calculateDueDateTime(this,
				patron, transactionDate);
		return dueDateTime;
	}

	public CirculationTransaction checkOut(Officer officer, Patron patron,
			Date transactionDate) {
		logger.debug("Entering checkOut(officer={}, patron={}, "
				+ " transactionDate={})", new Object[] { officer, patron,
				transactionDate });
		Assert.notNull(patron);
		Assert.notNull(transactionDate);

		this.setPatron(patron);
		this.setCirculationOfficer(officer);
		this.setChargeDateTime(transactionDate);

		Date dueDateTime = dueDateCalculationService.calculateDueDateTime(this,
				patron, transactionDate);
		this.setDueDateTime(dueDateTime);
		updateCheckOutItem(transactionDate, dueDateTime, patron);

		CirculationTransaction circulationTransaction = createCheckOutCirculationTransaction();

		publisher.publish(CirculationEvents.CHECKOUT, new CheckOutEventCommand(
				this, circulationTransaction));
		logger.debug("Leaving checkOut(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;

	}

	public CirculationTransaction checkIn(Officer officer, Date transactionDate) {
		logger.debug("Entering checkIn(officer={}, transactionDate={})",
				new Object[] { officer, transactionDate });

		this.setCirculationOfficer(officer);
		this.setDischargeDateTime(transactionDate);

		CirculationTransaction circulationTransaction = updateCheckInCirculationTransaction();
		updateCheckInItem();
		
		publisher.publish(CirculationEvents.CHECKIN, new CheckInEventCommand(
				this, circulationTransaction));
		logger.debug("Leaving checkIn(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;
	}
	
	public CirculationTransaction renew(Officer officer, Date transactionDate) {
		logger.debug("Entering renew(officer={}, transactionDate={})",
				new Object[] { officer, transactionDate });

		this.setCirculationOfficer(officer);
		this.setRenewDateTime(transactionDate);

		Date dueDateTime = dueDateCalculationService.calculateDueDateTime(this,
				patron, transactionDate);
		
		CirculationTransaction oldCirculationTransaction = updateRenewCirculationTransaction();
		CirculationTransaction newCirculationTransaction = createRenewCirculationTransaction(oldCirculationTransaction);
		
		updateRenewItem(dueDateTime);

		publisher.publish(CirculationEvents.RENEW, new RenewEventCommand(
				this, oldCirculationTransaction, newCirculationTransaction));
		logger.debug("Leaving renew(). newCirculationTransaction={}",
				newCirculationTransaction);
		return newCirculationTransaction;
	}

	public ReservationTransaction reserve(Officer officer, Patron patron,
			Branch pickUpBranch, Date transactionDate) {
		logger.debug("Entering reserve(officer={}, patron={}, "
				+ " pickUpBranch={}, transactionDate={})", new Object[] {
				officer, patron, pickUpBranch, transactionDate });
		Assert.notNull(patron);
		Assert.notNull(pickUpBranch);

		this.setReserver(patron);
		this.setReserverPickUpBranch(pickUpBranch);
		this.setReserveDateTime(transactionDate);
		this.setCirculationOfficer(officer);

		ReservationTransaction reservationTransaction = reservationTransactionFactory
				.createReservationTransactionByItem(this);

		publisher.publish(CirculationEvents.RESERVE, new ReserveEventCommand(
				reservationTransaction));

		logger.debug("Leaving reserve(). reservationTransaction={}",
				reservationTransaction);
		return reservationTransaction;

	}

	public void release(Officer officer, Date transactionDate) {
		logger.debug("Entering release(officer={}, transactionDate={})",
				new Object[] { officer, transactionDate });

		this.setReleaseDate(transactionDate);
		this.setReleaseOfficer(officer);

		updateReleaseItem(officer, transactionDate);
		publisher.publish(CirculationEvents.RELEASE, new ReleaseEventCommand(
				this));
		logger.debug("Leaving release(). item={}", this);

	}

	

	@JsonIgnore
	public List<CirculationTransaction> getCirculationTransactionsWithFlagDischarged() {
		List<CirculationTransaction> circulationTransactions = getCirculationTransactionsByFlag(CirculationTransactionFlag.DISCHARGED);
		return circulationTransactions;
	}

	@JsonIgnore
	private List<CirculationTransaction> getCirculationTransactionsByFlag(
			String status) {
		List<CirculationTransaction> circulationTransactions = circulationTransactionRepo
				.findByItemAndFlag(this, status);
		if (circulationTransactions.isEmpty()) {
			throw new NoCirculationTransactionException(
					"No circulation transaction.");
		}
		return circulationTransactions;
	}

	@JsonIgnore
	public List<CirculationTransaction> getCirculationTransactions() {
		List<CirculationTransaction> circulationTransactions = circulationTransactionRepo
				.findByItem(this);
		if (circulationTransactions.isEmpty()) {
			throw new NoCirculationTransactionException(
					"No circulation transaction.");
		}
		return circulationTransactions;
	}

	@JsonIgnore
	public ReservationTransaction getAwaitingCollectionReservationTransaction() {
		logger.debug("Entering getAwaitingCollectionReserver()");

		ReservationTransaction reservationTransaction = null;

		List<ReservationTransaction> reservationTransactionList = reservationTransactionRepo
				.findByStatusAndItem(ReservationStatus.AWAITING_COLLECTION,
						this);
		if (reservationTransactionList == null
				|| reservationTransactionList.isEmpty()) {
			String err = "No awaiting collection reserver found in reservationTransaction.";
			logger.error(err);
			throw new NoAwaitingCollectionReserverException(err);
		} else {
			reservationTransaction = reservationTransactionList.get(0);
		}

		logger.debug(
				"Leaving getAwaitingCollectionReserver(). reservationTransaction={}",
				reservationTransaction);
		return reservationTransaction;
	}

	@JsonIgnore
	public CirculationTransaction getCirculationTransactionWithFlagCharged() {
		logger.debug("Entering getCirculationTransactionWithFlagCharged()");
		List<CirculationTransaction> circulationTransactionList = circulationTransactionRepo
				.findByPatronAndItemAndFlag(this.getPatron(), this,
						CirculationTransactionFlag.CHARGED);
		logger.debug("Leaving getCirculationTransactionWithFlagCharged().");
		return circulationTransactionList.get(0);
	}

	protected void updateCheckInItem() {
		logger.debug("Entering updateCheckInItem()");
		populateCheckInItem();
		itemRepo.save(this);
		logger.debug("Leaving updateCheckInItem().");
	}

	protected void updateRenewItem(Date dueDateTime) {
		logger.debug("Entering updateRenewItem()");
		populateRenewItem(dueDateTime);
		itemRepo.save(this);
		logger.debug("Leaving updateRenewItem().");
	}

	protected void updateCheckOutItem(Date chargeDateTime, Date dueDateTime,
			Patron patron) {
		logger.debug(
				"Entering updateCheckOutItem(chargeDateTime={}, dueDateTime={}, patron={}",
				new Object[] { chargeDateTime, dueDateTime, patron });
		populateCheckOutItem(chargeDateTime, dueDateTime, patron);
		logger.debug("Saving Item. item={}", this);
		itemRepo.save(this);
		logger.debug("Saved item");
		logger.debug("Leaving updateCheckOutItem(). item={}", this);

	}

	protected void updateReleaseItem(Officer officer, Date releaseDateTime) {
		logger.debug(
				"Entering updateReleaseItem(officer={}, releaseDateTime={}",
				new Object[] { officer, releaseDateTime });
		populateReleaseItem(officer, releaseDateTime);
		logger.debug("Saving Item. item={}", this);
		itemRepo.save(this);
		logger.debug("Saved item");
		logger.debug("Leaving updateReleaseItem(). item={}", this);
	}

	protected void populateCheckOutItem(Date chargeDateTime, Date dueDateTime,
			Patron patron) {
		logger.debug(
				"Entering populateCheckOutItem(chargeDateTime={}, dueDateTime={}, "
						+ "patron={})", new Object[] { chargeDateTime,
						dueDateTime, patron });
		Assert.notNull(chargeDateTime);
		Assert.notNull(dueDateTime);
		Assert.notNull(patron);

		this.setChargeDateTime(chargeDateTime);
		this.setDueDateTime(dueDateTime);
		ItemStatus itemStatus = ItemStatus.CIRCULATED;
		this.setItemStatus(itemStatus);
		this.setPatron(patron);

		logger.debug("Leaving populateCheckOutItem().");

	}

	protected void populateCheckInItem() {
		logger.debug("Entering populateCheckInItem()");
		this.setItemStatus(ItemStatus._RETURNED);
		logger.debug("Leaving populateCheckInItem(). item={}", this);
	}

	protected void populateRenewItem(Date dueDateTime) {
		logger.debug("Entering populateRenewItem()");

		this.setDueDateTime(dueDateTime);
		this.setPatron(this.patron);
		this.setItemStatus(ItemStatus.RENEW);
		logger.debug("Leaving populateRenewItem(). item={}", this);
	}

	protected void populateReleaseItem(Officer officer, Date releaseDateTime) {
		logger.debug("Entering populateReleaseItem(item={})", this);
		this.setItemStatus(ItemStatus._RETURNED);
		logger.debug("Leaving populateReleaseItem(). item={}", this);
	}

	protected CirculationTransaction createCheckOutCirculationTransaction() {
		logger.debug("Entering createCheckOutCirculationTransaction()");
		CirculationTransaction circulationTransaction = circulationTransactionFactory
				.createCheckOutCirculationTransaction(this);

		logger.debug(
				"Leaving createCheckOutCirculationTransaction(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;
	}

	protected CirculationTransaction createRenewCirculationTransaction(
			CirculationTransaction parentCirculationTransaction) {
		logger.debug("Entering createRenewCirculationTransaction()");
		CirculationTransaction circulationTransaction = circulationTransactionFactory
				.createRenewCirculationTransaction(this,
						parentCirculationTransaction);

		logger.debug(
				"Leaving createRenewCirculationTransaction(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;
	}

	protected CirculationTransaction updateCheckInCirculationTransaction() {
		logger.debug("Entering updateCheckInCirculationTransaction()");

		CirculationTransaction circulationTransaction = circulationTransactionFactory
				.updateCheckInCirculationTransaction(this);

		logger.debug(
				"Leaving updateCheckInCirculationTransaction(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;
	}

	protected CirculationTransaction updateRenewCirculationTransaction() {
		logger.debug("Entering updateRenewCirculationTransaction()");

		CirculationTransaction circulationTransaction = circulationTransactionFactory
				.updateRenewCirculationTransaction(this);

		logger.debug(
				"Leaving updateRenewCirculationTransaction(). circulationTransaction={}",
				circulationTransaction);
		return circulationTransaction;
	}

	protected void validateForCheckOut(Patron patron, Item item) {
		logger.debug("Entering validateForCheckOut(patron={}, item={})",
				new Object[] { patron, item });

		checkOutPolicyItemValidator.validate(item);
		checkOutPolicyPatronItemValidator.validate(patron, item);

		logger.debug("Leaving validateForCheckOut(). ");
	}

	protected void validateForCheckIn(Patron patron, Item item) {
		logger.debug("Entering validateForCheckIn(patron={}, item={})",
				new Object[] { patron, item });

		checkInPolicyItemValidator.validate(item);

		logger.debug("Leaving validateForCheckIn(). ");
	}

	protected void validateForRenew(Patron patron, Item item) {
		logger.debug("Entering validateForRenew(patron={}, item={})",
				new Object[] { patron, item });

		renewPolicyItemValidator.validate(item);
		renewPolicyPatronItemValidator.validate(patron, item);

		logger.debug("Leaving validateForRenew(). ");
	}

	protected void validateForRecall(Patron patron, Item item) {
		logger.debug("Entering validateForRecall(patron={}, item={})",
				new Object[] { patron, item });

		recallPolicyItemValidator.validate(item);
		recallPolicyPatronItemValidator.validate(patron, item);

		logger.debug("Leaving validateForRecall(). ");
	}

	protected void validateForReserve(Patron patron, Item item) {
		logger.debug("Entering validateForReserve(patron={}, item={})",
				new Object[] { patron, item });

		reservePolicyItemValidator.validate(item);
		reservePolicyPatronItemValidator.validate(patron, item);

		logger.debug("Leaving validateForReserve(). ");
	}

	public Boolean isThereAnyReservation() {
		logger.debug("Entering isThereAnyReservation()");
		Boolean anyReservation = false;
		List<ReservationTransaction> reservationTransactions = findAllReservations();
		if (reservationTransactions == null
				|| reservationTransactions.isEmpty()) {
			anyReservation = false;
		} else {
			anyReservation = true;
		}
		logger.debug("Leaving isThereAnyReservation(). anyReservation={}",
				anyReservation);
		return anyReservation;
	}

	public List<ReservationTransaction> findAllReservations() {
		logger.debug("Entering findAllReservations()");
		Assert.notNull(this.getMaterial());
		logger.debug("this.getMaterial() = " + this.getMaterial());
		List<ReservationTransaction> reservationTransactions = new ArrayList<ReservationTransaction>();
		logger.debug(
				"Entering findByStatusAndMaterialOrderByPriorityWeight(status={}, material={}) "
						+ this.getMaterial(), ReservationStatus.RESERVE,
				this.getMaterial());
		logger.debug("reservationTransactionRepo = "
				+ reservationTransactionRepo);

		try {
			reservationTransactions = reservationTransactionRepo
					.findByStatusAndMaterialOrderByPriorityWeight(
							ReservationStatus.RESERVE, this.getMaterial());
			// reservationTransactions = reservationTransactionRepo.findAll();
		} catch (Exception e) {
			logger.debug("has exception");
			logger.debug(e.toString());
		}
		logger.debug(
				"Leaving findAllReservations(). reservationTransactions={}",
				reservationTransactions);
		return reservationTransactions;
	}

	public ReservationTransaction findFirstQualifyReserver()
			throws ReservationNotFoundException {
		logger.debug("Entering findFirstQualifyReserver()");

		ReservationTransaction firstQualify = null;
		List<ReservationTransaction> reservationTransactions = findAllReservations();
		for (ReservationTransaction reservationTransaction : reservationTransactions) {
			// boolean isCorrectReserver = false;
			if (reservationTransaction.getMaterial().equals(this.getMaterial())) {
				if (reservationTransaction.getItemCategory() == null
						&& reservationTransaction.getItem() == null) {
					// isCorrectReserver = true; // -patron is the correct
					// reserver
					firstQualify = reservationTransaction;
				} else if (!(reservationTransaction.getItem() == null)) {
					if (reservationTransaction.getItem().equals(this)) {
						// isCorrectReserver = true; // -patron is the correct
						// reserver
						firstQualify = reservationTransaction;
					} else {
						// isCorrectReserver = false; // -patron is not the
						// reserver
					}
				} else if (reservationTransaction.getItemCategory().equals(
						this.getItemCategory())) {
					// isCorrectReserver = true; // -patron is the correct
					// reserver
					firstQualify = reservationTransaction;
				}
			}

			/*
			 * if (isCorrectReserver){ Glpatr reserver = ciresv.getCi03patr();
			 * Glbrnc itemBranch = ctdocm.getBranch(); //String pickupBranchTemp
			 * = reservation.getPickupBranch();
			 * 
			 * if (reserver != null){ if
			 * (glflag2Service.isReserveByBranchActivated() &&
			 * (!itemBranch.equals(reserver.getGl14brnc()))){ //BlockByLocation
			 * //go next record } else { if
			 * (reserver.getGl14cate().allowReserve() && canPatronHold(ctdocm,
			 * reserver.getGl14cate())) { return ciresv; }
			 * 
			 * } } }
			 */
		}
		if (firstQualify == null) {
			throw new ReservationNotFoundException("reservation not found");
		}

		logger.debug(
				"Leaving findFirstQualifyReserverByCtdocm(). firstQualify={}",
				firstQualify);
		return firstQualify;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((chargeDateTime == null) ? 0 : chargeDateTime.hashCode());
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		result = prime * result
				+ ((copyNumber == null) ? 0 : copyNumber.hashCode());
		result = prime * result
				+ ((dueDateTime == null) ? 0 : dueDateTime.hashCode());
		result = prime * result
				+ ((foreignCost == null) ? 0 : foreignCost.hashCode());
		result = prime * result
				+ ((itemCategory == null) ? 0 : itemCategory.hashCode());
		result = prime * result
				+ ((itemIdentifier == null) ? 0 : itemIdentifier.hashCode());
		result = prime * result
				+ ((localCost == null) ? 0 : localCost.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((patron == null) ? 0 : patron.hashCode());
		result = prime * result
				+ ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result
				+ ((releaseOfficer == null) ? 0 : releaseOfficer.hashCode());
		result = prime * result + ((smd == null) ? 0 : smd.hashCode());
		result = prime * result + ((itemStatus == null) ? 0 : itemStatus.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (chargeDateTime == null) {
			if (other.chargeDateTime != null)
				return false;
		} else if (!chargeDateTime.equals(other.chargeDateTime))
			return false;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		if (copyNumber == null) {
			if (other.copyNumber != null)
				return false;
		} else if (!copyNumber.equals(other.copyNumber))
			return false;
		if (dueDateTime == null) {
			if (other.dueDateTime != null)
				return false;
		} else if (!dueDateTime.equals(other.dueDateTime))
			return false;
		if (foreignCost == null) {
			if (other.foreignCost != null)
				return false;
		} else if (!foreignCost.equals(other.foreignCost))
			return false;
		if (itemCategory == null) {
			if (other.itemCategory != null)
				return false;
		} else if (!itemCategory.equals(other.itemCategory))
			return false;
		if (itemIdentifier == null) {
			if (other.itemIdentifier != null)
				return false;
		} else if (!itemIdentifier.equals(other.itemIdentifier))
			return false;
		if (localCost == null) {
			if (other.localCost != null)
				return false;
		} else if (!localCost.equals(other.localCost))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (patron == null) {
			if (other.patron != null)
				return false;
		} else if (!patron.equals(other.patron))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (releaseOfficer == null) {
			if (other.releaseOfficer != null)
				return false;
		} else if (!releaseOfficer.equals(other.releaseOfficer))
			return false;
		if (smd == null) {
			if (other.smd != null)
				return false;
		} else if (!smd.equals(other.smd))
			return false;
		if (itemStatus == null) {
			if (other.itemStatus != null)
				return false;
		} else if (!itemStatus.equals(other.itemStatus))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [itemIdentifier=" + itemIdentifier + ", chargeDateTime="
				+ chargeDateTime + ", condition=" + condition + ", copyNumber="
				+ copyNumber + ", dueDateTime=" + dueDateTime
				+ ", itemCategory=" + itemCategory + ", location=" + location
				+ ", material=" + material + ", foreignCost=" + foreignCost
				+ ", localCost=" + localCost + ", patron=" + patron
				+ ", releaseDate=" + releaseDate + ", releaseOfficer="
				+ releaseOfficer + ", smd=" + smd + ", status=" + itemStatus
				+ ", volume=" + volume + "]";
	}
}
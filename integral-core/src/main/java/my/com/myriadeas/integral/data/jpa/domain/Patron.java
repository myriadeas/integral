package my.com.myriadeas.integral.data.jpa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import my.com.myriadeas.integral.circulation.CirculationTransactionFlag;
import my.com.myriadeas.integral.circulation.validation.CheckOutPolicyPatronValidator;
import my.com.myriadeas.integral.circulation.validation.RecallPolicyPatronValidator;
import my.com.myriadeas.integral.circulation.validation.RenewPolicyPatronValidator;
import my.com.myriadeas.integral.circulation.validation.ReservePolicyPatronValidator;
import my.com.myriadeas.integral.data.jpa.repositories.impl.CirculationTransactionRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.ItemRepositoryImpl;
import my.com.myriadeas.integral.data.jpa.repositories.impl.PatronRepositoryImpl;
import my.com.myriadeas.integral.eligibility.PatronEligibilityLookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Configurable
public class Patron extends AbstractUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(Patron.class);

	@ManyToOne(optional = true)
	private PatronCategory patronCategory;

	@ManyToOne(optional = true)
	private Course course;

	@NotNull
	// @Future
	@Temporal(TemporalType.DATE)
	private Date membershipExpiryDate;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date membershipDate;

	@Transient
	@JsonIgnore
	private CheckOutPolicyPatronValidator checkOutPolicyPatronValidator;
	@Transient
	@JsonIgnore
	private RenewPolicyPatronValidator renewPolicyPatronValidator;
	@Transient
	@JsonIgnore
	private RecallPolicyPatronValidator recallPolicyPatronValidator;
	@Transient
	@JsonIgnore
	private ReservePolicyPatronValidator reservePolicyPatronValidator;
	@Transient
	@JsonIgnore
	private ItemRepositoryImpl itemRepo;
	@Transient
	@JsonIgnore
	private CirculationTransactionRepositoryImpl circulationTransactionRepo;
	@Transient
	@JsonIgnore
	private PatronEligibilityLookup patronEligibilityLookup;
	@Transient
	@JsonIgnore
	private PatronRepositoryImpl patronRepo;

	public Patron() {
		super();
	}

	public Patron(String username, String password, String firstname,
			Date membershipExpiryDate, Branch branch,
			PatronCategory patronCategory) {
		
		super(username, password, firstname, branch);
		this.patronCategory = patronCategory;
		this.membershipExpiryDate = membershipExpiryDate;
		this.membershipDate = new Date();

	}

	

	@Autowired
	public void setItemRepo(ItemRepositoryImpl itemRepo) {
		this.itemRepo = itemRepo;
	}

	@Autowired
	public void setCirculationTransactionRepo(
			CirculationTransactionRepositoryImpl circulationTransactionRepo) {
		this.circulationTransactionRepo = circulationTransactionRepo;
	}

	public void renew(String itemId) {
		Item item = new Item();
	}

	@Autowired
	public void setPatronEligibilityLookup(
			PatronEligibilityLookup patronEligibilityLookup) {
		this.patronEligibilityLookup = patronEligibilityLookup;
	}

	@Autowired
	public void setPatronRepo(PatronRepositoryImpl patronRepo) {
		this.patronRepo = patronRepo;
	}

	public PatronEligibility getPatronEligibility() {
		return this.patronEligibilityLookup.lookup(this).getDomain();
	}

	protected void validateForCheckOut(Patron patron) {
		logger.debug("Entering validateForCheckOut(patron={})", patron);
		checkOutPolicyPatronValidator.validate(patron);
		logger.debug("Leaving validateForCheckOut(). ");
	}

	protected void validateForRecall(Patron patron) {
		logger.debug("Entering validateForRecall(patron={})", patron);
		recallPolicyPatronValidator.validate(patron);
		logger.debug("Leaving validateForRecall(). ");
	}

	protected void validateForRenew(Patron patron) {
		logger.debug("Entering validateForRenew(patron={})", patron);
		renewPolicyPatronValidator.validate(patron);
		logger.debug("Leaving validateForRenew(). ");
	}

	protected void validateForReserve(Patron patron) {
		logger.debug("Entering validateForReserve(patron={})", patron);
		reservePolicyPatronValidator.validate(patron);
		logger.debug("Leaving validateForReserve(). ");
	}

	@JsonIgnore
	public List<Item> getOnLoanItems() {
		logger.debug("Entering getOnLoanItems()");
		List<Item> onLoanItems = itemRepo.findByPatronAndItemStatus(this,
				getCirculatedItemStatusList(), null);
		logger.debug("Leaving getOnLoanItems(). onLoanItems={}", onLoanItems);
		return onLoanItems;
	}

	@JsonIgnore
	public List<Item> getOnLoanItemsFilterByItemCategoriesAndSmdsAndLocations(
			List<ItemCategory> itemCategoryList, List<SMD> smdList,
			List<Location> locationList) {
		logger.debug(
				"Entering getOnLoanItemsFilterByItemCategoriesAndSmdsAndLocations(itemCategoryList={}, smdList={}, locationList={})",
				new Object[] { itemCategoryList, smdList, locationList });
		Assert.notEmpty(itemCategoryList);
		Assert.notEmpty(smdList);
		Assert.notEmpty(locationList);

		List<Item> onLoanItems = itemRepo
				.findByPatronAndItemStatusAndItemCategoryAndSmdAndLocation(this,
						getCirculatedItemStatusList(), itemCategoryList,
						smdList, locationList, null);

		logger.debug(
				"Leaving getOnLoanItemsFilterByItemCategoriesAndSmdsAndLocations(). onLoanItems={}",
				onLoanItems);
		return onLoanItems;
	}

	@JsonIgnore
	public List<Item> getOnLoanItemsFilterByItemCategoriesAndSmds(
			List<ItemCategory> itemCategoryList, List<SMD> smdList) {
		logger.debug(
				"Entering getOnLoanItemsFilterByItemCategoriesAndSmds(itemCategoryList={}, smdList={})",
				new Object[] { itemCategoryList, smdList });
		Assert.notEmpty(itemCategoryList);
		Assert.notEmpty(smdList);

		List<Item> onLoanItems = itemRepo
				.findByPatronAndItemStatusAndItemCategoryAndSmd(this,
						getCirculatedItemStatusList(), itemCategoryList,
						smdList, null);

		logger.debug(
				"Leaving getOnLoanItemsFilterByItemCategoriesAndSmds(). onLoanItems={}",
				onLoanItems);
		return onLoanItems;
	}

	@JsonIgnore
	public List<Item> getOnLoanItemsFilterByItemCategories(
			List<ItemCategory> itemCategoryList) {
		logger.debug(
				"Entering getOnLoanItemsFilterByItemCategories(itemCategoryList={})",
				new Object[] { itemCategoryList });
		Assert.notEmpty(itemCategoryList);

		List<Item> onLoanItems = itemRepo.findByPatronAndItemStatusAndItemCategory(
				this, getCirculatedItemStatusList(), itemCategoryList, null);

		logger.debug(
				"Leaving getOnLoanItemsFilterByItemCategories(). onLoanItems={}",
				onLoanItems);
		return onLoanItems;
	}

	@JsonIgnore
	public List<Item> getOnLoanItemsFilterBySmds(List<SMD> smdList) {
		logger.debug("Entering getOnLoanItemsFilterBySmds(smdList={})",
				new Object[] { smdList });
		Assert.notEmpty(smdList);

		List<Item> onLoanItems = itemRepo.findByPatronAndItemStatusAndSmd(this,
				getCirculatedItemStatusList(), smdList, null);

		logger.debug("Leaving getOnLoanItemsFilterBySmds(). onLoanItems={}",
				onLoanItems);
		return onLoanItems;
	}

	@JsonIgnore
	public List<Item> getOnLoanItemsFilterByLocations(
			List<Location> locationList) {
		logger.debug(
				"Entering getOnLoanItemsFilterByLocations(locationList={})",
				new Object[] { locationList });
		Assert.notEmpty(locationList);

		List<Item> onLoanItems = itemRepo.findByPatronAndItemStatusAndLocation(
				this, getCirculatedItemStatusList(), locationList, null);

		logger.debug(
				"Leaving getOnLoanItemsFilterByLocations(). onLoanItems={}",
				onLoanItems);
		return onLoanItems;
	}

	private List<ItemStatus> getCirculatedItemStatusList() {
		List<ItemStatus> itemStatusList = new ArrayList<ItemStatus>();
		itemStatusList.add(ItemStatus.CIRCULATED);
		return itemStatusList;
	}

	@JsonIgnore
	public List<CirculationTransaction> getCirculationHistory() {
		logger.debug("Entering getCirculationHistory()");
		List<CirculationTransaction> circulationTransactionList = circulationTransactionRepo
				.findByPatron(this);
		logger.debug(
				"Leaving getCirculationHistory(). circulationTransactionList={}",
				circulationTransactionList);
		return circulationTransactionList;
	}

	@JsonIgnore
	public List<CirculationTransaction> getCirculationTransactionsWithFlagCharged() {
		List<CirculationTransaction> circulationTransactionList = circulationTransactionRepo
				.findByPatronAndFlag(this, CirculationTransactionFlag.CHARGED);
		return circulationTransactionList;
	}

	public PatronCategory getPatronCategory() {
		return patronCategory;
	}

	public void setPatronCategory(PatronCategory patronCategory) {
		this.patronCategory = patronCategory;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	

	public Date getMembershipExpiryDate() {
		return membershipExpiryDate;
	}

	public void setMembershipExpiryDate(Date membershipExpiryDate) {
		this.membershipExpiryDate = membershipExpiryDate;
	}

	public Date getMembershipDate() {
		return membershipDate;
	}

	public void setMembershipDate(Date membershipDate) {
		this.membershipDate = membershipDate;
	}


}

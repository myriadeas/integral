package my.com.myriadeas.integral.data.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import my.com.myriadeas.integral.data.jpa.repositories.BranchRepository;
import my.com.myriadeas.integral.data.jpa.repositories.ItemCategoryRepository;
import my.com.myriadeas.integral.data.jpa.repositories.PatronCategoryRepository;
import my.com.myriadeas.integral.data.jpa.repositories.SMDRepository;
import my.com.myriadeas.integral.eligibility.EligibilityRuleCommand;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import com.beust.jcommander.JCommander;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class AbstractEligibilityDomain extends AbstractLookupDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Length(max = 200)
	private String criteria;

	private Integer weight;
	@Transient
	private transient ItemCategoryRepository itemCategoryRepository;

	@Transient
	private transient PatronCategoryRepository patronCategoryRepository;

	@Transient
	private transient SMDRepository smdRepository;

	@Transient
	private transient BranchRepository branchRepository;

	@Transient
	transient List<ItemCategory> itemCategories;

	@Transient
	transient List<PatronCategory> patronCategories;

	@Transient
	transient List<SMD> smds;

	@Transient
	transient List<Branch> branches;

	public AbstractEligibilityDomain() {
		super();
	}

	public AbstractEligibilityDomain(String code, String description) {
		super(code, description);
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	@JsonIgnore
	public EligibilityRuleCommand getEligibilityRuleCommand() {
		if (StringUtils.isBlank(this.getCriteria())) {
			return new EligibilityRuleCommand();
		}
		EligibilityRuleCommand ruleCommand = new EligibilityRuleCommand();
		String[] argv = (this.getCriteria() + " -el " + this.getCode())
				.split(" ");
		new JCommander(ruleCommand, argv);
		return ruleCommand;
	}

	@Autowired
	@JsonIgnore
	public void setItemCategoryRepository(
			ItemCategoryRepository itemCategoryRepository) {
		this.itemCategoryRepository = itemCategoryRepository;
	}

	@Autowired
	@JsonIgnore
	public void setPatronCategoryRepository(
			PatronCategoryRepository patronCategoryRepository) {
		this.patronCategoryRepository = patronCategoryRepository;
	}

	@Autowired
	@JsonIgnore
	public void setsMDRepository(SMDRepository sMDRepository) {
		this.smdRepository = sMDRepository;
	}

	@Autowired
	@JsonIgnore
	public void setBranchRepository(BranchRepository branchRepository) {
		this.branchRepository = branchRepository;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	protected abstract void loadCriteriaDomain();

	protected void populateItemCategories() {
		this.itemCategories = new ArrayList<ItemCategory>();
		if (getEligibilityRuleCommand().isAnyItemCategories()) {
			itemCategories.addAll(itemCategoryRepository.findAll());
			return;
		}
		for (String itemCategoryCode : getEligibilityRuleCommand()
				.getItemCategories()) {
			ItemCategory itemCategory = itemCategoryRepository
					.findByCode(itemCategoryCode);
			if (itemCategory != null) {
				itemCategories.add(itemCategory);
			}
		}
	}

	protected void populatePatronCategories() {
		this.patronCategories = new ArrayList<PatronCategory>();
		if (getEligibilityRuleCommand().isAnyPatronCategories()) {
			patronCategories.addAll(patronCategoryRepository.findAll());
			return;
		}
		for (String patronCategoryCode : getEligibilityRuleCommand()
				.getPatronCategories()) {
			PatronCategory patronCategory = patronCategoryRepository
					.findByCode(patronCategoryCode);
			if (patronCategory != null) {
				patronCategories.add(patronCategory);
			}

		}
	}

	protected void populateBranches() {
		this.branches = new ArrayList<Branch>();
		if (getEligibilityRuleCommand().isAnyBranches()) {
			branches.addAll(branchRepository.findAll());
			return;
		}
		for (String branchCode : getEligibilityRuleCommand().getBranches()) {
			Branch branch = branchRepository.findByCode(branchCode);
			if (branch != null) {
				branches.add(branch);
			}
		}
	}

	protected void populateSMDs() {
		this.smds = new ArrayList<SMD>();
		if (getEligibilityRuleCommand().isAnySmds()) {
			smds.addAll(smdRepository.findAll());
			return;
		}
		for (String smdCode : getEligibilityRuleCommand().getSmds()) {
			SMD smd = smdRepository.findByCode(smdCode);
			if (smd != null) {
				smds.add(smd);
			}

		}
	}

}

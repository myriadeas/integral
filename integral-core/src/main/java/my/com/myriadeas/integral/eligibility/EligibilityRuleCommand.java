package my.com.myriadeas.integral.eligibility;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.beust.jcommander.Parameter;

public class EligibilityRuleCommand {

	private static final String ANY = "ANY";

	@Parameter(names = "-pc", description = "patron category")
	private List<String> patronCategories = new ArrayList<String>();

	@Parameter(names = "-ic", description = "item category")
	private List<String> itemCategories = new ArrayList<String>();

	@Parameter(names = "-smd", description = "special material designation")
	private List<String> smds = new ArrayList<String>();

	@Parameter(names = "-brc", description = "location")
	private List<String> branches = new ArrayList<String>();

	@Parameter(names = "-el", description = "eligibility id")
	private String eligibility;

	public List<String> getPatronCategories() {
		return patronCategories;
	}

	public void setPatronCategories(List<String> patronCategories) {
		this.patronCategories = patronCategories;
	}

	public boolean isAnyPatronCategories() {
		return isAny(this.patronCategories);
	}

	public List<String> getItemCategories() {
		return itemCategories;
	}

	public void setItemCategories(List<String> itemCategories) {
		this.itemCategories = itemCategories;
	}

	public boolean isAnyItemCategories() {
		return isAny(this.itemCategories);
	}

	public List<String> getSmds() {
		return smds;
	}

	public void setSmds(List<String> smds) {
		this.smds = smds;
	}

	public boolean isAnySmds() {
		return isAny(this.smds);
	}

	public List<String> getBranches() {
		return branches;
	}

	public void setBranches(List<String> branches) {
		this.branches = branches;
	}

	public boolean isAnyBranches() {
		return isAny(this.branches);
	}

	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}
	
	private boolean isAny(List<String> lists) {
		return CollectionUtils.size(lists) == 1 && lists.get(0).equals(ANY);
	}

	@Override
	public String toString() {
		return "EligibilityRuleCommand [patronCategories=" + patronCategories
				+ ", itemCategories=" + itemCategories + ", smds=" + smds
				+ ", branches=" + branches + ", eligibility=" + eligibility
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((branches == null) ? 0 : branches.hashCode());
		result = prime * result
				+ ((eligibility == null) ? 0 : eligibility.hashCode());
		result = prime * result
				+ ((itemCategories == null) ? 0 : itemCategories.hashCode());
		result = prime
				* result
				+ ((patronCategories == null) ? 0 : patronCategories.hashCode());
		result = prime * result + ((smds == null) ? 0 : smds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EligibilityRuleCommand other = (EligibilityRuleCommand) obj;
		if (branches == null) {
			if (other.branches != null)
				return false;
		} else if (!branches.equals(other.branches))
			return false;
		if (eligibility == null) {
			if (other.eligibility != null)
				return false;
		} else if (!eligibility.equals(other.eligibility))
			return false;
		if (itemCategories == null) {
			if (other.itemCategories != null)
				return false;
		} else if (!itemCategories.equals(other.itemCategories))
			return false;
		if (patronCategories == null) {
			if (other.patronCategories != null)
				return false;
		} else if (!patronCategories.equals(other.patronCategories))
			return false;
		if (smds == null) {
			if (other.smds != null)
				return false;
		} else if (!smds.equals(other.smds))
			return false;
		return true;
	}

}

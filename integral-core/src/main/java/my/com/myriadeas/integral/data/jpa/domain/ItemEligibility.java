package my.com.myriadeas.integral.data.jpa.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
public class ItemEligibility extends AbstractEligibilityDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Min(0)
	@Max(999)
	private Integer maxLoanAllowed = new Integer(0);

	public ItemEligibility() {
		super();
	}

	public ItemEligibility(String code, String description) {
		super(code, description);
	}

	public Integer getMaxLoanAllowed() {
		return maxLoanAllowed;
	}

	public void setMaxLoanAllowed(Integer maxLoanAllowed) {
		if (maxLoanAllowed == null) {
			maxLoanAllowed = new Integer(0);
		}
		this.maxLoanAllowed = maxLoanAllowed;
	}

	public List<ItemCategory> getItemCategories() {
		return itemCategories;
	}

	@PostLoad
	void postLoad() {
		loadCriteriaDomain();
	}
	
	protected void loadCriteriaDomain() {
		populateItemCategories();
	}
}

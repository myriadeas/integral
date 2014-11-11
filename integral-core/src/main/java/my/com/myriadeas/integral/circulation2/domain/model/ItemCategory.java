package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
public class ItemCategory extends AbstractPersistable<Long> implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	
	public ItemCategory(String code) {
		this.code = code;
	}
	
}
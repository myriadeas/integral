package my.com.myriadeas.integral.circulation2.domain.model;

import javax.persistence.Column;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
public class PatronCategory extends AbstractPersistable<Long> implements Entity {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String code;

	private String description;

	public PatronCategory() {
	}

	public PatronCategory(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}

}
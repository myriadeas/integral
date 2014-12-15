package my.com.myriadeas.integral.circulation2.domain.model;

import javax.persistence.ManyToOne;

import my.com.myriadeas.integral.core.domain.model.Entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.AbstractPersistable;

@javax.persistence.Entity
@Configuration
public class Borrower extends AbstractPersistable<Long> implements Entity {

	private String username;

	private Long userId;

	@ManyToOne(optional = true)
	private PatronCategory patronCategory;

	public Borrower() {

	}

	public Borrower(String username, Long userId, PatronCategory patronCateogry) {
		this.userId = userId;
		this.username = username;
		this.patronCategory = patronCateogry;
	}

	public void assignPatronCategory(PatronCategory patronCategory) {
		this.patronCategory = patronCategory;
	}
}

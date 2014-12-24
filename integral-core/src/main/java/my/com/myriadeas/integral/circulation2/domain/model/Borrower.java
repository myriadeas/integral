package my.com.myriadeas.integral.circulation2.domain.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.ManyToOne;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;
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

	public Borrower(String username, Long userId, PatronCategory patronCategory) {
		this.userId = userId;
		this.username = username;
		this.patronCategory = patronCategory;
	}

	public void assignPatronCategory(PatronCategory patronCategory) {
		this.patronCategory = patronCategory;
	}

	public String getUsername() {
		return username;
	}

	public Long getUserId() {
		return userId;
	}

	public PatronCategory getPatronCategory() {
		return patronCategory;
	}

	public Map<String, DomainEvent> getNewBorrowerCreatedEvent() {
		Map<String, DomainEvent> events = new HashMap<String, DomainEvent>();
		NewBorrowerCreated event = new NewBorrowerCreated(this.username,
				this.userId);
		events.put("NewBorrowerCreated", event);

		return events;
	}

}

package my.com.myriadeas.integral.circulation2.domain.model;

import my.com.myriadeas.integral.core.domain.model.DomainEvent;

public class NewBorrowerCreated implements DomainEvent {

	private String username;

	private Long userId;

	public NewBorrowerCreated(String username, Long userId) {
		super();
		this.username = username;
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}

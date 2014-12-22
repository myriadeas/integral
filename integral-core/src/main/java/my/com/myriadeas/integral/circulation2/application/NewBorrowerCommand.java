package my.com.myriadeas.integral.circulation2.application;

import my.com.myriadeas.integral.core.application.Command;

public class NewBorrowerCommand implements Command {

	private String username;
	
	private Long userId;
	
	public NewBorrowerCommand(String username, Long userId) {
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

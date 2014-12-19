package my.com.myriadeas.integral.circulation2.application;

public class RegisterBorrowerCommand {
	
	private String username;
	
	private String patronCategoryCode;

	public RegisterBorrowerCommand(String username, String patronCategoryCode) {
		super();
		this.username = username;
		this.patronCategoryCode = patronCategoryCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPatronCategoryCode() {
		return patronCategoryCode;
	}

	public void setPatronCategoryCode(String patronCategoryCode) {
		this.patronCategoryCode = patronCategoryCode;
	}


}

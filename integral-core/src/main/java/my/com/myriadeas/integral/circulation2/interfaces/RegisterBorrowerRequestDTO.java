package my.com.myriadeas.integral.circulation2.interfaces;

public class RegisterBorrowerRequestDTO {
	
	public String username;
	
    public String patronCategoryCode;

	public RegisterBorrowerRequestDTO(String username, String patronCategoryCode) {
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

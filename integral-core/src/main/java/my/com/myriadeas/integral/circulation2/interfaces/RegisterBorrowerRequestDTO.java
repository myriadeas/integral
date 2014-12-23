package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.core.interfaces.DTO;

public class RegisterBorrowerRequestDTO implements DTO{
	
	public String username;
	
    public String patronCategoryCode;

    
	public RegisterBorrowerRequestDTO()  {
	}

	public RegisterBorrowerRequestDTO(String patronCategoryCode, String username) {
		super();
		this.patronCategoryCode = patronCategoryCode;
		this.username = username;	
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

package my.com.myriadeas.integral.circulation2.application;

public class RegisterBorrowerCommand {
	
	private Long userId;
	
	private String patronCategoryCode;

	public RegisterBorrowerCommand(Long userId, String patronCategoryCode) {
		super();
		this.userId = userId;
		this.patronCategoryCode = patronCategoryCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPatronCategoryCode() {
		return patronCategoryCode;
	}

	public void setPatronCategoryCode(String patronCategoryCode) {
		this.patronCategoryCode = patronCategoryCode;
	}
	

}

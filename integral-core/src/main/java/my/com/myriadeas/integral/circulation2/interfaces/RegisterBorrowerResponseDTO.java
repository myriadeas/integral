package my.com.myriadeas.integral.circulation2.interfaces;

public class RegisterBorrowerResponseDTO {

	private Long id;
	private boolean isSuccessful;
	private String message;

	public RegisterBorrowerResponseDTO(Long id, boolean isSuccessful,
			String message) {
		super();
		this.id = id;
		this.isSuccessful = isSuccessful;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

package my.com.myriadeas.integral.circulation2.interfaces;

public class NewHoldingResponseDTO {

	private Long holdingId;

	public NewHoldingResponseDTO(Long holdingId) {
		this.holdingId = holdingId;
	}

	public Long getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Long holdingId) {
		this.holdingId = holdingId;
	}

}

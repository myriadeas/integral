package my.com.myriadeas.integral.circulation2.interfaces;

import my.com.myriadeas.integral.core.interfaces.DTO;

public class ReleaseHoldingResponseDTO implements DTO {

	private Long holdingId;

	public ReleaseHoldingResponseDTO() {

	}

	public ReleaseHoldingResponseDTO(Long holdingId) {
		super();
		this.holdingId = holdingId;
	}

	public Long getHoldingId() {
		return holdingId;
	}

	public void setHoldingId(Long holdingId) {
		this.holdingId = holdingId;
	}

}

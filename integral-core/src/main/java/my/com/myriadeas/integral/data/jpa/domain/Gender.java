package my.com.myriadeas.integral.data.jpa.domain;

/**
 * Implementation based on http://en.wikipedia.org/wiki/ISO_5218
 * @author hutingung
 *
 */
public enum Gender {

	NOT_KNOWN(0), MALE(1), FEMALE(2), NOT_APPLICABLE(9);
	
	private Integer code;
	
	private Gender(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
}

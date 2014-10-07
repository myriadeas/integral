package my.com.myriadeas.integral.data.jpa.domain;



public enum ItemStatus {
	
	_RETURNED("_RETURNED"), 
	_POTENTIALLY_LATE("_POTENTIALLY_LATE"), 
	AVAILABLE("A"),
	BINDERY("B"),
	CIRCULATED("C"),
	FINAL_PROCESSING("F"),
	FOUND("FOUND"),
	HOLD("H"),
	LOST("L"),
	MISPLACED("MISPLACED"),
	MISSING("M"),
	RECALLED("E"),
	RENEW("RENEW"),
	SUSPENDED("S"),
	WEED("W")
	;
	
	private String code;
	
	private ItemStatus(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	@Override
	public String toString() {
		switch(this) {
			case _RETURNED: return "_RETURNED"; 
			case _POTENTIALLY_LATE: return "_POTENTIALLY_LATE"; 
			case AVAILABLE: return "Available";
			case BINDERY: return "Bindery";
			case CIRCULATED: return "Circulated";
			case FINAL_PROCESSING: return "Final Processing";
			case FOUND: return "Found";
			case HOLD: return "Hold";
			case LOST: return "Lost";
			case MISPLACED: return "Misplaced";
			case MISSING: return "Missing";
			case RECALLED: return "Recalled";
			case RENEW: return "Renew";
			case SUSPENDED: return "Suspended";
			case WEED: return "Weed";			
			default: throw new IllegalArgumentException();
    }
  }
}
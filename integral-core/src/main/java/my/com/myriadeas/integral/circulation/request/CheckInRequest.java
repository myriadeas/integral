package my.com.myriadeas.integral.circulation.request;

import java.util.Date;

public class CheckInRequest extends AbstractCirculationRequest {

	// 09N20100115 12332520100115 123325AP|AOPTAR|AB0000628042|AC|BIY|
	// 09<no block><transaction date><return date><current location><institution
	// id><item identifier><terminal password><item properties><cancel>
	private boolean noBlock;

	/*
	 * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS. All dates and times are
	 * expressed according to the ANSI standard X3.30 for date and X3.43 for
	 * time. The ZZZZ field should contain blanks (code $20) to represent local
	 * time. To represent universal time, a Z character (code $5A) should be put
	 * in the last (right hand) position of the ZZZZ field. To represent other
	 * time zones the appropriate character should be used; a Q character (code
	 * $51) should be put in the last (right hand) position of the ZZZZ field to
	 * represent Atlantic Standard Time. When possible local time is the
	 * preferred format.
	 */

	private Date returnDate;
	/*
	 * 18-char, fixed-length field: YYYYMMDDZZZZHHMMSS. The date that an item
	 * was returned to the library, which is not necessarily the same date that
	 * the item was checked back in.
	 */

	private String currentLocation;
	/*
	 * variable-length field; the current location of the item. 3M SelfCheck
	 * system software could set this field to the value of the 3M SelfCheck
	 * system terminal location on a Checkin message.
	 */

	private String institutionId;
	/*
	 * variable-length field; the library�s institution ID.
	 */

	private String itemIdentifier;
	/*
	 * variable-length field; the article bar-code. This information is needed
	 * by the SC to verify that the article that was checked in matches the
	 * article bar-code at the SC.
	 */

	private String terminalPassword;
	/*
	 * variable-length field. This is the password for the SC unit. If this
	 * feature is not used by the ACS in the library then the field should be
	 * zero length if it is required in the command, and can be omitted entirely
	 * if the field is optional in the command.
	 */

	private String itemProperties;
	/*
	 * variable-length field. This field may contain specific item information
	 * that can be used for identifying a item, such as item weight, size,
	 * security marker, etc. It may possibly used for security reasons. ACSs are
	 * encouraged to store this information in their database.
	 */

	private boolean cancel;
	/*
	 * 1-char field: Y or N. This field should be set to Y for a Checkout
	 * command being used to cancel a failed Checkin command, or for a Checkin
	 * command being used to cancel a failed Checkout command. It should be set
	 * to N for all other Checkout or Checkin commands.
	 */

	private String officerIdentifier;

	public CheckInRequest() {

	}

	public CheckInRequest(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public boolean isNoBlock() {
		return noBlock;
	}

	public void setNoBlock(boolean noBlock) {
		this.noBlock = noBlock;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}

	public String getItemIdentifier() {
		return itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		this.itemIdentifier = itemIdentifier;
	}

	public String getTerminalPassword() {
		return terminalPassword;
	}

	public void setTerminalPassword(String terminalPassword) {
		this.terminalPassword = terminalPassword;
	}

	public String getItemProperties() {
		return itemProperties;
	}

	public void setItemProperties(String itemProperties) {
		this.itemProperties = itemProperties;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	public String getOfficerIdentifier() {
		return officerIdentifier;
	}

	public void setOfficerIdentifier(String officerIdentifier) {
		this.officerIdentifier = officerIdentifier;
	}

}

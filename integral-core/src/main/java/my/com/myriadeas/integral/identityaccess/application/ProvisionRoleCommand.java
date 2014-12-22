package my.com.myriadeas.integral.identityaccess.application;

public class ProvisionRoleCommand {
	private String userName;
	private String description;
	private String roleName;
	private boolean supportsNesting;

	public ProvisionRoleCommand(String userName, String roleName, String description,
			boolean supportsNesting) {

		super();

		this.userName = userName;
		this.description = description;
		this.roleName = roleName;
		this.supportsNesting = supportsNesting;
	}

	public ProvisionRoleCommand() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isSupportsNesting() {
		return supportsNesting;
	}

	public void setSupportsNesting(boolean supportsNesting) {
		this.supportsNesting = supportsNesting;
	}
}

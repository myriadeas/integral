package my.com.myriadeas.integral.identityaccess.application;

public class AssignUserToRoleCommand {

	private String username;
	private String roleName;

	public AssignUserToRoleCommand(String username, String roleName) {
		super();

		this.username = username;
		this.roleName = roleName;
	}

	public AssignUserToRoleCommand() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

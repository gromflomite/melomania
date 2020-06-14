package model;

public class DbCredentials {

	private String dbUsername = "";
	private String dbPassword = "";
	private String dbLocation = "";

	protected DbCredentials() {
		this.dbUsername = "root";
		this.dbPassword = "secret";
		this.dbLocation = "jdbc:mysql://localhost/audio";
	}

	protected String getDbUsername() {
		return this.dbUsername;
	}

	protected String getDbPassword() {
		return this.dbPassword;
	}
	
	protected String getDbLocation() {
		return this.dbLocation;
	}
}

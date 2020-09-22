package model.connectionManagers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static DbCredentials dbCredentials = new DbCredentials();

	private static String dbUsername = dbCredentials.getDbUsername();
	private static String dbPassword = dbCredentials.getDbPassword();
	private static String dbLocation = dbCredentials.getDbLocation();

	private final static String CONNECTION_URL	= dbLocation;
	private final static String USER		= dbUsername;
	private final static String PASSWORD		= dbPassword;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Connection dbConnection = null;

		// Calling the correct driver for the DB we are using (added in Maven pom.xml)
		Class.forName("com.mysql.cj.jdbc.Driver");

		// Establish connection
		dbConnection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);

		return dbConnection;
	}

}
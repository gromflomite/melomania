package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private final static String CONNECTION_URL = "jdbc:mysql://localhost/audio";
	private final static String USER = "debian-sys-maint";
	private final static String PASSWORD = "o8lAkaNtX91xMUcV";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Connection dbConnection = null;

		// Checking if the connector is working properly (added in Maven pom.xml)
		Class.forName("com.mysql.jdbc.Driver"); 

		// Establish connection
		dbConnection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);

		return dbConnection;
	}
}

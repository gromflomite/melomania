package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private final static String CONNECTION_URL = "jdbc:sqlite:/melomania.db";	

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Connection dbConnection = null;

		// Checking if the connector is working properly (added in Maven pom.xml)
		Class.forName("org.sqlite.JDBC"); 

		// Establish connection
		dbConnection = DriverManager.getConnection(CONNECTION_URL);

		return dbConnection;
	}
}

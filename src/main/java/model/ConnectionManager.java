package model;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {	

<<<<<<< HEAD
	private static DbCredentials dbCredentials = new DbCredentials();
	
	private static String dbUsername = dbCredentials.getDbUsername();
	private static String dbPassword = dbCredentials.getDbPassword();
	private static String dbLocation = dbCredentials.getDbLocation();
	
	private final static String CONNECTION_URL 	= dbLocation;
	private final static String USER 			= dbUsername;
	private final static String PASSWORD		= dbPassword;	
=======
	public static Connection getConnection() throws Exception {	
>>>>>>> refs/remotes/origin/sqlite-test

		// Getting the path to DB file (DB saved in /resources)
		URL resource = ConnectionManager.class.getResource("melomania.db");
		String path = new File(resource.toURI()).getAbsolutePath();

		// Checking if the connector is working properly (added in Maven pom.xml)
		Class.forName("org.sqlite.JDBC");
		
		// Calling the DB with obtained path
		Connection conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));

		return conn;
	}
}

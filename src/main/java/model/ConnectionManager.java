package model;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {	

	public static Connection getConnection() throws Exception {	

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

package model.connectionManagers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolConnectionManager {

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException  {

	Connection dbConnection = null;

	// Calling the correct driver for the DB we are using (added in Maven pom.xml)
	Class.forName("com.mysql.jdbc.Driver");

	InitialContext initCtx = new InitialContext();
	
	Context envCtx 		= (Context) initCtx.lookup("java:comp/env");
	DataSource dataSource 	= (DataSource) envCtx.lookup("jdbc/melomania");

	// Establish connection
	dbConnection = dataSource.getConnection();

	return dbConnection;
    }

}

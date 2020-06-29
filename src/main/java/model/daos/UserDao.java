package model.daos;

import java.awt.Robot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ConnectionManager;
import model.pojos.Role;
import model.pojos.User;

public class UserDao {

	// SQL queries --------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet
	private final String SQL_GETALL		= " SELECT u.id, u.name, u.email, u.id_role AS 'id_role' , u.password, r.name AS 'name_role' FROM users AS u, roles AS r WHERE u.id_role = r.id ORDER BY u.id ASC LIMIT 20; ";
	private final String QUERY_GETBYID 	= " ";	

	// executeUpdate -> returns -> integer with the number of affected rows
	private final String QUERY_INSERT 	= " INSERT INTO users (name, email, role, password) VALUES (?,?,?,?); ";
	private final String QUERY_UPDATE	= " UPDATE users SET name = ?, email = ?, role = ?, password = ? WHERE id = ?; ";
	private final String QUERY_DELETE	= " DELETE FROM users WHERE id = ?; ";	
	// --------------------------------------------------------------------------------------------

	
	// Singleton pattern ----------------------------------------------------------------------	
	private UserDao() {
		super();
	}	
	
	public static UserDao INSTANCE = null;

	public static synchronized UserDao getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new UserDao();
		}

		return INSTANCE;
	}
	// End Singleton pattern ------------------------------------------------------------------
	
	
	// getAll() -------------------------------------------------------------------------------
	public ArrayList<User> getAll() {
	    
	    // Object to set all the values and return to the view
	    ArrayList<User> dbRegisters = new ArrayList<User>();

	    try (
		    Connection 		dbConnection = ConnectionManager.getConnection(); 
		    PreparedStatement	dbStatement  = dbConnection.prepareStatement(SQL_GETALL); 
		    ResultSet		dbResultSet  = dbStatement.executeQuery();) {

		while(dbResultSet.next()) {
		    
		    // User values
		    int    id_user 		= dbResultSet.getInt("id");
		    String name_user		= dbResultSet.getString("name");
		    String email_user		= dbResultSet.getString("email");
		    String password_user 	= dbResultSet.getString("password");		    
		    // Role values
		    int	   id_role		= dbResultSet.getInt("id_role");
		    String name_role		= dbResultSet.getString("name_role");
		    
		    // Setting the value to role object
		    Role role = new Role();
		    
		    role.setId_role(id_role);
		    role.setType_role(name_role);		    
		    
		    // Setting the values to user object
		    User user = new User();
		    
		    user.setId(id_user);
		    user.setName(name_user);
		    user.setEmail(email_user);
		    user.setPassword(password_user);
		    
		    user.setRole(role); // Setting to user object the role created above		    
		    
		    dbRegisters.add(user); // Adding user object to the ArrayList	    
		} 
		
	    } catch (Exception e) {
		// TODO: handle exception
	    }

	    return dbRegisters; // Returning the ArrayList with the user object values
	}

	// End getAll()
	// ---------------------------------------------------------------------------
	
	

}

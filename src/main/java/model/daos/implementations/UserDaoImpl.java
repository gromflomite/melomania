package model.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.connectionManagers.ConnectionManager;
import model.daos.UserDao;
import model.pojos.Role;
import model.pojos.User;

public class UserDaoImpl implements UserDao {

    // SQL queries
    // --------------------------------------------------------------------------------
    // executeQuery -> returns -> ResultSet
    private final String QUERY_GETALL = " SELECT u.id, u.name, u.email, u.id_role AS 'id_role' , u.password, r.name AS 'name_role' FROM users AS u, roles AS r WHERE u.id_role = r.id ORDER BY u.id ASC LIMIT 20; ";
    private final String QUERY_GETBYID = " SELECT u.id, u.name, u.email, u.id_role AS 'id_role' , u.password, r.name AS 'name_role' FROM users AS u, roles AS r WHERE u.id_role = r.id AND u.id = ?; ";
    private final String QUERY_CHECKLOGIN = " SELECT u.id, u.name, u.email, u.password, u.id_role, r.name AS 'name_role' FROM users AS u, roles AS r WHERE u.name = ? AND u.password = ? AND u.id_role = r.id;  ";

    // executeUpdate -> returns -> integer with the number of affected rows
    private final String QUERY_INSERT = " INSERT INTO users (name, email, id_role, password) VALUES (?,?,?,?); ";
    private final String QUERY_UPDATE = " UPDATE users SET name = ?, email = ?, password = ?, id_role = ? WHERE id = ?; ";
    private final String QUERY_SEARCH_BY_NAME = " SELECT users.name FROM users WHERE users.name = ? ";
    // private final String QUERY_DELETE = " DELETE FROM users WHERE id = ?; ";
    // --------------------------------------------------------------------------------------------

    // Singleton pattern
    // ----------------------------------------------------------------------
    private UserDaoImpl() {
	super();
    }

    public static UserDaoImpl INSTANCE = null;

    public static synchronized UserDaoImpl getInstance() {

	if (INSTANCE == null) {
	    INSTANCE = new UserDaoImpl();
	}

	return INSTANCE;
    }
    // End Singleton pattern
    // ------------------------------------------------------------------

    // getAll()
    // -------------------------------------------------------------------------------
    public ArrayList<User> getAll() {

	// Object to set all the values and return to the view
	ArrayList<User> dbRegisters = new ArrayList<User>();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement dbStatement = dbConnection.prepareStatement(QUERY_GETALL); ResultSet dbResultSet = dbStatement.executeQuery();) {

	    while (dbResultSet.next()) {

		// User values
		int id_user = dbResultSet.getInt("id");
		String name_user = dbResultSet.getString("name");
		String email_user = dbResultSet.getString("email");
		String password_user = dbResultSet.getString("password");
		// Role values
		int id_role = dbResultSet.getInt("id_role");
		String name_role = dbResultSet.getString("name_role");

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

    // getById()
    // ---------------------------------------------------------------------------
    public User getById(int idUser) {

	User dbRegister = new User();
	Role role = new Role();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement dbStatement = dbConnection.prepareStatement(QUERY_GETBYID);) {

	    // Replacing ? in the SQL query
	    dbStatement.setInt(1, idUser);

	    ResultSet dbResultSet = dbStatement.executeQuery();

	    if (dbResultSet.next()) {

		// User values
		int id_user = dbResultSet.getInt("id");
		String name_user = dbResultSet.getString("name");
		String email_user = dbResultSet.getString("email");
		String password_user = dbResultSet.getString("password");
		// Role values
		int id_role = dbResultSet.getInt("id_role");
		String name_role = dbResultSet.getString("name_role");

		// Setting user values
		dbRegister.setId(id_user);
		dbRegister.setName(name_user);
		dbRegister.setEmail(email_user);
		dbRegister.setPassword(password_user);
		// Setting role values
		role.setId_role(id_role);
		role.setType_role(name_role);

		dbRegister.setRole(role); // Setting to user object the role created above

	    } else {
		throw new Exception(); // TODO
	    }

	} catch (Exception e) {
	    // TODO: handle exception
	}

	return dbRegister;
    }

    // End getById()
    // ---------------------------------------------------------------------------

    // insert()
    // ---------------------------------------------------------------------------
    public User insert(User user) throws Exception {

	try (Connection dbConnection = ConnectionManager.getConnection();
		/**
		 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB has assigned to the new created entry
		 */
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, user.getName());
	    preparedStatement.setString(2, user.getEmail());
	    preparedStatement.setInt(3, user.getRole().getId_role());
	    preparedStatement.setString(4, user.getPassword());

	    // Execute the query and save the number of affected rows
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows == 1) {

		// Knowing and getting the id number that DB has assigned to the new created
		// register
		try (ResultSet rsNewAssignedId = preparedStatement.getGeneratedKeys()) {

		    // Check and save the results from the ResultSet from RETURN_GENERATED_KEYS
		    if (rsNewAssignedId.next()) {

			int id = rsNewAssignedId.getInt(1); // Column position (one-based index in SQL, NOT zero-based) to retrive the id
							    // number

			user.setId(id);

		    }
		}

	    } else {

		throw new Exception("The user " + user.getName() + " has not been created");

	    }

	}

	return user;
    }

    // End insert()
    // ---------------------------------------------------------------------------

    // checkLogin()
    // ---------------------------------------------------------------------------
    public User checkLogin(String userName, String userPassword) {

	User userLogin = new User();
	Role userRole = new Role();

	userLogin = null;
	userRole = null;

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_CHECKLOGIN);) {

	    preparedStatement.setString(1, userName);
	    preparedStatement.setString(2, userPassword);

	    try (ResultSet dbResultSet = preparedStatement.executeQuery()) {

		if (dbResultSet.next()) {

		    // Setting values ------------------------------------------

		    // User values
		    userLogin = new User();

		    userLogin.setId(dbResultSet.getInt("id"));
		    userLogin.setName(dbResultSet.getString("name"));
		    userLogin.setEmail(dbResultSet.getString("email"));
		    userLogin.setPassword(dbResultSet.getString("password"));

		    // Role values
		    userRole = new Role();

		    userRole.setId_role(dbResultSet.getInt("id_role"));
		    userRole.setType_role(dbResultSet.getString("name_role"));

		    userLogin.setRole(userRole);

		}

	    }

	} catch (Exception e) {

	    // TODO: handle exception

	}

	return userLogin;
    }

    // End checkLogin()
    // ---------------------------------------------------------------------------

    // update()
    // ---------------------------------------------------------------------------
    public User update(User userUpdate) throws Exception {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_UPDATE);) {

	    // Get the values sended by the controller
	    int userId = userUpdate.getId();
	    String userName = userUpdate.getName();
	    String userMail = userUpdate.getEmail();
	    String userPassword = userUpdate.getPassword();
	    int userIdRole = userUpdate.getRole().getId_role();

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, userName);
	    preparedStatement.setString(2, userMail);
	    preparedStatement.setString(3, userPassword);
	    preparedStatement.setInt(4, userIdRole);
	    preparedStatement.setInt(5, userId);

	    // Execute the SQL query
	    int updatedRows = preparedStatement.executeUpdate();

	    if (updatedRows != 1) {

		throw new Exception("We have a problem updating your details");

	    }

	}

	return userUpdate;

    }
    // End update()
    // ---------------------------------------------------------------------------

    // searchByName()
    // ---------------------------------------------------------------------------
    /**
     * Get the user searching by name
     * 
     * We are using this method via API controller (UserAPIController)
     * 
     */
    @Override
    public boolean searchByName(String userName) {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_SEARCH_BY_NAME);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, userName);

	    // Execute SQL query
	    ResultSet dbResultSet = preparedStatement.executeQuery();

	    // Check if SQL returns results
	    if (dbResultSet.next()) {

		return true;
	    }

	} catch (Exception e) {
	    // TODO: handle exception
	}

	return false;
    }
    // End searchByName()
    // ---------------------------------------------------------------------------

}

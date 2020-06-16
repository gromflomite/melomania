package model.daos;

public class UserDao {

	// SQL queries --------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet
	private final String SQL_GETALL		= " SELECT u.id, u.name, u.email, u.role, u.password, r.role_type AS 'role_type' FROM users AS u INNER JOIN roles AS r ON u.id = r.id ORDER BY u.id ASC; ";
	private final String QUERY_GETBYID 	= " SELECT u.id, u.name, u.email, u.role, u.password, r.role_type AS 'role_type' FROM users AS u INNER JOIN roles AS r ON u.id = r.id WHERE u.id = ?; ";	

	// executeUpdate -> returns -> integer with the number of affected rows
	private final String QUERY_INSERT 	= " INSERT INTO users (name, email, role, password) VALUES (?,?,?,?); ";
	private final String QUERY_UPDATE	= " UPDATE users SET name = ?, email = ?, role = ?, password = ? WHERE id = ?; ";
	private final String QUERY_DELETE	= " DELETE FROM users WHERE id = ?; ";	
	// --------------------------------------------------------------------------------------------

	
	// Singleton pattern --------------------------------------------------------------------------	
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
	// End Singleton pattern ----------------------------------------------------------------------
	
	
	
	

}

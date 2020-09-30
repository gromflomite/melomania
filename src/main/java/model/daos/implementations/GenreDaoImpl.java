package model.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.connectionManagers.ConnectionManager;
import model.daos.GenreDao;
import model.pojos.Genre;

public class GenreDaoImpl implements GenreDao {

    // SQL queries
    // --------------------------------------------------------------------------------------------
    // executeQuery -> returns -> ResulSet
    private final static String QUERY_GETALL = " SELECT id, name FROM genres ORDER BY id ASC LIMIT 100; ";
    // --------------------------------------------------------------------------------------------

    // Singleton pattern
    // --------------------------------------------------------------------------------------------
    private GenreDaoImpl() {
	super();
    }

    public static GenreDaoImpl INSTANCE = null;

    public static synchronized GenreDaoImpl getInstance() {

	if (INSTANCE == null) {
	    INSTANCE = new GenreDaoImpl();
	}

	return INSTANCE;
    }
    // End Singleton pattern
    // --------------------------------------------------------------------------------------------

    // getAll()
    // --------------------------------------------------------------------------------------------
    public ArrayList<Genre> getAll() {

	// ArrayList to push the POJO "genre" items recovered form DB
	ArrayList<Genre> dbRegisters = new ArrayList<Genre>();

	// try with resources (autoclosable)
	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETALL); 
		ResultSet resultSet = preparedStatement.executeQuery();

	) {
	    while (resultSet.next()) {

		// Set "album" POJO to ArrayList "dbRegisters"
		dbRegisters.add(mapper(resultSet));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return dbRegisters;
    }

    // End getAll()
    // --------------------------------------------------------------------------------------------   
    
    
    
    // mapper()
    // --------------------------------------------------------------------------------------------
    private static Genre mapper(ResultSet resultSet) throws Exception {	

	// Getting the values from the resultSet (values from the DB)	
	int	genreId	   = resultSet.getInt("id");	
	String	genreName  = resultSet.getString("name");	
	
	Genre genre = new Genre();
	
	genre.setId(genreId);
	genre.setGenre(genreName);	
	
	return genre;
    }
    // End mapper()
    // --------------------------------------------------------------------------------------------
    
}
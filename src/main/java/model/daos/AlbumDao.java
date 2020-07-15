package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ConnectionManager;
import model.pojos.Album;
import model.pojos.Genre;

public class AlbumDao {

    // SQL queries
    // --------------------------------------------------------------------------------------------
    // executeQuery -> returns -> ResulSet
    private final String QUERY_GETALLBYGENRE = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name FROM albums AS a, genres AS g WHERE a.id_genre = g.id AND g.id = ? ORDER BY a.id ASC LIMIT 100; ";
    private final String QUERY_GETALL	= " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name FROM albums AS a, genres AS g WHERE a.id_genre = g.id ORDER BY a.id ASC LIMIT 100; ";
    // QUERY_GETLAST modified to show just the approved albums (where approved_date is not null)
    private final String QUERY_GETLAST	= " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name FROM albums AS a, genres AS g WHERE a.id_genre = g.id AND approved_date IS NOT NULL ORDER BY a.id DESC LIMIT ?; ";
    private final String QUERY_GETBYID	= " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name FROM albums AS a, genres AS g WHERE a.id_genre = g.id WHERE a.id = ? ORDER BY a.id ASC LIMIT 100; ";
        
    // executeUpdate -> returns -> integer with the number of affected rows
    private final String QUERY_INSERT = " INSERT INTO albums (title, artist, year, comments, cover) VALUES (?,?,?,?,?); ";
    private final String QUERY_UPDATE = " UPDATE albums SET title = ?, artist = ?, year = ?, comments = ?, cover = ? WHERE id = ?; ";
    private final String QUERY_DELETE = " DELETE FROM albums WHERE id = ? ; ";
    // --------------------------------------------------------------------------------------------

    // Singleton pattern
    // --------------------------------------------------------------------------------------------
    private AlbumDao() {
	super();
    }

    public static AlbumDao INSTANCE = null;

    public static synchronized AlbumDao getInstance() {

	if (INSTANCE == null) {
	    INSTANCE = new AlbumDao();
	}

	return INSTANCE;
    }
    // End Singleton pattern
    // --------------------------------------------------------------------------------------------

    
    // validateAlbum()
    // --------------------------------------------------------------------------------------------

    public void validateAlbum(int id) {
	
	// TODO
	// Example of query to validate album
	// UPDATE album SET approved_date = NOW() WHERE id = ?;

    }
    // End validateAlbum()
    // -------------------------------------------------------------------------------------------- 
    
    
    
    
    // getByGenre()
    // --------------------------------------------------------------------------------------------
    
    public ArrayList<Album> getByGenre(int genreId) {

	// ArrayList to push the POJO album and genres items recovered form DB
	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	// try with resources (autoclosable)
	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETALLBYGENRE);

	) {
	    preparedStatement.setInt(1, genreId);

	    try (ResultSet resulSet = preparedStatement.executeQuery();) {
			
		while (resulSet.next()) {

		    // Set "album" POJO to ArrayList "dbRegisters"
		    dbRegisters.add(mapper(resulSet));
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return dbRegisters;	
    }
    // End getByGenre()
    // --------------------------------------------------------------------------------------------
    
    
    /**
     * 
     * Method to retrieve from the DB the numAlbums (the number of new albums to show in index.jsp).
     * 
     * @param numAlbums int number of the last albums we want to show.
     * @return ArrayList whith the albums retrieved from the DB.
     * 
     */
    public ArrayList<Album> getLast(int numAlbums) {

	// ArrayList to push the POJO "album" items recovered form DB
	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	// try with resources (autoclosable)
	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETLAST);

	) {
	    preparedStatement.setInt(1, numAlbums);

	    try (ResultSet resulSet = preparedStatement.executeQuery();) {
		while (resulSet.next()) {

		    // Set "album" POJO to ArrayList "dbRegisters"
		    dbRegisters.add(mapper(resulSet));
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return dbRegisters;
    }
    // getLast()
    // --------------------------------------------------------------------------------------------
    

    // getAll()
    // --------------------------------------------------------------------------------------------
    public ArrayList<Album> getAll() {

	// ArrayList to push the POJO "album" items recovered form DB
	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	// try with resources (autoclosable)
	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETALL); 
		ResultSet resulSet = preparedStatement.executeQuery();

	) {
	    while (resulSet.next()) {

		// Set "album" POJO to ArrayList "dbRegisters"
		dbRegisters.add(mapper(resulSet));
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	return dbRegisters;
    }
    // End getAll()
    // --------------------------------------------------------------------------------------------
    

    
    // getById()
    // --------------------------------------------------------------------------------------------
    public Album getById(int albumId) throws Exception {

	// Create POJO and set the recovered values
	Album album = new Album();

	try (
		Connection dbConnection = ConnectionManager.getConnection(); 
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETBYID);) {

	    // Replacing ? in the SQL query
	    preparedStatement.setInt(1, albumId);

	    // Executing the query against the DB and getting the ResultSet with the values
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {

		album = mapper(resultSet);

	    } else {

		throw new Exception("The album " + album.getTitle() + " does not exists in your collection");
	    }
	}

	return album;
    }
    // End getById()
    // --------------------------------------------------------------------------------------------
    

    
    // insert()
    // --------------------------------------------------------------------------------------------
    public Album insert(Album newAlbum) throws Exception {

	try (
		Connection dbConnection = ConnectionManager.getConnection();
		/**
		 * 
		 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB has assigned to the new created entry
		 * 
		 */
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, newAlbum.getTitle());
	    preparedStatement.setString(2, newAlbum.getArtist());
	    preparedStatement.setInt(3, newAlbum.getYear());
	    preparedStatement.setString(4, newAlbum.getComments());
	    preparedStatement.setString(5, newAlbum.getCover());

	    // Executing the update against the DB and saving the number of affected rows
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows == 1) {

		// TODO Manage feedback if necessary

	    } else {

		throw new Exception("The album " + newAlbum.getTitle() + " has not been saved");
	    }
	}

	return newAlbum;
    }
    // End insert()
    // --------------------------------------------------------------------------------------------
    

    // update()
    // --------------------------------------------------------------------------------------------
    public Album update(Album updateAlbum) throws Exception {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_UPDATE);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, updateAlbum.getTitle());
	    preparedStatement.setString(2, updateAlbum.getArtist());
	    preparedStatement.setInt(3, updateAlbum.getYear());
	    preparedStatement.setString(4, updateAlbum.getComments());
	    preparedStatement.setString(5, updateAlbum.getCover());
	    preparedStatement.setInt(6, updateAlbum.getId());

	    // Exectute update. executeUpdate returns the numbers of affected rows
	    if (preparedStatement.executeUpdate() != 1) {

		throw new Exception("The album " + updateAlbum.getTitle() + " can not be updated");
	    }

	} catch (Exception e) {

	    e.printStackTrace();
	}

	return updateAlbum;
    }
    // End update()
    // --------------------------------------------------------------------------------------------
    

    
    // delete()
    // --------------------------------------------------------------------------------------------
    public Album delete(int deleteAlbumId) throws Exception {

	// Get the album before try deleting it
	Album albumToDelete = getById(deleteAlbumId);

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_DELETE);) {

	    // Changing the ? in the SQL query
	    preparedStatement.setInt(1, deleteAlbumId);

	    // Getting the number of affected rows after execute the query
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows != 1) {
		throw new Exception("The album " + albumToDelete.getTitle() + " cannot be deleted.");
	    }
	}

	return albumToDelete;
    }
    // End delete()
    // --------------------------------------------------------------------------------------------
    

    // mapper()
    // --------------------------------------------------------------------------------------------
    private Album mapper(ResultSet resultSet) throws Exception {

	// Getting the values from the resultSet (values from the DB)
	
	int	albumId		= resultSet.getInt("album_id");
	String	title 		= resultSet.getString("album_title");
	String	artist 		= resultSet.getString("album_artist");
	int 	year 		= resultSet.getInt("album_year");
	String 	comments 	= resultSet.getString("album_comments");
	String 	cover 		= resultSet.getString("album_cover");
	
	// Genres
	int 	genreId 	= resultSet.getInt("genre_id");
	String 	genreName 	= resultSet.getString("genre_name");

	// Create POJOS and set the recovered values ----------------

	// Set the recovered values to Album object
	Album album = new Album();
	
	album.setId(albumId);
	album.setTitle(title);
	album.setArtist(artist);
	album.setYear(year);
	album.setComments(comments);
	album.setCover(cover);

	// Setting the genre object attributes
	Genre genre = new Genre();
	
	genre.setId(genreId);
	genre.setGenre(genreName);

	// Setting the Genre object to Album
	album.setGenre(genre);

	return album;
    }
    // End mapper()
    // --------------------------------------------------------------------------------------------

} // Class end
  // ----------------------------------------------------------------------------------------------

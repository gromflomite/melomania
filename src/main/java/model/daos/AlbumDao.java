package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ConnectionManager;
import model.pojos.Album;

public class AlbumDao {

	// SQL queries
	// --------------------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet
	private final String QUERY_GETALL = " SELECT id, title, artist, year, comments, cover FROM albums ORDER BY id ASC; ";
	private final String QUERY_GETBYID = " SELECT id, title, artist, year, comments, cover FROM albums WHERE id = ? ; ";

	// executeUpdate -> returns -> integer with the number of affected rows
	private final String QUERY_INSERT = " INSERT INTO albums (title, artist, year, comments, cover) VALUES (?,?,?,?,?); ";
	private final String QUERY_UPDATE = " UPDATE albums SET title = ?, artist = ?, year = ?, comments = ?, cover = ? WHERE id = ?; ";	
	// --------------------------------------------------------------------------------------------

	
	// Singleton pattern --------------------------------------------------------------------------
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
	// End Singleton pattern ----------------------------------------------------------------------	
	

	// getAll -------------------------------------------------------------------------------------
	public ArrayList<Album> getAll() {

		// ArrayList to push the POJO "album" items recovered form DB
		ArrayList<Album> dbRegisters = new ArrayList<Album>();

		// try with resources (autoclosable)
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GETALL);
				ResultSet resulSet = preparedStatement.executeQuery();

		) {
			while (resulSet.next()) {

				// Getting the values from the resultSet (values from the DB)
				int id 			= resulSet.getInt("id");
				String title 	= resulSet.getString("title");
				String artist 	= resulSet.getString("artist");
				int year		= resulSet.getInt("year");
				String comments = resulSet.getString("comments");
				String cover 	= resulSet.getString("cover");

				// Create POJO and set the recovered values
				Album album = new Album();

				// Set the recovered values to POJO
				album.setId(id);
				album.setTitle(title);
				album.setArtist(artist);
				album.setYear(year);
				album.setComments(comments);
				album.setCover(cover);

				// Set "album" POJO to ArrayList "dbRegisters"
				dbRegisters.add(album);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dbRegisters;
	}
	// End getAll ---------------------------------------------------------------------------------
	
	
	// getById method ------------------------------------------------------------------------------
	public Album getById(int albumId) throws Exception {		
		
		// Create POJO and set the recovered values
		Album album = new Album();
		
		try (
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GETBYID);) {

			// Replacing ? in the SQL query
			preparedStatement.setInt(1, albumId);

			// Executing the query against the DB and getting the ResultSet with the values
			ResultSet resulSet = preparedStatement.executeQuery();
			
			if (resulSet.next()) {
				
				// Getting the values from the resultSet (values from the DB)
				int id				= resulSet.getInt("id");
				String title		= resulSet.getString("title");
				String artist 		= resulSet.getString("artist");
				int year 			= resulSet.getInt("year");
				String comments 	= resulSet.getString("comments");
				String cover 		= resulSet.getString("cover");				
				
				// Set the recovered values to POJO
				album.setId(id);
				album.setTitle(title);
				album.setArtist(artist);
				album.setYear(year);
				album.setComments(comments);
				album.setCover(cover);			
				
			} else {
				
				throw new Exception("The inserted ID (" + albumId + ") does not exists in the DB");
			}
		}

		return album;
	}	
	// End getById method -------------------------------------------------------------------------
	

	// insert method ------------------------------------------------------------------------------
	public Album insert(Album newAlbum) throws Exception {

		try (
				Connection dbConnection = ConnectionManager.getConnection();
				/**
				 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB has assigned to the new created entry
				 */
				PreparedStatement pst = dbConnection.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);)
		{

			// Replace ? in the SQL query
			pst.setString	(	1, 	newAlbum.getTitle());
			pst.setString	(	2, 	newAlbum.getArtist());
			pst.setInt		(	3, 	newAlbum.getYear());
			pst.setString	(	4, 	newAlbum.getComments());
			pst.setString	(	5,	newAlbum.getCover());
			
			// Executing the update against the DB and saving the number of affected rows
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 1) {
				
				// TODO ??
				
			} else {
				
				throw new Exception("The album " + newAlbum.getTitle() + " has not been saved");
			}
		}
		
		return newAlbum;
	}
	// End insert method --------------------------------------------------------------------------	
	
	
	// update method ------------------------------------------------------------------------------
	public Album update(Album updateAlbum) throws Exception {

		try (
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);) {			

			// Replace ? in the SQL query
			preparedStatement.setString(	1, updateAlbum.getTitle());
			preparedStatement.setString(	2, updateAlbum.getArtist());
			preparedStatement.setInt(		3, updateAlbum.getYear());
			preparedStatement.setString(	4, updateAlbum.getComments());
			preparedStatement.setString(	5, updateAlbum.getCover());
			preparedStatement.setInt(		6, updateAlbum.getId());

			System.out.println(preparedStatement);
			
			// Exectute update. executeUpdate returns the numbers of affected rows
			if (preparedStatement.executeUpdate() != 1) {

				throw new Exception("The album " + updateAlbum.getTitle() + " can not be updated");
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

		return updateAlbum;
	}
	// End update method --------------------------------------------------------------------------	

} // Class end ------------------------------------------------------------------------------------

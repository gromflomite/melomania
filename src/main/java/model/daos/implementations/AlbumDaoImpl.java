package model.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.connectionManagers.ConnectionManager;
import model.daos.AlbumDao;
import model.pojos.Album;
import model.pojos.Genre;
import model.pojos.User;
import model.pojos.UserAlbums;

public class AlbumDaoImpl implements AlbumDao {

    // private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    // SQL queries
    // --------------------------------------------------------------------------------------------
    // executeQuery -> returns -> ResulSet

    // QUERY_GETALLBYGENRE -> Returns all albums within a genre
    private final String QUERY_GETALLBYGENRE = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, id_user AS id_user FROM albums AS a, genres AS g WHERE a.id_genre = g.id AND g.id = ? ORDER BY a.id ASC LIMIT 100; ";

    private final String QUERY_GETALL = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, a.creation_date, a.approved_date, g.id AS genre_id, g.name AS genre_name, id_user AS id_user, u.name AS name_user FROM albums AS a, genres AS g, users AS u WHERE a.id_genre = g.id AND a.id_user = u.id ORDER BY a.id ASC LIMIT 100; ";

    // QUERY_GETLAST -> Returns all the approved albums from ALL users
    private final String QUERY_GETLAST = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, id_user AS id_user FROM albums AS a, genres AS g WHERE a.id_genre = g.id AND approved_date IS NOT NULL ORDER BY a.id DESC LIMIT ?; ";

    private final String QUERY_GETBYID = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, id_user AS id_user FROM albums AS a, genres AS g WHERE a.id_genre = g.id AND a.id = ? ORDER BY a.id ASC LIMIT 100; ";

    // QUERY_GETALLBYUSER_APPROVEDALBUMS -> Returns all user validated albums
    private final String QUERY_GETALLBYUSER_APPROVED = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, id_user AS id_user FROM albums AS a, genres AS g, users AS u WHERE a.id_genre = g.id AND a.id_user = u.id AND a.id_user = ? AND approved_date IS NOT NULL ORDER BY a.id DESC LIMIT 100; ";

    // QUERY_GETALLBYUSER_PENDINGALBUMS -> Returns all user pending albums
    private final String QUERY_GETALLBYUSER_PENDING = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, id_user AS id_user FROM albums AS a, genres AS g, users AS u WHERE a.id_genre = g.id AND a.id_user = u.id AND a.id_user = ? AND approved_date IS NULL ORDER BY a.id DESC LIMIT 100; ";

    // QUERY_GETUSERALBUMS_VIEW -> Query against a DB view. Returns JUST THE NUMBER of approved and pending user albums
    private final String QUERY_GETNUMBERUSERALBUMS_VIEW = " SELECT vua.id_user AS userId, vua.approved_albums AS userApprovedAlbums , vua.pending_albums AS userPendingAlbums FROM view_user_albums vua WHERE vua.id_user = ?; ";

    // QUERY_GETBYID_CHECKING_USER -> Returns an album by ID checking if it belongs to the user
    private final String QUERY_GETBYID_CHECKING_USER = " SELECT a.id AS album_id, a.title AS album_title, a.artist AS album_artist, a.year AS album_year, a.comments AS album_comments, a.cover AS album_cover, g.id AS genre_id, g.name AS genre_name, a.id_user AS id_user FROM albums AS a, genres AS g, users AS u WHERE a.id_genre = g.id AND a.id_user = u.id AND a.id_user = ? AND a.id = ? ORDER BY a.id DESC LIMIT 100; ";

    // -----------------------------------

    // executeUpdate -> returns -> integer with the number of affected rows

    private final String QUERY_INSERT = " INSERT INTO albums (title, artist, year, comments, cover, id_genre, id_user) VALUES (?,?,?,?,?,?,?); ";

    private final String QUERY_UPDATE = " UPDATE albums SET title = ?, artist = ?, year = ?, comments = ?, cover = ? WHERE id = ?; ";

    // QUERY_UPDATE_CHECKING_USER -> Update an album - IMPORTANT: Because every updated album must be approved by an admin,
    // we are setting the "approved_date" to NULL in order to set the album as pending
    private final String QUERY_UPDATE_CHECKING_USER = " UPDATE albums SET title = ?, artist = ?, year = ?, comments = ?, cover = ?, id_genre = ?, approved_date = NULL WHERE id = ?; ";

    private final String QUERY_DELETE = " DELETE FROM albums WHERE id = ? ; ";

    // QUERY_DELETE_CHECKING_USER -> Delete album checking if it belongs to the user
    private final String QUERY_DELETE_CHECKING_USER = " DELETE FROM albums WHERE id = ? AND id_user = ?; ";

    // QUERY_APPROVE -> Approve an album
    private final String QUERY_APPROVE = " UPDATE albums SET approved_date = current_timestamp() WHERE id = ?; ";

    // QUERY_APPROVE -> Disapprove an album
    private final String QUERY_DISAPPROVE = " UPDATE albums SET approved_date = NULL WHERE id = ?; ";

    // End SQL queries
    // --------------------------------------------------------------------------------------------

    // Singleton pattern
    // --------------------------------------------------------------------------------------------
    private AlbumDaoImpl() {
	super();
    }

    public static AlbumDaoImpl INSTANCE = null;

    public static synchronized AlbumDaoImpl getInstance() {

	if (INSTANCE == null) {
	    INSTANCE = new AlbumDaoImpl();
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

	    try (ResultSet resultSet = preparedStatement.executeQuery();) {

		while (resultSet.next()) {

		    dbRegisters.add(albumMapper(resultSet)); // Set "album" POJO to ArrayList "dbRegisters"
		}
	    }

	} catch (Exception e) {

	    // TODO Log

	}

	return dbRegisters;
    }
    // End getByGenre()
    // --------------------------------------------------------------------------------------------

    /**
     * 
     * Method to retrieve the numAlbums (the number of new albums to show in index.jsp).
     * 
     * @param numAlbums int number of the last albums we want to show.
     * @return ArrayList whith the albums retrieved from the DB.
     * 
     */
    public ArrayList<Album> getLast(int numAlbums) {

	// ArrayList to push the POJO "album" items recovered form DB
	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	// try with resources (autoclosable)
	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETLAST);

	) {
	    preparedStatement.setInt(1, numAlbums);

	    try (ResultSet resulSet = preparedStatement.executeQuery();) {
		while (resulSet.next()) {

		    // Set "album" POJO to ArrayList "dbRegisters"
		    dbRegisters.add(albumMapper(resulSet));
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
	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETALL); ResultSet resultSet = preparedStatement.executeQuery();

	) {
	    while (resultSet.next()) {

		// Album
		int idAlbum = resultSet.getInt("album_id");
		String title = resultSet.getString("album_title");
		String artist = resultSet.getString("album_artist");
		int year = resultSet.getInt("album_year");
		String comments = resultSet.getString("album_comments");
		String cover = resultSet.getString("album_cover");
		String dateApproved = resultSet.getString("approved_date");

		// Genres
		int idGenre = resultSet.getInt("genre_id");
		String nameGenre = resultSet.getString("genre_name");

		// User
		int idUser = resultSet.getInt("id_user");
		String nameUser = resultSet.getString("name_user");

		// Set Album object values -------------------------
		Album album = new Album();

		album.setId(idAlbum);
		album.setTitle(title);
		album.setArtist(artist);
		album.setYear(year);
		album.setComments(comments);
		album.setCover(cover);
		album.setDateApproved(dateApproved);

		// Set User object values --------------------------
		User user = new User();

		user.setId(idUser);
		user.setName(nameUser);

		album.setUser(user); // Set User object to Album

		// Set Genre object values -------------------------
		Genre genre = new Genre();

		genre.setId(idGenre);
		genre.setGenre(nameGenre);

		album.setGenre(genre); // Set Genre object to Album

		// Add album to ArrayList() ------------------------
		dbRegisters.add(album);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("HERE" + dbRegisters);
	return dbRegisters;
    }
    // End getAll()
    // --------------------------------------------------------------------------------------------

    // getAllbyUser()
    // --------------------------------------------------------------------------------------------
    public ArrayList<Album> getAllbyUser(int idUser, boolean areApproved) {

	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	String sqlQuery = (areApproved) ? QUERY_GETALLBYUSER_APPROVED : QUERY_GETALLBYUSER_PENDING;

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(sqlQuery);) {

	    preparedStatement.setInt(1, idUser);

	    try (ResultSet resultSet = preparedStatement.executeQuery()) {

		while (resultSet.next()) {
		    dbRegisters.add(albumMapper(resultSet));
		}
	    }

	} catch (Exception e) {

	    // TODO Manage exception

	}

	return dbRegisters;
    }

    // End getAllbyUser()
    // --------------------------------------------------------------------------------------------

    // getUserAlbumsDbView()
    // --------------------------------------------------------------------------------------------
    public UserAlbums getUserAlbumsDbView(int userId) {

	// Create POJO and set the recovered values
	UserAlbums userAlbums = new UserAlbums();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETNUMBERUSERALBUMS_VIEW);) {

	    // Replacing ? in the SQL query
	    preparedStatement.setInt(1, userId);

	    // Execute the SQL query
	    try (ResultSet resultSet = preparedStatement.executeQuery()) {

		// Set the DB values into the UserAlbums object
		if (resultSet.next()) {
		    userAlbums.setId_user(resultSet.getInt("userId"));
		    userAlbums.setUserAlbumsApproved(resultSet.getInt("userApprovedAlbums"));
		    userAlbums.setUserAlbumsPending(resultSet.getInt("userPendingAlbums"));
		}

	    }

	} catch (Exception e) {

	    // TODO: handle exception

	}

	return userAlbums;
    }

    // End getUserAlbumsDbView()
    // --------------------------------------------------------------------------------------------

    // getById()
    // --------------------------------------------------------------------------------------------
    public Album getById(int albumId) throws Exception {

	// Create POJO and set the recovered values
	Album album = new Album();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETBYID);) {

	    // Replacing ? in the SQL query
	    preparedStatement.setInt(1, albumId);

	    // Executing the query against the DB and getting the ResultSet with the values
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) {

		album = albumMapper(resultSet);

	    } else {

		throw new Exception("The album " + album.getTitle() + " does not exists in your collection");
	    }
	}

	return album;
    }
    // End getById()
    // --------------------------------------------------------------------------------------------

    /**
     * 
     * Retrieve an album by album ID, checking if that album belongs to the logged user *
     *
     * @return An album if exists in the DB and belongs to idUser
     * @throws Exception If not results for that idUser
     */
    public Album getByIdCheckingUser(int idAlbum, int idUser) throws Exception {

	Album album = new Album();

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_GETBYID_CHECKING_USER);) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, idUser);
	    preparedStatement.setInt(2, idAlbum);

	    // Execute SQL query against the DB and get the ResultSet with the values
	    ResultSet resultSet = preparedStatement.executeQuery();

	    if (resultSet.next()) { // TODO IF

		album = albumMapper(resultSet);

	    } else {

		throw new Exception("The album " + album.getTitle() + " does not exists in your collection");
	    }
	}

	return album;
    }
    // End getByIdCheckingUser()
    // --------------------------------------------------------------------------------------------

    // insert()
    // --------------------------------------------------------------------------------------------
    public Album insert(Album newAlbum) throws Exception {

	try (Connection dbConnection = ConnectionManager.getConnection();
		/**
		 * @see We use RETURN_GENERATED_KEYS to be able to get the id number that the DB has assigned to the new created entry
		 */
		PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, newAlbum.getTitle());
	    preparedStatement.setString(2, newAlbum.getArtist());
	    preparedStatement.setInt(3, newAlbum.getYear());
	    preparedStatement.setString(4, newAlbum.getComments());
	    preparedStatement.setString(5, newAlbum.getCover());
	    preparedStatement.setInt(6, newAlbum.getGenre().getId());
	    preparedStatement.setInt(7, newAlbum.getUser().getId());

	    // Executing the query against the DB and saving the number of affected rows
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

    // updateCheckingUser()
    // --------------------------------------------------------------------------------------------
    public Album updateCheckingUser(Album editAlbum, int idUser) {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_UPDATE_CHECKING_USER);) {

	    // Replace ? in the SQL query
	    preparedStatement.setString(1, editAlbum.getTitle());
	    preparedStatement.setString(2, editAlbum.getArtist());
	    preparedStatement.setInt(3, editAlbum.getYear());
	    preparedStatement.setString(4, editAlbum.getComments());
	    preparedStatement.setString(5, editAlbum.getCover());
	    preparedStatement.setInt(6, editAlbum.getGenre().getId());
	    preparedStatement.setInt(7, editAlbum.getId());

	    int userIdFromAlbum = editAlbum.getUser().getId();

	    if (userIdFromAlbum != idUser) {

		throw new Exception("The album " + editAlbum.getTitle() + " is not yours");

	    } else {

		// Executing the query against the DB and saving the number of affected rows
		int affectedRows = preparedStatement.executeUpdate();

		if (affectedRows != 1) {
		    throw new Exception("The album " + editAlbum.getTitle() + " can not be updated");
		}
	    }

	} catch (Exception e) {

	    // TODO: handle exception

	}

	return editAlbum; // Returns the same editAlbum to repopulate the view form if neccesary
    }

    // End updateCheckingUser()
    // --------------------------------------------------------------------------------------------

    // delete() -- Delete an album with NO verification of ID user. To be used it by the admin.
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

    // deleteCheckingUser() -- Delete an album with ID user verification. To be used it by the user
    // --------------------------------------------------------------------------------------------
    public void deleteCheckingUser(int idAlbum, int idUser) throws Exception, SQLException {

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_DELETE_CHECKING_USER)) {

	    // Get the album before try deleting it in order to fill the feedback below
	    // Album albumToDelete = getById(idAlbum);

	    // Changing the ? in the SQL query
	    preparedStatement.setInt(1, idAlbum);
	    preparedStatement.setInt(2, idUser);

	    // Execute que SQL query and get the # of affected rows (value returned by executeUpdate())
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows != 1) { // The DB returns not affected rows

		// TODO: Log

		throw new Exception("The album can not be deleted."); // TODO Add name of the album (albumToDelete.getTitle(idAlbum))
	    }

	} catch (SQLException sqlException) {

	    // TODO: Log

	    throw new SQLException("Sorry, we were unable to delete the album (database error: " + sqlException + "), contact the admin.");
	}
    }
    // End deleteCheckingUser()
    // --------------------------------------------------------------------------------------------

    // approve() -- Approve a pending album -> To be used by admin
    // --------------------------------------------------------------------------------------------
    @Override
    public boolean approve(int albumId) throws Exception {

	boolean isApprovedOk = false;

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_APPROVE)) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, albumId);

	    // Execute que SQL query and get the # of affected rows (value returned by executeUpdate())
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows == 1) {

		isApprovedOk = true;

	    } else {

		throw new Exception("Album not approved");

	    }

	}

	return isApprovedOk;
    }
    // End approve()
    // --------------------------------------------------------------------------------------------

    // approve() -- Disapprove an album -> To be used by admin
    // --------------------------------------------------------------------------------------------
    @Override
    public boolean disapprove(int albumId) throws Exception {

	boolean isDisapprovedOk = false;

	try (Connection dbConnection = ConnectionManager.getConnection(); PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY_DISAPPROVE)) {

	    // Replace ? in the SQL query
	    preparedStatement.setInt(1, albumId);

	    // Execute que SQL query and get the # of affected rows (value returned by executeUpdate())
	    int affectedRows = preparedStatement.executeUpdate();

	    if (affectedRows == 1) {

		isDisapprovedOk = true;

	    } else {

		throw new Exception("Album not disapproved");

	    }

	}

	return isDisapprovedOk;

    }

    // mapper()
    // --------------------------------------------------------------------------------------------
    private Album albumMapper(ResultSet resultSet) throws Exception {

	// Getting the values from the resultSet

	// Album
	int idAlbum = resultSet.getInt("album_id");
	String title = resultSet.getString("album_title");
	String artist = resultSet.getString("album_artist");
	int year = resultSet.getInt("album_year");
	String comments = resultSet.getString("album_comments");
	String cover = resultSet.getString("album_cover");

	// Genres
	int idGenre = resultSet.getInt("genre_id");
	String nameGenre = resultSet.getString("genre_name");

	// User
	int idUser = resultSet.getInt("id_user");

	// Instantiate objects and set the recovered values ----------------

	// Set Album object values -------------------------
	Album album = new Album();

	album.setId(idAlbum);
	album.setTitle(title);
	album.setArtist(artist);
	album.setYear(year);
	album.setComments(comments);
	album.setCover(cover);

	// Set User object values --------------------------
	User user = new User();

	user.setId(idUser);

	album.setUser(user); // Set User object to Album

	// Set Genre object values -------------------------
	Genre genre = new Genre();

	genre.setId(idGenre);
	genre.setGenre(nameGenre);

	album.setGenre(genre); // Set Genre object to Album

	return album;
    }
    // End mapper()
    // --------------------------------------------------------------------------------------------

} // Class end
  // ----------------------------------------------------------------------------------------------

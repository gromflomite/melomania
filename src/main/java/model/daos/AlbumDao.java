package model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.ConnectionManager;
import model.pojos.Album;

public class AlbumDao {

	// SQL queries
	// --------------------------------------------------------------------------------
	// executeQuery -> returns -> ResulSet
	private final String QUERY_GETALL = " SELECT id, title, artist, year, comments, cover FROM albums ORDER BY id ASC; ";

	private AlbumDao() {
		super();
	}

	// Singleton pattern
	public static AlbumDao INSTANCE = null;

	public static synchronized AlbumDao getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new AlbumDao();
		}

		return INSTANCE;
	}

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
				int id = resulSet.getInt("id");
				String title = resulSet.getString("title");
				String artist = resulSet.getString("artist");
				int year = resulSet.getInt("year");
				String comments = resulSet.getString("comments");
				String cover = resulSet.getString("cover");

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

}

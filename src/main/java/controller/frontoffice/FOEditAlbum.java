package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.daos.AlbumDao;
import model.pojos.Album;
import model.pojos.Feedback;
import model.pojos.Genre;
import model.pojos.User;
import model.pojos.UserAlbums;

@WebServlet("/views/frontoffice/foeditalbum")
public class FOEditAlbum extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Validations
    //private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    //private static Validator validator = factory.getValidator();

    private static final AlbumDao EDITALBUMDAO = AlbumDao.getInstance(); // New AlbumDAO instance

    /**
     * Retrieve album values from DB using the album ID received as parameter from
     * view
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Album albumValues = new Album(); // New album object to set the values from DAO and return it to view

	try {

	    // TODO: Implement security

	    String idAlbumString = request.getParameter("idAlbum"); // Get the album ID from parameter
	    int idAlbum = Integer.parseInt(idAlbumString); // Parse to int the ID album string

	    // Get the id of the user from the Session (the User object in Session was
	    // created by FOHomeController.java)
	    User userSession = (User) request.getSession().getAttribute("userLogin");
	    int idUser = userSession.getId();

	    albumValues = EDITALBUMDAO.getByIdCheckingUser(idAlbum, idUser); // Calling DAO method

	} catch (Exception e) {

	    // TODO: handle exception

	} finally {

	    request.setAttribute("album", albumValues);

	    request.getRequestDispatcher("edit-album.jsp").forward(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// New objects to set the values from form view
	Album editAlbum = new Album();
	User userSession = new User();
	Genre albumGenre = new Genre();
	Feedback feedback = new Feedback();
	UserAlbums userAlbums = new UserAlbums();
	HttpSession session = request.getSession();

	try {

	    // Get album values from the form (FO edit-album.jsp)
	    String albumIdString = request.getParameter("albumId"); // To parse
	    String albumTitle = request.getParameter("albumTitle");
	    String albumArtist = request.getParameter("artist");
	    String albumYearString = request.getParameter("year"); // To parse
	    String albumIdGenreString = request.getParameter("genre"); // To parse
	    String albumComments = request.getParameter("comments");
	    String albumCover = request.getParameter("cover");

	    // Parse needed int values from String
	    int albumId = Integer.parseInt(albumIdString);
	    int albumYear = Integer.parseInt(albumYearString);
	    int albumIdGenre = Integer.parseInt(albumIdGenreString);

	    // Genre
	    albumGenre.setId(albumIdGenre);

	    // User
	    // Get the id of the user from the Session (the User object in Session was
	    // created by FOHomeController.java)
	    userSession = (User) request.getSession().getAttribute("userLogin");

	    int idUser = userSession.getId();

	    // Set all the final values to a Album object
	    editAlbum.setId(albumId);
	    editAlbum.setTitle(albumTitle);
	    editAlbum.setArtist(albumArtist);
	    editAlbum.setYear(albumYear);
	    editAlbum.setGenre(albumGenre); // Genre
	    editAlbum.setComments(albumComments);
	    editAlbum.setCover(albumCover);
	    editAlbum.setUser(userSession);

	    // Call DAO sending the Album object
	    EDITALBUMDAO.updateCheckingUser(editAlbum, idUser);	    

	    // Call the DAO to get the approved and pending user albums to fill the info cards in view
	    userAlbums = EDITALBUMDAO.getUserAlbumsDbView(idUser); // Get the number of approved and pending albums
	    session.setAttribute("userAlbums", userAlbums); // Set the values to session

	    // Create feedback
	    feedback = new Feedback("success", "Album updated!! An admin will check your updated album");

	} catch (Exception e) {

	    // TODO: handle exception

	} finally {
	    session.setAttribute("userAlbums", userAlbums); // Set the values to session
	    session.setAttribute("album", editAlbum);
	    session.setAttribute("feedback", feedback); // Set feedback to Session
	    response.sendRedirect("user-albums?albumsrequest=not-approved");
	}

    }
}

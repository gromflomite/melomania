package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.AlbumDao;
import model.pojos.Album;
import model.pojos.Feedback;
import model.pojos.Genre;
import model.pojos.User;

@WebServlet("/views/frontoffice/fonewalbum")
public class FONewAlbum extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final static Logger logger = LogManager.getLogger("melomania-log");

    private static final AlbumDao ALBUMDAO = AlbumDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Initialize a new Album POJO to send some pre-set values to fill the form
	Album initForm = new Album();

	// Genres are already set via Listener "AppListener.java" into the app context

	request.setAttribute("album", initForm);	

	request.getRequestDispatcher("new-album.jsp").forward(request, response);
	logger.debug("Instantiated new Album object and pre-defined values sended to new-album.jsp: " + initForm);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Album newAlbum = new Album();
	Feedback feedback = new Feedback();	
	HttpSession session = request.getSession(); // To get the logged user details and to put the feedback (we are using redirect)	

	// Get values from form
	String albumIdString = request.getParameter("albumId");
	String albumTitle = request.getParameter("albumTitle");
	String albumArtist = request.getParameter("artist");
	String albumYearString = request.getParameter("year");
	String albumIdGenreString = request.getParameter("genre");
	String albumComments = request.getParameter("comments");
	String albumCover = request.getParameter("cover");

	try {

	    // Parse String values to int
	    int albumId = Integer.parseInt(albumIdString);
	    int albumYear = Integer.parseInt(albumYearString);
	    int albumIdGenre = Integer.parseInt(albumIdGenreString);

	    // Get user from Session
	    User user = (User) session.getAttribute("userLogin");

	    // Genre
	    Genre albumGenre = new Genre();
	    albumGenre.setId(albumIdGenre);

	    // Populate object with all the values
	    newAlbum.setId(albumId);
	    newAlbum.setTitle(albumTitle);
	    newAlbum.setArtist(albumArtist);
	    newAlbum.setYear(albumYear);
	    newAlbum.setComments(albumComments);
	    newAlbum.setCover(albumCover);
	    newAlbum.setUser(user); // User
	    newAlbum.setGenre(albumGenre); // Genre

	    // Call DAO sending the Album object
	    ALBUMDAO.insert(newAlbum);
	    logger.debug("Called AlbumDao.insert():" + newAlbum);

	    // Create feedback
	    feedback = new Feedback("success", "Album created!! An admin will check your new album");

	} catch (Exception e) {

	    logger.fatal("Unable to save new album: ", e);

	} finally {
	    
	    session.setAttribute("feedback", feedback); // Set feedback to session

	    // Send the user to their list of pending albums
	    // request.getRequestDispatcher("user-albums?albumsrequest=not-approved").forward(request, response);
	    response.sendRedirect("user-albums?albumsrequest=not-approved");	    
	}

    }

}

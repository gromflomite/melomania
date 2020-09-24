package controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import model.daos.AlbumDao;
import model.pojos.Album;

@WebServlet("/api/album/*")
public class AlbumAPIController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");
    private static AlbumDao DAO = AlbumDao.getInstance();
    private int returnStatusCode;
    private int albumId;
    private PrintWriter output;
    private String responseToClient;

    public void init(ServletConfig config) throws ServletException {

	LOGGER.info("Controller initialized");

    }

    public void destroy() {

	LOGGER.info("Controller destroyed");

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Execute BEFORE actions

	try {

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");

	    // Get album ID from the client request (using the method created for this)
	    getAlbumIdFromRequest(request.getPathInfo()); // Using getPathInfo() we get the part of the request that comes after /servlet-mapping/ E.g.: If the client complete request is
							  // http://localhost:8446/melomania/api/album/XXXX , we get the value "XXXX".

	    super.service(request, response); // Execute the action requested by the user (GET - POST - PUT - DELETE)

	    // Execute AFTER ACTIONS

	} catch (Exception e) {

	    LOGGER.error(e);
	    returnStatusCode = HttpServletResponseWrapper.SC_INTERNAL_SERVER_ERROR;

	} finally {

	    output = response.getWriter();
	    output.write(responseToClient);
	    response.setStatus(returnStatusCode);
	    output.flush();

	}

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	if (albumId == 0) { // List all albums

	    ArrayList<Album> allAlbums = DAO.getAll();
	    responseToClient = new Gson().toJson(allAlbums);
	    returnStatusCode = HttpServletResponseWrapper.SC_OK;

	} else {

	    try { // List by album ID

		Album albumRequested = DAO.getById(albumId);
		responseToClient = new Gson().toJson(albumRequested);
		returnStatusCode = HttpServletResponseWrapper.SC_OK;

	    } catch (Exception e) {

		LOGGER.error(e);
		returnStatusCode = HttpServletResponseWrapper.SC_NO_CONTENT;

	    }

	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// When clients use a POST calling, we receive a Json object, so we need BufferedReader to get it
	BufferedReader jsonFromClient = request.getReader();
	Album newAlbum = new Gson().fromJson(jsonFromClient, Album.class); // Parsing from JSON to Album object (Gson library)

	try {

	    DAO.insert(newAlbum);
	    responseToClient = ("Album properly created \n" + new Gson().toJson(newAlbum));
	    returnStatusCode = HttpServletResponseWrapper.SC_CREATED;

	} catch (Exception e) {

	    final String DUPLICATE_ALBUM = "Duplicate entry";
	    final String USER_ID_NOTVALID = "REFERENCES `users` (`id`)";
	    final String GENRE_ID_NOTVALID = "REFERENCES `genres` (`id`)";

	    String exceptionContent = e.toString();

	    if (exceptionContent.contains(DUPLICATE_ALBUM)) { // Duplicate album

		LOGGER.error(e);
		responseToClient = ("Your submitted album already exists \n" + new Gson().toJson(newAlbum));
		returnStatusCode = HttpServletResponse.SC_CONFLICT;

	    } else if (exceptionContent.contains(USER_ID_NOTVALID)) { // User ID not valid

		LOGGER.error(e);
		responseToClient = ("Submitted user ID is not valid \n" + new Gson().toJson(newAlbum));
		returnStatusCode = HttpServletResponse.SC_CONFLICT;

	    } else if (exceptionContent.contains(GENRE_ID_NOTVALID)) { // Genre ID not valid

		LOGGER.error(e);
		responseToClient = ("Submitted genre ID is not valid \n" + new Gson().toJson(newAlbum));
		returnStatusCode = HttpServletResponse.SC_CONFLICT;

	    } else { // Other errors

		LOGGER.error(e);
		responseToClient = ("We had a problem saving your album \n" + new Gson().toJson(newAlbum));
		returnStatusCode = HttpServletResponse.SC_CONFLICT;
	    }

	}

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// When clients use a POST calling, we receive a Json object, so we need BufferedReader to get it
	BufferedReader jsonFromClient = request.getReader();
	Album albumToUpdate = new Gson().fromJson(jsonFromClient, Album.class); // Parsing from JSON to Album object (Gson library)

	try {

	    DAO.update(albumToUpdate);
	    responseToClient = ("Album properly updated \n" + new Gson().toJson(albumToUpdate));
	    returnStatusCode = HttpServletResponseWrapper.SC_OK;

	} catch (Exception e) {

	    LOGGER.error(e);
	    responseToClient = ("We had a problem updating your album \n" + new Gson().toJson(albumToUpdate));
	    returnStatusCode = HttpServletResponse.SC_CONFLICT;
	}

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {

	    Album albumToDelete = DAO.delete(albumId);
	    responseToClient = new Gson().toJson(albumToDelete); // Returning the values of the deleted album
	    returnStatusCode = HttpServletResponseWrapper.SC_OK;

	} catch (Exception e) {

	    LOGGER.error(e);
	    responseToClient = ("Delete error: The album does not exist");
	    returnStatusCode = HttpServletResponse.SC_CONFLICT;
	}

    }

    /**
     * 
     * Method to get the album ID from the request received from the client
     * 
     * E.g.: If clients send GET melomania/api/album/12 , we are interested in "12" (check getPathInfo() at service() method)
     * 
     * @param clientRequest
     */
    private void getAlbumIdFromRequest(String clientRequest) {

	albumId = 0; // Album Id initialized at 0 (check doGet() method)

	if (clientRequest != null) {

	    String[] parametersRequests = clientRequest.split("/"); // Split the request by slash

	    if (parametersRequests.length > 0) {

		albumId = Integer.parseInt(parametersRequests[1]); // If array is greater than 0, take the 1st position value (remember, zero index)

	    }

	}

    }

}

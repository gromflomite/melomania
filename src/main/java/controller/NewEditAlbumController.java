package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.daos.AlbumDao;
import model.pojos.Album;
import model.pojos.Feedback;

@WebServlet("/newalbum")

public class NewEditAlbumController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Validations
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieving album values from album list table (using edit option) via doGet
		
		Album album = new Album();
		
		// Getting and parsing the ID number
		// It comes with forced "0" from the form view
		try {

			int idParameter = Integer.parseInt(request.getParameter("id"));			

			if (idParameter > 0) {

				// Instanciating DAO
				AlbumDao editAlbumDao = AlbumDao.getInstance();

				// Getting the album registry by ID from DB
				album = editAlbumDao.getById(idParameter);
			}

		} catch (Exception e) {
			
			// TODO Manage this exception if necessary
			e.printStackTrace();

		} finally {

			// Setting the attribute (user object)
			request.setAttribute("album", album);

			// Calling the JSP forwarding the request
			request.getRequestDispatcher("views/albums/new-album.jsp").forward(request, response);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Tag to go via redirect or via forward
		boolean isRedirect = false;

		// Instancing new albumDAO (via Singleton pattern)
		AlbumDao albumDao = AlbumDao.getInstance();

		// Creating new album object
		Album album = new Album();

		// Creating new feedback object
		Feedback feedback = new Feedback();

		// Getting JSession
		HttpSession session = request.getSession();

		try {
			// Getting the values from the form (new-album.jsp)			
			int id 		= Integer.parseInt(request.getParameter("albumId"));
			String title 	= request.getParameter("albumTitle");
			String artist 	= request.getParameter("artist");
			int year 	= Integer.parseInt(request.getParameter("year")); // TODO Check if user entered just numbers
			String comments = request.getParameter("comments");
			String cover	= request.getParameter("cover");

			// Setting the ID and name on the Album object
			album.setId(id);
			album.setTitle(title);
			album.setArtist(artist);
			album.setYear(year);
			album.setComments(comments);
			album.setCover(cover);		

			// Validating entered values in Album fields (for validation annotations, see Album.java)			
			//Sending the created object to validate and pushing the validation results in a Set
			Set<ConstraintViolation<Album>> violations =  validator.validate(album);			
						
			if (violations.isEmpty()) { // If empty --> No validation errors

				if (id == 0) {
					
					// Create the registry in the DB
					albumDao.insert(album);
				
				} else { // if id != 0, the registry already exists in the DB, so update it
					
					// Update the registry in the DB
					albumDao.update(album);				
				}	
				
				// Creating some feedback to the user
				feedback = new Feedback("success", "Album properly saved");

				// The entered values are correct, so we are going back to view via redirect
				isRedirect = true;

			} else { // validations !empty --> There are validations error (messages in validation annotations (Album.java) will show as feedback)
				
				String validationErrorMessages = "";

				// Iterating the "violations" Set to extract validations messages and create a String with them in order to send them back to view as Feedback object
				for (ConstraintViolation<Album> v : violations) {
					validationErrorMessages += "<p>" + v.getMessage() + "</p>";
				}

				feedback = new Feedback("danger", validationErrorMessages);				

				// Sending back the object to the form view to repopulate the form fields with the entered values
				request.setAttribute("album", album);

				// The entered values are not correct, so we are going to send back the user to the new album form via forward (we are not modifying the isRedirect boolean)
			}
		
		// Catching the SQLException of already existing album to give more precise feedback to the user
		} catch ( SQLException e) {	

			feedback = new Feedback( "danger", "The album '" + album.getTitle() + "' already exists in your collection");
						

		} catch (Exception e) {

			feedback = new Feedback("danger", "Sorry, we were not able to save the album: " + e.getMessage());

		} finally {

			// Setting attributes to JSession to retrive them in the view
			session.setAttribute("feedback", feedback); // We could be using a redirect, so an option to pass info to the view is saving it in JSession			

			if (isRedirect) {

				// Go back to the view (album controller)
				response.sendRedirect("album"); 

			} else {

				// Sending back the object to the form view to repopulate the form fields with the entered values
				request.setAttribute("album", album);
				
				// Calling the form view passing the feeback and the object
				request.getRequestDispatcher("new-album.jsp").forward(request, response);
			}

		} // finally end --------------------------------------------------------------------------

	} // doPost end -------------------------------------------------------------------------------

} // class end ------------------------------------------------------------------------------------

package controller;

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

@WebServlet("/newalbum")

public class NewEditAlbumController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Variable to save the album id value of the album to edit
	// We are using this method to avoid the necessity to send back the id to the form (as attribute) and 
	// get it back the value as parameter)	
	public int albumUpdateId = 0;	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// RETRIEVING album values from album list table (using edit option) via doGet
		
		Album album = new Album();
		
		// Getting and parsing the ID number
		// It comes with forced "0" from the form view
		try {

			int idParameter = Integer.parseInt(request.getParameter("id"));
			
			albumUpdateId = Integer.parseInt(request.getParameter("id"));

			if (idParameter > 0) {

				// Instanciating DAO
				AlbumDao editAlbumDao = AlbumDao.getInstance();

				// Getting the album registry by ID from DB
				album = editAlbumDao.getById(idParameter);
			}

		} catch (Exception e) {

			// TODO --
			e.printStackTrace();

		} finally {

			// Setting the attribute (user object)
			request.setAttribute("album", album);

			// Calling the JSP forwarding the request
			request.getRequestDispatcher("new-album.jsp").forward(request, response);
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
			int id 			= albumUpdateId; // Taken the value from the global variable //Integer.parseInt(request.getParameter("id"));			
			String title 	= request.getParameter("albumTitle");
			String artist 	= request.getParameter("artist");
			int year 		= Integer.parseInt(request.getParameter("year")); // TODO Check if user entered just numbers
			String comments = request.getParameter("comments");
			String cover	= request.getParameter("cover");

			// Setting the ID and name on the Album object
			album.setId(id);
			album.setTitle(title);
			album.setArtist(artist);
			album.setYear(year);
			album.setComments(comments);
			album.setCover(cover);

			// Validating entered values in Album field
			if ( (artist != null) && (artist.length() >= 2) && (artist.length() <= 100) && ( (year >= 1500) && (year <= 2050) ) ) {

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

			} else {

				// Sending feedback to user 
				feedback = new Feedback("danger", "The Artist field must contain at least 2 characters and Year field must be between 1500 and 2050");

				// Sending back the object to the form view to repopulate the form fields with the entered values
				request.setAttribute("newAlbum", album);
				
				// The entered values are not correct, so we are going to send back the user to the new album form via forward (we are not modifying the isRedirect boolean)
			}

		} catch (Exception e) {

			feedback = new Feedback("danger", "We were not able to save the album: " + e.getMessage());

		} finally {

			// Setting attributes to JSession to retrive them in the view
			session.setAttribute("feedback", feedback); // We could be using a redirect, so an option to pass info to the view is saving it in JSession			

			if (isRedirect) {

				// Go back to the view (album controller)
				response.sendRedirect("album"); 

			} else {

				// Calling the form view passing the feeback and the object
				request.getRequestDispatcher("new-album.jsp").forward(request, response);

			}

		} // finally end --------------------------------------------------------------------------

	} // doPost end -------------------------------------------------------------------------------

} // class end ------------------------------------------------------------------------------------

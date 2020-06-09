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
public class NewAlbumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Not using doGet yet
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Tag to go via redirect or via forward
		boolean isRedirect = false;

		// Instancing new albumDAO (via Singleton pattern)
		AlbumDao newAlbumDao = AlbumDao.getInstance();

		// Creating new album object
		Album newAlbum = new Album();

		// Creating new feedback object
		Feedback feedback = new Feedback();

		// Getting JSession
		HttpSession session = request.getSession();

		try {

			// Getting the values from the form (new-album.jsp)
			String title = request.getParameter("albumTitle");
			String artist = request.getParameter("artist");
			int year = Integer.parseInt(request.getParameter("year")); // TODO Check if user entered just numbers
			String comments = request.getParameter("comments");
			String cover = request.getParameter("cover");

			// Setting the ID and name on the Album object
			newAlbum.setTitle(title);
			newAlbum.setArtist(artist);
			newAlbum.setYear(year);
			newAlbum.setComments(comments);
			newAlbum.setCover(cover);

			// Validating entered values in Album field
			if ( (artist != null) && (artist.length() >= 2) && (artist.length() <= 100) && ( (year >= 1500) && (year <= 2050) ) ) {

				// Sending Album object to AlbumDAO
				newAlbumDao.insert(newAlbum);

				// Creating some feedback to the user
				feedback = new Feedback("success", "Album properly saved");

				// The entered values are correct, so we are going back to view via redirect
				isRedirect = true;

			} else {

				// Sending feedback to user 
				feedback = new Feedback("danger", "The Artist field must contain at least 2 characters and Year field must be between 1500 and 2050");

				// Sending back the object to the form view to repopulate the form fields with the entered values
				request.setAttribute("newAlbum", newAlbum);
				
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

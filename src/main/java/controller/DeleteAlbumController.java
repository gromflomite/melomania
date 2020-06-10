package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.pojos.Album;
import model.pojos.Feedback;

@WebServlet("/deletealbum")
public class DeleteAlbumController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// Creating new feedback object
		Feedback feedback = new Feedback();
		
		// Get parameter from view		
		String parameterId = request.getParameter("id"); // TODO Validate if user entered just numbers
		int albumId = Integer.parseInt(parameterId);
		
		// Create an instace of AlbumDAO (Singleton pattern)		
		AlbumDao deleteAlbumDao = AlbumDao.getInstance();
		
		try {
			
			// Calling delete() in AlbumDAO
			deleteAlbumDao.delete(albumId);
			
			feedback = new Feedback("success", "The album has been properly deleted");
			
		} catch (Exception e) {

			feedback = new Feedback("warning", "There was an error trying to delete the album: " + e.getMessage());			
		}
		
		/**
		 * Sending data back to view
		 * 
		 * We have to send back all the registers of the DB to populate the fields on the view 
		 */
		
		// Creating an ArrayList with the registers obtained by dao.getAll() method from the DB
		ArrayList<Album> allAlbums = deleteAlbumDao.getAll();
		
		// Sending the ArrayList to view
		request.setAttribute("albums", allAlbums );		
		
		// Sending the feedback		
		request.setAttribute("feedback", feedback);
		
		// Calling the view and forwarding the request
		request.getRequestDispatcher("albums.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// We are not using doPost()
		doGet(request, response);
	}

}

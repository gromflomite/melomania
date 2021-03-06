package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.daos.implementations.AlbumDaoImpl;
import model.pojos.Album;

@WebServlet("/album")
public class AlbumController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Instancing new albumDAO (via Singleton pattern)
		AlbumDao dao = AlbumDaoImpl.getInstance();
		
		// Saving in a ArrayList the values from DAO
		ArrayList<Album> albums = dao.getAll();

		// Setting attributes to request
		request.setAttribute("albums", albums);
		
		// Calling albums.jsp pushing request and response
		request.getRequestDispatcher("views/albums/albums.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		// We are not using doPost()
		doGet(request, response);
	}

}

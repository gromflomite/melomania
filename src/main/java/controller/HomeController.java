package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;        

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	AlbumDao albumDao = AlbumDao.getInstance();
	
	// Getting the genre ID and genre values selected in navbar dropdown
	String idGenreParameter = request.getParameter("idGenre");
	String genre = request.getParameter("genre");
	
	/**
	 * 
	 * At the initial web charge, we have not genre ID selected so, is null.
	 * 
	 * If is null, show the last N albums.
	 * 
	 * If the user has selected a genre, show the albums with this genre.
	 * 
	 */
	if (idGenreParameter != null) {

	    int idGenre = Integer.parseInt(idGenreParameter);
	    
	    request.setAttribute("albums", albumDao.getByGenre(idGenre));
	    request.setAttribute("cardDeckTitle", genre);
	    
	} else {

	    // Calling getLast() method from AlbumDao to retrieve the last N albums that we will show in index.jsp
	    boolean allAlbums = true;
	    
	    request.setAttribute("albums", albumDao.getLast(99));	    
	    request.setAttribute("allalbums", allAlbums);
	    request.setAttribute("cardDeckTitle", "All albums in your collection ");
	}
	
	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// We are going to use only doGet()
	doGet(request, response);
    }
}

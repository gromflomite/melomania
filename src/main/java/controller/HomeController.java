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

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
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
	 * Also, we are sending to the view the text for use as title in HTML
	 * 
	 */	
	AlbumDao albumDao = AlbumDaoImpl.getInstance();
	
	ArrayList<Album> dbRegisters = new ArrayList<Album>();
	
	if (idGenreParameter != null) {

	    int idGenre = Integer.parseInt(idGenreParameter);
	    
	    dbRegisters = albumDao.getByGenre(idGenre);	   
	   	   
	    request.setAttribute("cardDeckTitle", genre + " (" + dbRegisters.size() + " albums of this genre)" );	    
	    
	} else {

	    // Calling getLast() method from AlbumDao to retrieve the last N albums that we will show in index.jsp
	    // getLast() shows just approved albums (see AlbumDAO for more info)
	    dbRegisters = albumDao.getLast(99);
	    
	    request.setAttribute("cardDeckTitle", "melomania approved albums (" + dbRegisters.size() + " albums in our collection)");
	}
	
	request.setAttribute("albums", dbRegisters);
	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// We are going to use only doGet()
	doGet(request, response);
    }
}

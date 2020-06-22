package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.daos.GenreDao;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final AlbumDao albumDao = AlbumDao.getInstance();
    private static final GenreDao genreDao = GenreDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Getting the genre ID selected in navbar dropdown
	String idGenreParameter = request.getParameter("idGenre");

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

	} else {

	    // Calling getLast() method from AlbumDao to retrieve the last N albums that we
	    // will show in index.jsp
	    request.setAttribute("albums", albumDao.getLast(3));
	}

	request.setAttribute("genres", genreDao.getAll());

	// request.getRequestDispatcher("header.jsp").forward(request, response);
	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// We are going to use only doGet()
	doGet(request, response);
    }
}

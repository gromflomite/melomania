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

	// We are not going to use the doGet method
	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// Calling getLast() method from AlbumDao to retrieve the last N albums that we will show in index.jsp
	request.setAttribute("albums", albumDao.getLast(5));	
	
	// Calling getAll() method from GenreDao to retrieve all the genres that we will show in a dropdown
	request.setAttribute("genres", genreDao.getAll());	
	
	// Calling index.jsp sending request and response
	request.getRequestDispatcher("index.jsp").forward(request, response);
	request.getRequestDispatcher("header.jsp").forward(request, response);
    }
}

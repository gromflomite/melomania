package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.GenreDao;
import model.daos.implementations.GenreDaoImpl;

@WebServlet("/genre")
public class GenreController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final GenreDao genreDao = GenreDaoImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// We are not going to use the doGet method
	doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Calling getAll() method from GenreDao to retrieve all the genres that we will show in a dropdown
	request.setAttribute("genres", genreDao.getAll());
	request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}

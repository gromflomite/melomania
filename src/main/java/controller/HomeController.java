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
    
    private static final AlbumDao albumDAO = AlbumDao.getInstance();        

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// We are not going to use the doGet method
	doPost(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setAttribute("albums", albumDAO.getLast(5));
	
	request.getRequestDispatcher("index.jsp").forward(request, response);	
    }

}

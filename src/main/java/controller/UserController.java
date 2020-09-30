package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.UserDao;
import model.daos.implementations.UserDaoImpl;
import model.pojos.User;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	UserDao dao = UserDaoImpl.getInstance();
	ArrayList<User> users = dao.getAll();
	request.setAttribute("users", users);
	request.getRequestDispatcher("views/users/users.jsp").forward(request, response);

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// We are not going to use doPost()
	doGet(request, response);
    }

}

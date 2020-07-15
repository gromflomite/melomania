package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.daos.UserDao;
import model.pojos.Feedback;
import model.pojos.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// Get parameters from view (login.jsp)
	String userName 	= request.getParameter("userName");
	String userPassword	= request.getParameter("userPassword");
	
	// Get Session
	HttpSession session = request.getSession();
	
	// Check against the DB the values entered by user -----------------
	
	UserDao dao = UserDao.getInstance();
	
	User userLogin = dao.checkLogin(userName, userPassword); // User values if login is correct or null if not
	
	if (userLogin != null) {

	    // Set attribute to session
	    session.setAttribute("userLogin", userLogin);

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("success", "Welcome again!!"));

	    // Go back to index
	    request.getRequestDispatcher("home").forward(request, response);

	} else { // Entered login values not correct

	    // Invalidate session
	    session.invalidate();

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("warning", "Username or password not correct!!"));

	    // Go back to loging page
	    request.getRequestDispatcher("views/login/login.jsp").forward(request, response);

	}	
	
    }

}
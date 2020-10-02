package controller.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.UserDao;
import model.daos.implementations.UserDaoImpl;

@WebServlet("/api/user/*")
public class UserAPIController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private final static Logger LOGGER = LogManager.getLogger("melomania-log");
    private static UserDao DAO = UserDaoImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Set response details
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");

	// Get paramater from call	
	String userName = request.getParameter("name");
	
	// Call DAO
	boolean nameFound = DAO.searchByName(userName);
	
	if (nameFound) { // If true -> Name exists in the DB ** false -> Name does not exist	    

	    response.setStatus(HttpServletResponse.SC_OK);

	} else {

	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);

	}

    }

}

package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.UserDao;
import model.pojos.Feedback;
import model.pojos.Role;
import model.pojos.User;

@WebServlet("/views/frontoffice/fouserprofile")
public class FOUserProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");
    private final static UserDao DAO = UserDao.getInstance();    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {

	    // Get the user ID and role values from Session "userLogin" (created by LoginController UserDAO -> checkLogin())
	    //
	    // Must collect the other user values (name and email) from the DB (userDAO - > getByID) not from
	    // the Session: If user updates their profile, we will not be able to get new values from Session until 
	    // LoginController has been called again.	    
	   
	    // Get user ID from Session
	    User userSession = (User) request.getSession().getAttribute("userLogin");	   	   	    
	    int userIdFromSession = userSession.getId();
	   
	    // Call UserDao to retrieve user name, mail and role
	    User userDetailsFromDb = DAO.getById(userIdFromSession);
	    
	    String userName = userDetailsFromDb.getName();
	    String userMail = userDetailsFromDb.getEmail();
	    String userRole = userDetailsFromDb.getRole().getType_role();

	    // Set the attributes to send them back to the view
	    request.setAttribute("userId", userIdFromSession);
	    request.setAttribute("userName", userName);
	    request.setAttribute("userMail", userMail);
	    request.setAttribute("userRole", userRole);

	} catch (Exception e) {

	    LOGGER.error("Problem: ", e);

	} finally {

	    request.getRequestDispatcher("profile.jsp").forward(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Feedback feedback = new Feedback();
	HttpSession session = request.getSession(); // To get the logged user details and to put the feedback (we are using redirect)	
	
	try {
	    
	    User userSession = (User) request.getSession().getAttribute("userLogin"); // To get some user values (user ID, role ID)
	    User userUpdate = new User();
	    Role userRole = new Role();

	    // Get the entered values from view
	    String userName = request.getParameter("userName");
	    String userMail = request.getParameter("userMail");

	    // String changeUserPassword = request.getParameter("passwordChange");
	    // String changeUserPasswordConfirm = request.getParameter("passwordChangeConfirm");

	    // Get values from Session
	    int userId = userSession.getId();
	    int userRoleId = userSession.getRole().getId_role();
	    String userPassword = userSession.getPassword();

	    // Set values to User object
	    userUpdate.setId(userId);
	    userUpdate.setName(userName);
	    userUpdate.setEmail(userMail);
	    userUpdate.setPassword(userPassword);

	    // Set values to Role object
	    userRole.setId_role(userRoleId);

	    // Set role object to user
	    userUpdate.setRole(userRole);

	    // Call the DAO update method
	    DAO.update(userUpdate);

	    // Creating some feedback to the user
	    feedback = new Feedback("success", "Details properly saved");	    

	} catch (Exception e) {
	    
	    feedback = new Feedback("danger", "We had a problem updating your profile");
	    LOGGER.error("User profile update not done: ", e);

	} finally {
	    
	    session.setAttribute("feedback", feedback);	    
	    response.sendRedirect("fouserprofile");
	}

    }

}

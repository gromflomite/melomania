package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.UserDao;
import model.daos.implementations.UserDaoImpl;
import model.pojos.Feedback;
import model.pojos.Role;
import model.pojos.User;

@WebServlet("/newuser")

public class NewEditUserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	User dbRegister = new User();

	try {

	    // Getting the user values using the id number received by parameter in the URL from the view
	    
	    // Get and parse the id from the URL parameter
	    int idUserParameter = Integer.parseInt(request.getParameter("userid"));

	    if (idUserParameter > 0) { // If user ID is greater than 0, the users exists and we already have their ID (via URL parameter from the view)

		// Inst. user DAO
		UserDao editUser = UserDaoImpl.getInstance();

		// Call DAO getById method passing the user ID
		dbRegister = editUser.getById(idUserParameter);
	    }
	    
	} catch (Exception e) {

	    // TODO: handle exception

	} finally {

	    // Set the user object as attribute
	    request.setAttribute("user", dbRegister);

	    // Call the JSP view forwarding the request
	    request.getRequestDispatcher("views/users/new-user.jsp").forward(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// New UserDao instance
	UserDao userDao = UserDaoImpl.getInstance();
	
	// Creating new feedback object
	Feedback feedback = new Feedback();
	
	// New User object
	User user = new User();	

	// Get the parameters from the view form	
	String idString = 		request.getParameter("id");
	String name = 			request.getParameter("userName");
	String email = 			request.getParameter("userEmail");
	String idRoleString = 		request.getParameter("idRol");
	String password = 		request.getParameter("password");
	String passwordChange = 	request.getParameter("passwordChange");
	String passwordChangeConfirm = 	request.getParameter("passwordChangeConfirm");	
		
	int id;		// To put the idString parsed value	
	int idRole;	// To put the idRoleString parsed value	
	
	Role role = new Role();
	
	try {

	    // Parsing the string attributes received	   
	    idRole = Integer.parseInt(idRoleString);
	    id =     Integer.parseInt(idString);	    
	    
	    // Setting the values on User object	
	    user.setName(name);
	    user.setEmail(email);
	    
	    // Instanciating new Role object, setting the id role to it setting the Role to user
	    role.setId_role(idRole);
	    user.setRole(role);

	    // Password management --------------------------------------------
	    if (password != null) { // New user password

		user.setPassword(password);

	    } else { // User password change set

		if ((passwordChange != null && passwordChangeConfirm != null) && (passwordChange.equals(passwordChangeConfirm))) {

		    user.setPassword(passwordChange);

		} else {
		    
		    // TODO LOG.trace
		    // TODO feedback
		    
		}
	    }
	    // End password management -----------------------------------------

	    // if id == 0, the registry does not exists in the DB, so create it
	    if (id == 0) {		
		
		userDao.insert(user); // Create the registry in the DB
		
	    // if id != 0, the registry alreadt exists in the DB, so update it 
	    } else { 
		
		userDao.update(user); // Update the registry in the DB
	    }

	    // Creating some feedback to the user
	    feedback = new Feedback("success", "User properly saved with new ID: " + user.getId());

	} catch (Exception e) {
	    // TODO: handle exception
	
	} finally {

	    // Send the feedback back to the view (new-user.jsp)
	    request.setAttribute("feedback", feedback);

	    // Send the product back to the view to get the form fields filled after submit (new-user.jsp)
	    request.setAttribute("user", user);

	    // Go back to the view (new-user.jsp)
	    request.getRequestDispatcher("views/users/new-user.jsp").forward(request, response);

	}

    }

}

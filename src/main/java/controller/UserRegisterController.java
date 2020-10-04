package controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.implementations.UserDaoImpl;
import model.pojos.Feedback;
import model.pojos.Role;
import model.pojos.User;

@WebServlet("/userRegister")
/**
 * 
 * @author Caronte
 * 
 * Controller used for create a new user from register view and save the values into the DB. * 
 *
 */
public class UserRegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static UserDaoImpl DAO = UserDaoImpl.getInstance();
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Call doPost()
	doPost(request, response);

    }

    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// New instances
	User newUser = new User();
	Role newUserRole = new Role();
	Feedback feedback = new Feedback();

	String passwordChecked = "";
	String validationErrorMessages = "<p> <i class=\"fas fa-exclamation-triangle\"></i> <b> There are errors in the submited form: </p></b>"; // To put the form values validation error messages

	boolean passwordProblems = true;
	boolean newUserCreatedOk = false;

	try {

	    // Get parameters from view
	    String userNameParameter = request.getParameter("userName");
	    String emailParameter = request.getParameter("email");
	    String passwordParameter = request.getParameter("password");
	    String passwordConfirmParameter = request.getParameter("passwordConfirm");

	    // Validate password (just if both password and confirm are equals. Rest of password validations done with Hibernate below)
	    if (passwordParameter.equals(passwordConfirmParameter)) {

		passwordChecked = passwordParameter;
		
		passwordProblems = false;

	    } else {

		/**
		 * Setting a fake password that will not be saved in order to avoid the validations error messages (if password in User object is not set with a string that meets the Hibernate constrains, we will have the validation error messages in the view).
		 */
		final String passwordFake = "passwordFake";
		
		passwordChecked = passwordFake;

		validationErrorMessages += "<p><b>password: </b>Password confirmation failed</p>";

		feedback = new Feedback("danger", validationErrorMessages);

		// boolean problemsInForm remain true -> DAO call will not be executed

	    }

	    // Set values to role object
	    newUserRole.setId_role(Role.LISTENER); // Hardcoding plain user value

	    // Set values to user object
	    newUser.setName(userNameParameter);
	    newUser.setEmail(emailParameter);
	    newUser.setPassword(passwordChecked);
	    newUser.setRole(newUserRole); // Role object

	    // Validate entered values (Hibernate validator -> check user model)
	    Set<ConstraintViolation<User>> hibernateViolations = validator.validate(newUser);

	    if (hibernateViolations.isEmpty() && (!passwordProblems)) { // No validations errors and password confirm is ok

		newUserCreatedOk = DAO.insert(newUser); // Call DAO

		if (newUserCreatedOk) {

		    feedback = new Feedback("success", "Signed up, welcome to melomania!");

		} else {

		    feedback = new Feedback("danger", "We had a problem signing you up");

		}

	    } else {

		for (ConstraintViolation<User> v : hibernateViolations) { // Extract the validations errors from "Set"

		    validationErrorMessages += "<p><b>" + v.getPropertyPath() + "</b>: " + v.getMessage() + "</p>"; // Set the error messages into the String

		    feedback = new Feedback("danger", validationErrorMessages);

		}

	    }

	} catch (Exception e) { // Catching the exceptions from DAO method call

	    feedback = new Feedback("danger", "Email or name already exists");

	    LOGGER.error(e);

	} finally {

	    if (newUserCreatedOk) { // User properly registered

		request.setAttribute("feedback", feedback);
		request.getRequestDispatcher("views/login/login.jsp").forward(request, response);

	    } else { // User not registered

		request.setAttribute("feedback", feedback);
		request.setAttribute("newUser", newUser); // Sending back to view the received parameters to refill "Name" and "Email" form fields
		request.getRequestDispatcher("views/users/register.jsp").forward(request, response);

	    }

	}

    }

}

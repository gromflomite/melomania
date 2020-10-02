package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import model.pojos.Feedback;
import model.pojos.User;

@WebServlet("/userRegister")
public class UserRegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// New instances
	User newUser = new User();
	Feedback feedback = new Feedback();
	boolean emailValid = false;
	boolean userNameValid = false;
	boolean passwordValid = false;

	// Get parameters from view
	String userName = request.getParameter("userName");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String passwordConfirm = request.getParameter("passwordConfirm");

	// Validate username
	if (!userName.isBlank()) {

	    if ((userName.length() <= 3) || (userName.length() >= 25)) {

		// UserName NOT valid
		System.out.println("userName not valid - TOO LONG OR TOO SHORT");

	    } else {

		// Username valid
		System.out.println("userName VALID");

	    }

	} else {

	    // Username not valid
	    System.out.println("userName not valid - BLANK");

	}

	// Validate email
	emailValid = EmailValidator.getInstance().isValid(email);

	if (emailValid) {

	    // Mail valid
	    System.out.println("Mail valid");

	} else {

	    // Mail not valid
	    System.out.println("Mail not valid");

	}

	// Validate password
	if ((!password.isBlank()) && (!passwordConfirm.isBlank())) {	    

	    if (password.equals(passwordConfirm)) {

		// PASS OK
		System.out.println("Password valid");

	    } else {

		// Pass not equal
		System.out.println("Password NOT valid -> NOT EQUAL");

	    }

	} else {

	    // Pass or confirm empty
	    System.out.println("Password NOT valid -> BLANK");

	}

    }

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.pojos.Feedback;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private final static Logger logger = LogManager.getLogger("melomania-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {

	    // Get Session
	    HttpSession session = request.getSession();
	    
	    logger.debug("User log out (Session): " + request.getSession().getAttribute("userLogin"));

	    // Invalidate session
	    session.invalidate();

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("success", "You are out, see you soon!!"));

	} catch (Exception e) {

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("warning", "We had a problem getting you out, sorry"));
	    logger.fatal("Unable to finish the log out properly", e);

	} finally {

	    // Go back to index
	    request.getRequestDispatcher("home").forward(request, response);
	    logger.info("User properly log out -> getRequestDispatcher(\"home\")");

	}

    }

}

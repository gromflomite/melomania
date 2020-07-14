package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.pojos.Feedback;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	try {

	    // Get Session
	    HttpSession session = request.getSession();

	    // Invalidate session
	    session.invalidate();

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("success", "You are out, see you soon!!"));

	} catch (Exception e) {

	    // Set feedback
	    request.setAttribute("feedback", new Feedback("warning", "We had a problem getting you out, sorry"));

	} finally {

	    // Go back to index
	    request.getRequestDispatcher("index.jsp").forward(request, response);

	}

    }

}

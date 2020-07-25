package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.pojos.Feedback;
import model.pojos.Role;
import model.pojos.User;

/**
 * Servlet Filter implementation class BackOfficeFilter
 */
@WebFilter(
	dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ERROR }, 
	urlPatterns = { "/views/backoffice/*" })

public class BackOfficeFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	/**
	 * 
	 * Users that can navigate backoffice: ADMIN
	 * 
	 */

	// Important: It must be parsed from ServletRequest to HttpServletRequest
	HttpServletRequest httpRequest = (HttpServletRequest) request;
	HttpServletResponse httpResponse = (HttpServletResponse) response;

	// Get the root path of the app
	String rootPath = httpRequest.getContextPath();

	// Creating new feedback object
	Feedback feedback = new Feedback();

	HttpSession session = httpRequest.getSession();

	// Get user values from Session
	User userLogin = (User) httpRequest.getSession().getAttribute("userLogin");

	if (userLogin == null) { // User not logged

	    // Create feedback
	    feedback = new Feedback("danger", "You are not logged or not authorized");
	    session.setAttribute("feedback", feedback);

	    httpResponse.sendRedirect(rootPath + "/views/unauthorized/index.jsp"); // Absolute path

	} else if (userLogin.getRole().getId_role() != Role.ADMIN) { // For more explanation about LISTENER constant, see the Role model

	    // Create feedback
	    feedback = new Feedback("danger", "You have no privileges to view that page (you are a " + userLogin.getRole().getType_role() + ")");
	    session.setAttribute("feedback", feedback);

	    // User has not enough privileges to access that page -> Go home
	    httpResponse.sendRedirect(rootPath + "/views/unauthorized/index.jsp"); // Absolute path

	} else { // User is logged and has the needed privileges -> Keep moving

	    chain.doFilter(request, response);

	}
    }
}

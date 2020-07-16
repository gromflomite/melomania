package controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * IMPORTANT: 
 *
 */
@WebServlet("/views/frontoffice/fohome") 
public class FOHomeController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    // Hardcoding some values to show them in frontoffice
	    // TODO Get these values from DB
	    request.setAttribute("albums_approved", 3);
	    request.setAttribute("albums_pending", 2);
	    
	    // Get the path
	    //String contextPath = request.getContextPath();
	    //String finalView = contextPath + "/views/frontoffice/index.jsp";
	    
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    	// Using just doPost()
		doGet(request, response);
	}

}

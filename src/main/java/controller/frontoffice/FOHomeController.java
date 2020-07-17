package controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/views/frontoffice/fohome") // IMPORTANT: As we are using a filter (FrontOfficeFilter.java) in order to
					 // check authentication and in that filter we are using "/views/frontoffice/*"
					 // as pattern to filtrate, we must set the same path in this controller
					 // (remember to call this controller using the specified path).

public class FOHomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

	/**
	 * IMPORTANT: Note the controller URL pattern specified above:
	 * "/views/frontoffice/fohome"
	 * 
	 * So, in the .getRequestDispatcher we must indicate just the .jsp because the last part (fohome)
	 * will be removed --> "/views/frontoffice/fohome" + "index.jsp" = "/views/frontoffice/index.jsp"
	 * 
	 */
	request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Using just doPost()
	doGet(request, response);
    }

}

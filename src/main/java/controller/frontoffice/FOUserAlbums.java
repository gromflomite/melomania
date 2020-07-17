package controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/views/frontoffice/user-albums")
public class FOUserAlbums extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String albumsRequest = request.getParameter("albumsrequest");

	if (albumsRequest.equals("approved")) { // User wants validated albums

	    request.setAttribute("albums_approved", 3);	   
	    
	    request.getRequestDispatcher("approved-albums.jsp").forward(request, response);

	} else { // We send back pending albums

	    request.setAttribute("albums_pending", 2);
	    
	    request.getRequestDispatcher("pending-albums.jsp").forward(request, response);
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Using just doGet()
	doGet(request, response);
    }

}

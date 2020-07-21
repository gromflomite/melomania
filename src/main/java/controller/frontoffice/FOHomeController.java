package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.daos.AlbumDao;
import model.pojos.User;
import model.pojos.UserAlbums;

@WebServlet("/views/frontoffice/fohome") // IMPORTANT: As we are using a filter (FrontOfficeFilter.java) in order to
					 // check authentication and in that filter we are using "/views/frontoffice/*"
					 // as pattern to filtrate, we must set the same path in this controller
					 // (remember to call this controller using the specified path).

public class FOHomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final AlbumDao DAO = AlbumDao.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

	// Get Session
	HttpSession session = request.getSession();
	
	// Get the id of the user from the Session (the User object in Session was created by FOHomeController.java)	
	User userSession = (User) request.getSession().getAttribute("userLogin");	
	int userId = userSession.getId();	
	
	// Call the DAO to get the approved and pending user albums 
	UserAlbums userAlbums = DAO.getUserAlbumsDbView(userId);
		
	// Set the object to Session
	session.setAttribute("userAlbums", userAlbums);	
		
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

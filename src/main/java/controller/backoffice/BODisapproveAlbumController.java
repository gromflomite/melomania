package controller.backoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.AlbumDao;
import model.daos.implementations.AlbumDaoImpl;
import model.pojos.Feedback;

@WebServlet("/views/backoffice/album-disapprove")
public class BODisapproveAlbumController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final AlbumDao DAO = AlbumDaoImpl.getInstance();
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	boolean isDisapprovedOk = false;
	Feedback feedback = new Feedback();

	try {

	    // Get album id from view
	    String albumIdParameter = request.getParameter("id");
	    int albumId = Integer.parseInt(albumIdParameter);

	    // Call DAO
	    isDisapprovedOk = DAO.disapprove(albumId);

	    if (isDisapprovedOk) {

		feedback = new Feedback("success", "Album disapproved");

	    } else {

		feedback = new Feedback("danger", "Error: Album not disapproved");

	    }

	} catch (Exception e) {

	    LOGGER.error(e);

	} finally {

	    request.setAttribute("feedback", feedback);
	    request.getRequestDispatcher("albums").forward(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}

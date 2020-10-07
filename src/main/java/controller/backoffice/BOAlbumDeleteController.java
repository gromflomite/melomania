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


@WebServlet("/views/backoffice/album-delete")
public class BOAlbumDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final AlbumDao DAO = AlbumDaoImpl.getInstance();
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Feedback feedback = new Feedback();

	try {

	    // Get album id from view
	    String albumIdParameter = request.getParameter("id");
	    int albumId = Integer.parseInt(albumIdParameter);

	    // Call DAO
	    DAO.delete(albumId);
	    feedback = new Feedback("success", "Album deleted");

	} catch (Exception e) {

	    LOGGER.error(e);

	} finally {

	    request.setAttribute("feedback", feedback);
	    request.getRequestDispatcher("albums").forward(request, response);
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
    }

}

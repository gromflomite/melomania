package controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.AlbumDao;
import model.daos.implementations.AlbumDaoImpl;
import model.pojos.Album;

@WebServlet("/views/backoffice/albums")
public class BOAlbumsController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final static AlbumDao DAO = AlbumDaoImpl.getInstance();
    private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	ArrayList<Album> allAlbums = new ArrayList<Album>();

	try {

	    // Call DAO
	    allAlbums = DAO.getAll();

	} catch (Exception e) {

	    LOGGER.error(e);

	} finally {

	    request.setAttribute("allAlbums", allAlbums);
	    request.getRequestDispatcher("albums.jsp").forward(request, response);

	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}

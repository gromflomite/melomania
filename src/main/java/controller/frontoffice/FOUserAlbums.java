package controller.frontoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.daos.implementations.AlbumDaoImpl;
import model.pojos.Album;
import model.pojos.User;

@WebServlet("/views/frontoffice/user-albums")
public class FOUserAlbums extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final AlbumDao albumDao = AlbumDaoImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String albumsRequest = request.getParameter("albumsrequest");
	ArrayList<Album> dbRegisters = new ArrayList<Album>();

	try {

	    User userSession = (User) request.getSession().getAttribute("userLogin");
	    int idUser = userSession.getId();

	    boolean areApproved = true;

	    if ("approved".equals(albumsRequest)) { // User wants validated albums

		dbRegisters = albumDao.getAllbyUser(idUser, areApproved);

		request.setAttribute("albums_approved", dbRegisters);

		request.getRequestDispatcher("approved-albums.jsp").forward(request, response);

	    } else { // We send back pending albums

		areApproved = false;

		dbRegisters = albumDao.getAllbyUser(idUser, areApproved);

		request.setAttribute("albums_pending", dbRegisters);

		request.getRequestDispatcher("pending-albums.jsp").forward(request, response);
	    }

	} catch (Exception e) {
	    // TODO: handle exception

	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Using just doGet()
	doGet(request, response);
    }

}

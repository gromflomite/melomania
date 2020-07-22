package controller.frontoffice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.pojos.Album;

@WebServlet("/views/frontoffice/foeditalbum")
public class FOEditAlbum extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private static final AlbumDao EDITALBUMDAO = AlbumDao.getInstance(); // New AlbumDAO instance

    /**
     * Retrieve album values from DB using the album ID received as parameter from view
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Album albumValues = new Album(); // New album object to set the values from DAO and return it to view

	try {
	    
	    // TODO Implement security

	    String albumIdString = request.getParameter("id"); // Get the album ID

	    int albumId = Integer.parseInt(albumIdString); // Parse the string parameter to integer	    

	    albumValues = EDITALBUMDAO.getById(albumId); // Calling DAO method

	} catch (Exception e) {

	    // TODO: handle exception

	} finally {

	    request.setAttribute("album", albumValues);

	    request.getRequestDispatcher("edit-album.jsp").forward(request, response);
	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Using just doGet()
	doGet(request, response);
    }

}

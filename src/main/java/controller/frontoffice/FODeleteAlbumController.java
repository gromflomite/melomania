package controller.frontoffice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.daos.AlbumDao;
import model.daos.implementations.AlbumDaoImpl;
import model.pojos.Feedback;
import model.pojos.User;

/**
 * Controlador usado para eliminar un álbum de la colección de un usuario.
 *  
 * <p>
 * 
 * Solo se podrá borrar el álbum si el mismo pertenece al usuario que está lanzando el 
 * borrado. Este extremo se confirma antes de ejecutar dicho borrado.
 * 
 * @author Caronte
 * @version 1.0
 *
 */
@WebServlet("/views/frontoffice/fodeletealbum")
public class FODeleteAlbumController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final AlbumDao DELETE_ALBUM_CHECKING_USER_DAO = AlbumDaoImpl.getInstance();    
   
    /**
     * El borrado solo se podrá lanzar desde un enlace HTML, por tanto, solo usamos doGet()
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Feedback feedback = new Feedback(); // Instantiate feedback object

	try {   	    

	    String idAlbumString = request.getParameter("id"); // Get parameter ID album from view
	    int idAlbum = Integer.parseInt(idAlbumString); // Parse to int the album ID

	    // Get the id of the user from the Session (the User object in Session was
	    // created by FOHomeController.java)
	    User userSession = (User) request.getSession().getAttribute("userLogin");
	    int idUser = userSession.getId();

	    // Calling deleteCheckingIdUser() in AlbumDAO
	    DELETE_ALBUM_CHECKING_USER_DAO.deleteCheckingUser(idAlbum, idUser);
	    
	    feedback = new Feedback("success", "The album was properly deleted"); // Ok feedback

	} catch (Exception e) {

	    feedback = new Feedback("danger", e.getMessage());  // Nope feedback - Getting the text from the
	    							// exceptions created into the DAO method
	    
	    	
	} finally {
	    
	    request.setAttribute("feedback", feedback); // Add feedback to request	    
	    request.getRequestDispatcher("fohome").forward(request, response); // Go home
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// Using just doGet()
	doGet(request, response);
    }
}

package controller.frontoffice;

import model.pojos.Role;
import model.pojos.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/views/frontoffice/fouserprofile")
public class FOUserProfileController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = LogManager.getLogger("melomania-log");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            // Get the user values from Session "userLogin" (created by LoginController: UserDAO -> checkLogin())
            User userSession = (User) request.getSession().getAttribute("userLogin");

            int userId = userSession.getId();
            String userName = userSession.getName();
            String userMail = userSession.getEmail();
            String userRole = userSession.getRole().getType_role();

            // Set the attributes to send them to the view
            request.setAttribute("userId", userId);
            request.setAttribute("userName", userName);
            request.setAttribute("userMail", userMail);
            request.setAttribute("userRole", userRole);

        } catch (Exception e) {

            LOGGER.error("Problem: ", e);

        } finally {

            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            User userSession = (User) request.getSession().getAttribute("userLogin"); // To get some user values (user ID, role ID)
            User userUpdate = new User();
            Role userRole = new Role();

            // Get the entered values from view
            String userName = request.getParameter("userName");
            String userMail = request.getParameter("userMail");

            String changeUserPassword = request.getParameter("passwordChange");
            String changeUserPasswordConfirm = request.getParameter("passwordChangeConfirm");

            // Get values from Session
            int userId = userSession.getId();
            int userRoleId = userSession.getRole().getId_role();
            String userPassword = userSession.getPassword();

            // Set values to User object
            userUpdate.setId(userId);
            userUpdate.setName(userName);
            userUpdate.setEmail(userMail);
            userUpdate.setPassword(userPassword);

            // Set values to Role object
            userRole.setId_role(userRoleId);

            // Set role object to user
            userUpdate.setRole(userRole);            

        } catch (Exception e) {
            // TODO: handle exception
        } finally {

        }


        // Get the entered values in the view


    }

}

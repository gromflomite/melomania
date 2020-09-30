package listener;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.daos.GenreDao;
import model.daos.implementations.GenreDaoImpl;
import model.pojos.Genre;

/**
 * Application Lifecycle Listener implementation class HomeListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger("melomania-log");    

    public void contextDestroyed(ServletContextEvent sce) {
	
	logger.info("*** melomania finished ***");
	
	// To execute at app end	
    }

    public void contextInitialized(ServletContextEvent sce) {
	
	logger.info("*** melomania started ***");
	
	// To execute at app start		

	// Initializing a ServletContext
	// This content affects all the app. We can retrieve data from this context in
	// every .jsp or Servlet
	ServletContext appContext = sce.getServletContext();
	logger.debug("Asked for appContext: " + appContext.getContextPath());

	// Instantiating a new GenreDao
	GenreDao genreDao = GenreDaoImpl.getInstance();

	// We are retrieving all the genres from the DB and sending them to the Servlet
	// context to use it in navbar dropdown
	try {

	    ArrayList<Genre> allGenres = genreDao.getAll();
	    logger.debug("Called genreDao.getAll: " + allGenres);
	    
	    appContext.setAttribute("genres", allGenres);	    

	} catch (Exception e) {
	    
	    logger.fatal("Unable to execute genreDao.getAll().", e);	  
	}

    }

}

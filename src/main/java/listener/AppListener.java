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
 * 
 * @author gromflomite
 * 
 * Listener to get the music genres from the DB at app init. 
 *
 */
@WebListener
public class AppListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger("melomania-log");

    /**
     * 
     * Retrive all music genres from the DB and put it them into ServletContext.
     * 
     */
    public void contextInitialized(ServletContextEvent sce) { // To execute at app start

	logger.info("--- melomania started ---");

	ServletContext appContext = sce.getServletContext(); // Get the ServletContext

	logger.debug("Asked for ServletContext: " + appContext.getContextPath());

	GenreDao genreDao = GenreDaoImpl.getInstance(); // Instantiating a new GenreDao

	try {

	    ArrayList<Genre> allGenres = genreDao.getAll();
	    logger.debug("Called genreDao.getAll: " + allGenres);

	    appContext.setAttribute("genres", allGenres);

	} catch (Exception e) {

	    logger.error("Unable to execute genreDao.getAll().", e);
	}

    }

    public void contextDestroyed(ServletContextEvent sce) { // To execute at app end

	logger.info("--- melomania finished ---");

    }

}

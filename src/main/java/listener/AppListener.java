package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import model.daos.GenreDao;

/**
 * Application Lifecycle Listener implementation class HomeListener
 *
 */
@WebListener
public class AppListener implements ServletContextListener {
    
    private final static Logger LOG = Logger.getLogger(AppListener.class);    
    
    public void contextDestroyed(ServletContextEvent sce)  { 
         // To execute at app end
    }
	
    public void contextInitialized(ServletContextEvent sce)  { 
         // To execute at app start
	
	LOG.info("*** melomania app started ***");
	
	// Initiating a ServletContext
	// This content affects all the app. We can retrieve data from this context in every .jsp or Servlet
	ServletContext appContext = sce.getServletContext();
	
	// Instantiating a new GenreDao
	GenreDao genreDao = GenreDao.getInstance();
	
	// We are retrieving all the genres from the DB and sending them to the Servlet context to use it
	// in navbar dropdown
	try {	    
	    appContext.setAttribute("genres", genreDao.getAll());
	    
	} catch (Exception e) {
	    // TODO: handle exception
	}
	
	
	
	
	
	
	
	
    }
	
}

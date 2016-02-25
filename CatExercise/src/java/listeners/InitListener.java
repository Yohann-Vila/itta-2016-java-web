package listeners;

import business.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Yohann
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // créer une db user pour tester
        sce.getServletContext().setAttribute("userdbtest", createUserDBTest());
        
        // créer une db de threads pour tester
        // TODO
        
        // créer une db de commentaires pour tester
        //TODO
    }

    private IUserDAO createUserDBTest() {
        IUserDAO userdbtest = new UserDAOTest();
        userdbtest.creat(new User("toto", ""));
        userdbtest.creat(new User("titi", "password")); 
        return userdbtest;
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

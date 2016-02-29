package listeners;

import dao.UserDAOTest;
import dao.ICatThreadDAO;
import dao.CommentDAOTest;
import dao.IUserDAO;
import dao.ICommentDAO;
import dao.CatThreadDAOTest;
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
        createUserDBTest();
        createCatThreadDBTest();
        createCommentDBTest();
    }

    private IUserDAO createUserDBTest() {
        IUserDAO userDBTest = new UserDAOTest();
        
        userDBTest.insert(new User("toto", ""));
        userDBTest.insert(new User("titi", "password")); 
        userDBTest.insert(new User("tata", "password"));
        
        User admin = new User("admin", "admin");
        admin.setSeclevel(100);
        admin.setPseudo("Responsable");        
        userDBTest.insert(admin);
        
        return userDBTest;
    }
    
    private ICatThreadDAO createCatThreadDBTest() {
        ICatThreadDAO catThreadDBTest = new CatThreadDAOTest();
        
        catThreadDBTest.insert(new CatThread("toto", "Le premier fil de Toto", "http://www.cats.org.uk/uploads/images/pages/photo_latest14.jpg"));
        catThreadDBTest.insert(new CatThread("toto", "Deuxième fil de Toto", "http://www.cgdev.org/sites/default/files/cat8.jpg"));
        catThreadDBTest.insert(new CatThread("tata", "Les chats c'est nul trololol", "http://g-ecx.images-amazon.com/images/G/01/img15/pet-products/small-tiles/23695_pets_vertical_store_dogs_small_tile_8._CB312176604_.jpg"));
        for(int i=0; i<53; i++) {
            catThreadDBTest.insert(new CatThread("toto", "Toto est un spammer " + i, "http://taraflyart.com/wp-content/uploads/2011/09/MrBennet-spam-598x600.jpg"));
        }
        
        return catThreadDBTest;
    }
    
    private ICommentDAO createCommentDBTest() {
        ICommentDAO commentDBTest = new CommentDAOTest();
        
        commentDBTest.insert(new Comment(1, "titi", "First !"));
        commentDBTest.insert(new Comment(1, "anonymous", "$$$$$$ --- BUY CHEAP VIAGRA GO TO http://www.ittacademy.ch BEST QUALITY ££$$$$ -----"));
        
        return new CommentDAOTest();
    }    
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

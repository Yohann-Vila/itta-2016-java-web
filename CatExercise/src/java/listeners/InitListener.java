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
        createUserDBTest();
        createCatThreadDBTest();
        createCommentDBTest();
    }

    private IUserDAO createUserDBTest() {
        IUserDAO userdbtest = new UserDAOTest();
        
        userdbtest.creat(new User("toto", ""));
        userdbtest.creat(new User("titi", "password")); 
        userdbtest.creat(new User("tata", "password"));
        
        User admin = new User("admin", "admin");
        admin.setSeclevel(100);
        userdbtest.creat(admin);
        
        return userdbtest;
    }
    
    private ICatThreadDAO createCatThreadDBTest() {
        ICatThreadDAO catthreaddbtest = new CatThreadDAOTest();
        
        catthreaddbtest.create(new CatThread("toto", "Le premier fil de Toto", "http://www.cats.org.uk/uploads/images/pages/photo_latest14.jpg"));
        catthreaddbtest.create(new CatThread("toto", "Deuxième fil de Toto", "http://www.cgdev.org/sites/default/files/cat8.jpg"));
        catthreaddbtest.create(new CatThread("tata", "Les chats c'est nul trololol", "http://g-ecx.images-amazon.com/images/G/01/img15/pet-products/small-tiles/23695_pets_vertical_store_dogs_small_tile_8._CB312176604_.jpg"));
        for(int i=0; i<53; i++) {
            catthreaddbtest.create(new CatThread("toto", "Toto est un spammer", "http://taraflyart.com/wp-content/uploads/2011/09/MrBennet-spam-598x600.jpg"));
            
        }
        
        
        return catthreaddbtest;
    }
    
    private ICommentDAO createCommentDBTest() {
        ICommentDAO commentdbtest = new CommentDAOTest();
        
        return new CommentDAOTest();
    }    
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}

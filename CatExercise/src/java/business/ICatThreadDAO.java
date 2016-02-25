package business;

import java.util.Collection;

/**
 *
 * @author Yohann
 */
public interface ICatThreadDAO {
    Collection<CatThread> getAll();
    Collection<CatThread> findByLogin (String login);
    Collection<CatThread> findByTitle (String partialTitle);
    
    boolean Modify(CatThread catThread);
    boolean Create(CatThread catThread);
    CatThread findByID(int id);
}

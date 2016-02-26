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
    
    boolean modify(CatThread catThread);
    boolean create(CatThread catThread);
    boolean delete(CatThread catThread);

    CatThread findByID(int id);
}

package dao;

import business.CatThread;
import java.util.Collection;

/**
 *
 * @author Yohann
 */
public interface ICatThreadDAO {
    Collection<CatThread> getAll(boolean actif);
    Collection<CatThread> findByLogin (String login,boolean actif);
    Collection<CatThread> findByTitle (String partialTitle,boolean actif);
    
    boolean update(CatThread catThread);
    boolean insert(CatThread catThread);
    CatThread findByID(int id);
}

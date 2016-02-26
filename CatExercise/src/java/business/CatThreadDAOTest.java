package business;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author Yohann
 */
public class CatThreadDAOTest implements ICatThreadDAO {
    
    static Set<CatThread> catThreads = new LinkedHashSet<>();
    
    @Override
    public Collection<CatThread> getAll() {
        return Collections.unmodifiableSet(catThreads);
    }

    @Override
    public Collection<CatThread> findByLogin(String login) {
        Set<CatThread> results = new LinkedHashSet<>();
        for (CatThread thread : catThreads) {
            if (thread.getLogin().equals(login)) {
                results.add(thread);
            }
        }
        return Collections.unmodifiableSet(results);
    }

    @Override
    public Collection<CatThread> findByTitle(String partialTitle) {
        String partialTitleLowered = partialTitle.toLowerCase();
        Set<CatThread> results = new LinkedHashSet<>();
        for (CatThread thread : catThreads) {
            if (thread.getTitre().toLowerCase().contains(partialTitleLowered)) {
                results.add(thread);
            }
        }
        return Collections.unmodifiableSet(results);    }

    @Override
    public boolean modify(CatThread catThread) {
        if (catThread == null) {
            return false;
        }
        
        int id = catThread.getCatThreadId();
        CatThread oldCatThread = findByID(id);
        
        if (oldCatThread == null) {
            return false;
        }
        
        catThreads.remove(oldCatThread);
        return catThreads.add(catThread);
    }

    @Override
    public boolean create(CatThread catThread) {
        if (catThread == null) {
            return false;
        }
        return(catThreads.add(catThread));
    }
    @Override
    public boolean delete(CatThread catThread) {
        if (catThread == null) {
            return false;
        }
        return(catThreads.remove(catThread));
    }
    @Override
    public CatThread findByID(int id) {
        for (CatThread catThread : catThreads) {
            if (catThread.getCatThreadId() == id) {
                return catThread;
            }
        }
        return null;
    }
    
}

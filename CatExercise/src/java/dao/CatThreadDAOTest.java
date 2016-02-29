package dao;

import business.CatThread;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Yohann
 */
public class CatThreadDAOTest implements ICatThreadDAO {

    static AtomicInteger count = new AtomicInteger(0); // for id autogeneration
    static ArrayList<CatThread> catThreads = new ArrayList<>();

    @Override
    public Collection<CatThread> getAll(boolean actif) {
        Set<CatThread> results = new LinkedHashSet<>();
        if (actif) {
            for (CatThread thread : catThreads) {
                if (!thread.isDeleted()) {
                    results.add(thread);
                }
            }
            return results;
        } else {
            return Collections.unmodifiableCollection(catThreads);
        }
    }

    @Override
    public Collection<CatThread> findByLogin(String login, boolean actif) {
        Set<CatThread> results = new LinkedHashSet<>();
        for (CatThread thread : catThreads) {
            if (thread.getLogin().equals(login)) {
                if (!(actif && thread.isDeleted())) {
                    results.add(thread);
                }
            }
        }
        return Collections.unmodifiableSet(results);
    }

    @Override
    public Collection<CatThread> findByTitle(String partialTitle, boolean actif) {
        String partialTitleLowered = partialTitle.toLowerCase();
        Set<CatThread> results = new LinkedHashSet<>();
        for (CatThread thread : catThreads) {
            if (thread.getTitre().toLowerCase().contains(partialTitleLowered)) {
                if (!(actif && thread.isDeleted())) {
                    results.add(thread);
                }
            }
        }
        return Collections.unmodifiableSet(results);
    }

    @Override
    public boolean update(CatThread catThread) {
        if (catThread == null) {
            return false;
        }
        int index = catThreads.indexOf(catThread);

        if (index == -1) {
            return false;
        }
        
        catThreads.set(index, catThread);
        return true;
    }

    @Override
    public boolean insert(CatThread catThread) {
        if (catThread == null) {
            return false;
        }
        // set unique id
        catThread.setCatThreadId(count.incrementAndGet());
        return (catThreads.add(catThread));
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

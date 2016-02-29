package dao;

import business.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 *
 * @author Yohann
 */
public class UserDAOTest implements IUserDAO {

    static ArrayList<User> users = new ArrayList<>();
    
    public UserDAOTest() {
        
    }
    
    @Override
    public Collection<User> getAll() {
        return Collections.unmodifiableCollection(users);
    }

    @Override
    public User find(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean insert(User user) {
        if (user == null) {
            return false;
        }
        
        // check if user with same id doesn't already exists
        if (find(user.getLogin()) != null) {
            return false;
        }
        
        return users.add(user);
    }

    @Override
    public boolean update(User user) {
        if (user == null) {
            return false;
        }
        int index = users.indexOf(user);

        if (index == -1) {
            return false;
        }
        
        users.set(index, user);
        return true;
    }
    
}

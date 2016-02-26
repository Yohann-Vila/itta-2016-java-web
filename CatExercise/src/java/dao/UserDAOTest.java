package dao;

import business.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Yohann
 */
public class UserDAOTest implements IUserDAO {

    static Collection<User> users = new ArrayList<>();
    
    public UserDAOTest() {
        
    }
    
    @Override
    public Collection<User> getAll() {
        return users;
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
        String login = user.getLogin();
        User oldUser = find(login);
        
        if (oldUser == null) {
            return false;
        }
        
        users.remove(oldUser);
        return users.add(user);
        
    }
    
}

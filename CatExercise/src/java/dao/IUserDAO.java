package dao;
import business.User;
import java.util.Collection;

public interface IUserDAO {
    User find(String login);
    boolean create(User user);
    boolean update(User user);   
    Collection<User> getAll(); 
}

package business;
import java.util.Collection;

public interface IUserDAO {
    User find(String login);
    boolean creat(User user);
    boolean modify(User user);   
    Collection<User> getAll(); 
}

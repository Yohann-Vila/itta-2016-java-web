package business;
import java.util.Collection;

public interface IUserDAO {
    User find();
    boolean creat();
    boolean modify();   
    Collection<User> getAll(); 
}

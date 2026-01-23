package model.entities;
import java.util.ArrayList;

/**
 *
 * @author Lab07 Guiselle López 
 */
public interface UserOperations {

    public User searchUser(String identification);

    public User searchUser(User user);

    public ArrayList<User> sortUsers(Customer allUsers[]);

    public ArrayList<User> sortUsers(String identification, User allUsers[]);

}
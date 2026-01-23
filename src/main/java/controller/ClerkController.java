package controller;

import java.util.ArrayList;
import model.data.ClerkData;
import model.entities.Clerk;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;

/**
 *
 * @author Lab07 Guiselle López 
 */
public class ClerkController implements UserOperations {
    
    ClerkData clerkData = new ClerkData();
    
    @Override
    public User searchUser(String identification) {
        
        ArrayList<Clerk> clerks = clerkData.getAllClerks();
        // Clerk clerkToReturn = null;
        User userToReturn = null;
        for (Clerk clerk : clerks) {
            
            if (clerk.getId().equalsIgnoreCase(identification)) {
                userToReturn = clerk;
            }
        }
        
        return userToReturn;
        
    }
    
    @Override
    public User searchUser(User user) {
        
        ArrayList<Clerk> clerks = clerkData.getAllClerks();
        // Clerk clerkToReturn = null;
        User userToReturn = null;
        for (Clerk clerk : clerks) {
            
            if (clerk.getUsername().equalsIgnoreCase(user.getUsername())
                    && clerk.getPassword().equals(user.getPassword())) {
                userToReturn = clerk;
            }
        }
        
        return userToReturn;
        
    }
    
    
    
    @Override
    public ArrayList<User> sortUsers(Customer[] allUsers) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ArrayList<User> sortUsers(String identification, User[] allUsers) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

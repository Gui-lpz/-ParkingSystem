package controller;

import java.util.ArrayList;
import model.data.ClerkData;
import model.entities.Clerk;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;

public class ClerkController implements UserOperations {
    private ClerkData clerkData = new ClerkData();

    @Override
    public User searchUser(String query) {
        for (Clerk clerk : clerkData.getAllClerks()) {
            if (clerk.getId().equalsIgnoreCase(query) || clerk.getName().toLowerCase().contains(query.toLowerCase())) {
                return clerk;
            }
        }
        return null;
    }

    @Override
    public User searchUser(User user) {
        for (Clerk clerk : clerkData.getAllClerks()) {
            if (clerk.getUsername().equalsIgnoreCase(user.getUsername()) && clerk.getPassword().equals(user.getPassword())) {
                return clerk;
            }
        }
        return null;
    }

    public String insertClerk(Clerk clerk) {
        if (searchUser(clerk.getId()) == null) {
            clerkData.insertClerk(clerk);
            return "Dependiente registrado con éxito.";
        }
        return "Error: Ya existe un dependiente con esa identificación.";
    }

    public String updateClerk(Clerk clerk) {
        boolean exists = false;
        for (Clerk c : clerkData.getAllClerks()) {
            if (c.getEmployeeCode() == clerk.getEmployeeCode()) {
                exists = true;
                break;
            }
        }
        if (exists) {
            clerkData.updateClerk(clerk);
            return "Dependiente actualizado.";
        }
        return "Error: No se encontró el dependiente.";
    }

    public String deleteClerk(int employeeCode) {
        clerkData.deleteClerk(employeeCode);
        return "Dependiente eliminado.";
    }

    public ArrayList<Clerk> getAllClerks() {
        return clerkData.getAllClerks();
    }

    @Override public ArrayList<User> sortUsers(Customer[] allUsers) { return null; }
    @Override public ArrayList<User> sortUsers(String id, User[] allUsers) { return null; }
}
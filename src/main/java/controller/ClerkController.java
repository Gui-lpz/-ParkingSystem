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
    public User searchUser(String identification) {
        for (Clerk clerk : clerkData.getAllClerks()) {
            if (clerk.getId().equalsIgnoreCase(identification)) {
                return clerk;
            }
        }
        return null;
    }

    @Override
    public User searchUser(User user) {
        for (Clerk clerk : clerkData.getAllClerks()) {
            if (clerk.getUsername().equalsIgnoreCase(user.getUsername())
                    && clerk.getPassword().equals(user.getPassword())) {
                return clerk;
            }
        }
        return null;
    }

    public Clerk getClerkByCode(int code) {
        for (Clerk clerk : clerkData.getAllClerks()) {
            if (clerk.getEmployeeCode() == code) {
                return clerk;
            }
        }
        return null;
    }

    public String insertClerk(Clerk clerk) {
        if (getClerkByCode(clerk.getEmployeeCode()) == null) {
            clerkData.insertClerk(clerk);
            return "Dependiente registrado con éxito en el archivo.";
        }
        return "Error: Ya existe un dependiente con ese código.";
    }

    public String updateClerk(Clerk clerk) {
        if (getClerkByCode(clerk.getEmployeeCode()) != null) {
            clerkData.updateClerk(clerk);
            return "Datos del dependiente actualizados en el archivo.";
        }
        return "Error: No se encontró el dependiente para actualizar.";
    }


    public String deleteClerk(int employeeCode) {
        if (getClerkByCode(employeeCode) != null) {
            clerkData.deleteClerk(employeeCode);
            return "Dependiente eliminado del archivo.";
        }
        return "Error: No se encontró el dependiente.";
    }

    public ArrayList<Clerk> getAllClerks() {
        return clerkData.getAllClerks();
    }

    @Override
    public ArrayList<User> sortUsers(Customer[] allUsers) {
        throw new UnsupportedOperationException("Operación no necesaria.");
    }

    @Override
    public ArrayList<User> sortUsers(String identification, User[] allUsers) {
        throw new UnsupportedOperationException("Operación no necesaria.");
    }
}

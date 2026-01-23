package controller;

import java.util.ArrayList;
import model.data.ClerkData;
import model.entities.Clerk;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;

/**
 * @author Lab07 Guiselle López 
 */
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

  

    public String insertClerk(Clerk clerk) {
        // Validar si ya existe por ID o por Código de Empleado
        if (searchUser(clerk.getId()) == null) {
            clerkData.insertClerk(clerk);
            return "Dependiente registrado con éxito en el archivo.";
        }
        return "Error: Ya existe un dependiente con esa identificación.";
    }

    public String updateClerk(Clerk clerk) {
        // Se busca por código de empleado para actualizar sus datos
        boolean exists = false;
        for (Clerk c : clerkData.getAllClerks()) {
            if (c.getEmployeeCode() == clerk.getEmployeeCode()) {
                exists = true;
                break;
            }
        }

        if (exists) {
            clerkData.updateClerk(clerk);
            return "Datos del dependiente actualizados en el archivo.";
        }
        return "Error: No se encontró el dependiente para actualizar.";
    }

    public String deleteClerk(int employeeCode) {
        clerkData.deleteClerk(employeeCode);
        return "Dependiente eliminado del archivo.";
    }

    public ArrayList<Clerk> getAllClerks() {
        return clerkData.getAllClerks();
    }


    @Override
    public ArrayList<User> sortUsers(Customer[] allUsers) {
        throw new UnsupportedOperationException("Operación de ordenamiento no implementada.");
    }
    
    @Override
    public ArrayList<User> sortUsers(String identification, User[] allUsers) {
        throw new UnsupportedOperationException("Operación de ordenamiento no implementada.");
    }
}
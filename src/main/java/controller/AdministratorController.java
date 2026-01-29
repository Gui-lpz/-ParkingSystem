package controller;

import model.data.AdministratorData;
import model.entities.Administrator;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;
import java.util.ArrayList;

public class AdministratorController implements UserOperations {
  
    private AdministratorData administratorData = new AdministratorData();

    @Override
    public User searchUser(String identification) {
        for (Administrator admin : administratorData.getAllAdministrators()) {
            if (admin.getId().trim().equalsIgnoreCase(identification.trim())) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public User searchUser(User user) {
        // Validamos que el objeto no sea nulo
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return null;
        }

        for (Administrator admin : administratorData.getAllAdministrators()) {
            String storedUser = admin.getUsername().trim();
            String storedPass = admin.getPassword().trim();
          
            String inputUser = user.getUsername().trim();
            String inputPass = user.getPassword().trim();

           
            if (storedUser.equals(inputUser) && storedPass.equals(inputPass)) {
                return admin;
            }
        }
        return null;
    }

    public String insertAdministrator(Administrator admin) {
        if (searchUser(admin.getId()) == null) {
            administratorData.insertAdministrator(admin);
            return "Administrador registrado exitosamente.";
        } else {
            return "Error: Ya existe un administrador con esa identificación.";
        }
    }

    public String updateAdministrator(Administrator updatedAdmin) {
        if (searchUser(updatedAdmin.getId()) != null) {
            administratorData.updateAdministrator(updatedAdmin);
            return "Administrador actualizado correctamente.";
        } else {
            return "Error: No se encontró el administrador para actualizar.";
        }
    }

    public String deleteAdministrator(int adminNum) { 
        boolean found = false;
        for (Administrator admin : administratorData.getAllAdministrators()) {
            if (admin.getAdminNumber() == adminNum) {
                found = true;
                break;
            }
        }

        if (found) {
            administratorData.deleteAdministrator(String.valueOf(adminNum)); 
            return "Administrador eliminado con éxito.";
        }
        return "Error: No se encontró el administrador con ese número.";
    }

    public ArrayList<Administrator> getAllAdministrators() {
        return administratorData.getAllAdministrators();
    }

    @Override
    public ArrayList<User> sortUsers(Customer[] allUsers) {
        throw new UnsupportedOperationException("Ordenamiento no implementado aún.");
    }

    @Override
    public ArrayList<User> sortUsers(String identification, User[] allUsers) {
        throw new UnsupportedOperationException("Ordenamiento no implementado aún.");
    }
}

import java.util.ArrayList;
import model.data.AdministratorData;
import model.entities.Customer;
import model.entities.User;
import model.entities.UserOperations;

public class AdministratorController implements UserOperations {
    
    AdministratorData administratorData = new AdministratorData();

    @Override
    public User searchUser(String identification) {
        for (Administrator admin : administratorData.getAllAdministrators()) {
            if (admin.getId().equalsIgnoreCase(identification)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public User searchUser(User user) {
        for (Administrator admin : administratorData.getAllAdministrators()) {
            if (admin.getUsername().equals(user.getUsername()) && 
                admin.getPassword().equals(user.getPassword())) {
                return admin;
            }
        }
        return null;
    }
    
    // Agrega métodos CRUD para ser usados por la interfaz
    public String addAdministrator(Administrator admin) {
        if (searchUser(admin.getId()) == null) {
            administratorData.insertAdministrator(admin);
            return "Administrador creado.";
        }
        return "El administrador ya existe.";
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

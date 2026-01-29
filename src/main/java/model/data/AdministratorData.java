package model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.entities.Administrator;

public class AdministratorData {

    private final String fileName = "administrators.txt";
    private ArrayList<Administrator> administrators;

    public AdministratorData() {
        this.administrators = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            administrators.clear(); // Limpiar lista antes de cargar
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() >= 5) {
                    String id = st.nextToken();
                    String name = st.nextToken();
                    String user = st.nextToken();
                    String pass = st.nextToken();
                    int adminNumber = Integer.parseInt(st.nextToken());

                    Administrator admin = new Administrator(adminNumber, null);
                    admin.setId(id);
                    admin.setName(name);
                    admin.setUsername(user);
                    admin.setPassword(pass);
                    
                    administrators.add(admin);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar administradores: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Administrator a : administrators) {
                pw.println(a.getId() + ";" + 
                           a.getName() + ";" + 
                           a.getUsername() + ";" + 
                           a.getPassword() + ";" + 
                           a.getAdminNumber());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar administradores: " + e.getMessage());
        }
    }

    public void insertAdministrator(Administrator admin) {
        administrators.add(admin);
        saveToFile();
    }

    public ArrayList<Administrator> getAllAdministrators() {
        return administrators;
    }

    public void updateAdministrator(Administrator updatedAdmin) {
        for (int i = 0; i < administrators.size(); i++) {
            if (administrators.get(i).getAdminNumber() == updatedAdmin.getAdminNumber()) {
                administrators.set(i, updatedAdmin);
                saveToFile();
                return;
            }
        }
    }

    public void deleteAdministrator(String identifier) {
        
        administrators.removeIf(a -> a.getId().equals(identifier) || 
                                     String.valueOf(a.getAdminNumber()).equals(identifier));
        saveToFile();
    }
}
package model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.entities.Clerk;

public class ClerkData {

    private final String fileName = "clerks.txt";
    private ArrayList<Clerk> clerks;

    public ClerkData() {
        this.clerks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() >= 8) {
                    // Orden en el txt: code;schedule;age;id;name;username;password;parkingLotId
                    int code = Integer.parseInt(st.nextToken());
                    String schedule = st.nextToken();
                    int age = Integer.parseInt(st.nextToken());
                    String id = st.nextToken();
                    String name = st.nextToken();
                    String user = st.nextToken();
                    String pass = st.nextToken();
                    st.nextToken(); 

                    clerks.add(new Clerk(code, schedule, age, null, id, name, user, pass));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar dependientes: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Clerk c : clerks) {
                pw.println(c.getEmployeeCode() + ";" +
                           c.getSchedule() + ";" +
                           c.getAge() + ";" +
                           c.getId() + ";" +
                           c.getName() + ";" +
                           c.getUsername() + ";" +
                           c.getPassword() + ";" +
                           (c.getParkingLot() != null ? "PL-EXIST" : "NULL"));
            }
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public void insertClerk(Clerk clerk) {
        clerks.add(clerk);
        saveToFile();
    }

    public ArrayList<Clerk> getAllClerks() {
        return clerks;
    }

    public void updateClerk(Clerk updatedClerk) {
        for (int i = 0; i < clerks.size(); i++) {
            if (clerks.get(i).getEmployeeCode() == updatedClerk.getEmployeeCode()) {
                clerks.set(i, updatedClerk);
                saveToFile();
                return;
            }
        }
    }

    public void deleteClerk(int employeeCode) {
        clerks.removeIf(c -> c.getEmployeeCode() == employeeCode);
        saveToFile();
    }
}

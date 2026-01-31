package model.data;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.entities.Vehicle;
import model.entities.VehicleType;

public class VehicleData {
    private final String fileName = "vehicles.txt";
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public VehicleData() {
        if (vehicles.isEmpty()) {
            loadFromFile();
        }
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;
        vehicles.clear(); 

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() < 6) continue;

                Vehicle v = new Vehicle();
                v.setPlate(st.nextToken());
                v.setBrand(st.nextToken());
                v.setModel(st.nextToken());
                v.setColor(st.nextToken());

                VehicleType vt = new VehicleType();
                vt.setId(Integer.parseInt(st.nextToken()));
                vt.setFee(Float.parseFloat(st.nextToken()));
                v.setVehicleType(vt);

                if (st.hasMoreTokens()) {
                    String timeStr = st.nextToken();
                    v.setEntryTime(timeStr.equals("null") ? null : LocalDateTime.parse(timeStr));
                }
                vehicles.add(v);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Vehicle v : vehicles) {
                String entry = (v.getEntryTime() != null) ? v.getEntryTime().toString() : "null";
                pw.println(v.getPlate() + ";" + v.getBrand() + ";" + v.getModel() + ";" + 
                           v.getColor() + ";" + v.getVehicleType().getId() + ";" + 
                           v.getVehicleType().getFee() + ";" + entry);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public void insertVehicle(Vehicle v) {
        vehicles.add(v);
        saveToFile();
    }

    public void updateVehicle(String originalPlate, Vehicle v) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlate().equalsIgnoreCase(originalPlate)) {
                vehicles.set(i, v);
                break;
            }
        }
        saveToFile();
    }

    public void deleteVehicle(String plate) {
        vehicles.removeIf(v -> v.getPlate().equalsIgnoreCase(plate));
        saveToFile();
    }

    public ArrayList<Vehicle> getAllVehicles() { return vehicles; }

    public Vehicle findVehicleByPlate(String plate) {
        for (Vehicle v : vehicles) {
            if (v.getPlate().equalsIgnoreCase(plate)) return v;
        }
        return null;
    }
}

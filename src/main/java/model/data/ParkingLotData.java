package model.data;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import model.entities.ParkingLot;
import model.entities.Space;
import model.entities.Vehicle;
import model.entities.Customer;

public class ParkingLotData {

    private final String fileName = "parking_lots.txt";
    private ArrayList<ParkingLot> parkingLots;
    private static int lastId = 0;

    public ParkingLotData() {
        this.parkingLots = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ";");
                if (st.countTokens() >= 3) {
                    int id = Integer.parseInt(st.nextToken());
                    String name = st.nextToken();
                    int numSpaces = Integer.parseInt(st.nextToken());

                    ParkingLot pl = new ParkingLot();
                    pl.setId(id);
                    pl.setName(name);
                    pl.setNumberOfSpaces(numSpaces);
                    pl.setVehicles(new ArrayList<>());
                    pl.setSpaces(new Space[numSpaces]); 

                    parkingLots.add(pl);
                    if (id > lastId) lastId = id;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (ParkingLot pl : parkingLots) {
                pw.println(pl.getId() + ";" + pl.getName() + ";" + pl.getNumberOfSpaces());
            }
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }

    public ParkingLot registerParkingLot(String name, Space[] spaces) {
        lastId++;
        ParkingLot pl = new ParkingLot();
        pl.setId(lastId);
        pl.setName(name);
        pl.setSpaces(spaces);
        pl.setNumberOfSpaces(spaces.length);
        pl.setVehicles(new ArrayList<>());

        parkingLots.add(pl);
        saveToFile();
        return pl;
    }

    public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        Space[] spaces = parkingLot.getSpaces();
        boolean needsDisability = false;
        
        for (Customer c : vehicle.getCustomers()) {
            if (c.isDisabilityPresented()) {
                needsDisability = true;
                break;
            }
        }

        for (Space space : spaces) {
            if (space != null && !space.isSpaceTaken()) {
                if (space.isDisabilityAdaptation() == needsDisability) {
                    if (space.getVehicleType().getId() == vehicle.getVehicleType().getId()) {
                        space.setSpaceTaken(true);
                        vehicle.setAssignedSpace(space);
                        parkingLot.getVehicles().add(vehicle);
                        saveToFile(); 
                        return space.getId();
                    }
                }
            }
        }
        return 0;
    }

    public void removeVehicleFromParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        if (vehicle.getAssignedSpace() != null) {
            vehicle.getAssignedSpace().setSpaceTaken(false);
            parkingLot.getVehicles().remove(vehicle);
            vehicle.setAssignedSpace(null);
            saveToFile();
        }
    }

    public ParkingLot findParkingLotById(int id) {
        for (ParkingLot pl : parkingLots) {
            if (pl.getId() == id) return pl;
        }
        return null;
    }

    public ArrayList<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }

    public void deleteParkingLot(int id) {
        parkingLots.removeIf(p -> p.getId() == id);
        saveToFile();
    }
}
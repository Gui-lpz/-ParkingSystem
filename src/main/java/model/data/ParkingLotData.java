package model.data;

import java.util.ArrayList;
import model.entities.Customer;
import model.entities.ParkingLot;
import model.entities.Space;
import model.entities.Vehicle;

public class ParkingLotData {

    public ArrayList<ParkingLot> parkingLots;
    static int parkingLotId = 0;

    public ParkingLotData() {
        parkingLots = new ArrayList<>();
    }

    public ParkingLot registerParkingLot(String name, Space spaces[]) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLotId++;
        parkingLot.setId(parkingLotId);
        parkingLot.setName(name);
        parkingLot.setSpaces(spaces);
        parkingLots.add(parkingLot);

        return parkingLot;

    }

    public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        ArrayList<Vehicle> vehiclesInParkingLot = parkingLot.getVehicles();
        Space[] spaces = parkingLot.getSpaces();
        int spaceId = 0;

        boolean needsDisabilitySpace = false;
        for (Customer c : vehicle.getCustomers()) {
            if (c.isDisabilityPresented()) {
                needsDisabilitySpace = true;
                break;
            }
        }

      
        for (Space space : spaces) {
            if (!space.isSpaceTaken()) {
                if (space.isDisabilityAdaptation() == needsDisabilitySpace) {
                    if (space.getVehicleType().getId() == vehicle.getVehicleType().getId()) {
                        space.setSpaceTaken(true);          // El espacio queda ocupado
                        vehicle.setAssignedSpace(space);    // guarda el espacio

                        vehiclesInParkingLot.add(vehicle);
                        return space.getId();
                    }
                }
            }
        }

        parkingLot.setSpaces(spaces);
        parkingLot.setVehicles(vehiclesInParkingLot);

        return spaceId; // Retorna 0 si no encontró espacio disponible
    }
    public void removeVehicleFromParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        if (vehicle.getAssignedSpace() != null) {
            vehicle.getAssignedSpace().setSpaceTaken(false);

            parkingLot.getVehicles().remove(vehicle);

            vehicle.setAssignedSpace(null);
        }
    }

    public ParkingLot findParkingLotById(int id) {

        ParkingLot parkingLotToBeReturned = null;

        for (ParkingLot parkingLot : parkingLots) {

            if (parkingLot.getId() == id) {

                parkingLotToBeReturned = parkingLot;
                break;
            }
        }
        return parkingLotToBeReturned;
    }

    public ArrayList<ParkingLot> getAllParkingLots() {

        return parkingLots;

    }
    
    public void deleteParkingLot(int id) {
        parkingLots.removeIf(p -> p.getId() == id);
    }

}

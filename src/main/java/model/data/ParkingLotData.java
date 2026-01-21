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

        // 1. DETERMINAR SI EL VEHÍCULO REQUIERE ESPACIO DE DISCAPACIDAD
        // Revisamos la lista de clientes; si uno solo tiene discapacidad, el vehículo califica.
        boolean needsDisabilitySpace = false;
        for (Customer c : vehicle.getCustomers()) {
            if (c.isDisabilityPresented()) {
                needsDisabilitySpace = true;
                break;
            }
        }

        // 2. RECORRER LOS ESPACIOS PARA ENCONTRAR UNO APTO
        for (Space space : spaces) {
            // ¿El espacio está libre?
            if (!space.isSpaceTaken()) {

                // ¿El espacio coincide con la necesidad de discapacidad del vehículo?
                if (space.isDisabilityAdaptation() == needsDisabilitySpace) {

                    // ¿El tipo de vehículo del espacio coincide con el del vehículo?
                    if (space.getVehicleType().getId() == vehicle.getVehicleType().getId()) {

                        // VINCULACIÓN DOBLE:
                        space.setSpaceTaken(true);          // El espacio queda ocupado
                        vehicle.setAssignedSpace(space);    // El vehículo guarda su espacio

                        vehiclesInParkingLot.add(vehicle);
                        return space.getId();
                    }
                }
            }
        }

        parkingLot.setSpaces(spaces);
        parkingLot.setVehicles(vehiclesInParkingLot);

        return spaceId; // Retornará 0 si no encontró espacio disponible
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

}

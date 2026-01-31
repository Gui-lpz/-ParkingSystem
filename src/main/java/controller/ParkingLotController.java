package controller;

import java.util.ArrayList;
import model.entities.ParkingLot;
import model.data.ParkingLotData;
import model.entities.Space;
import model.entities.Vehicle;

public class ParkingLotController {

    private ParkingLotData parkingLotData = new ParkingLotData();

    public ParkingLot registerParkingLot(String name, Space spaces[]) {
        return parkingLotData.registerParkingLot(name, spaces);
    }

    public String updateParkingLot(int id, String name, Space spaces[]) {
        return parkingLotData.updateParkingLot(id, name, spaces);
    }

    public ParkingLot getParkingLotById(int id) {
        return parkingLotData.findParkingLotById(id);
    }

    public ParkingLot findParkingLotById(int id) {
        return parkingLotData.findParkingLotById(id);
    }

    public ArrayList<ParkingLot> getAllParkingLots() {
        return parkingLotData.getAllParkingLots();
    }

    public String deleteParkingLot(int id) {
        if (parkingLotData.findParkingLotById(id) != null) {
            parkingLotData.deleteParkingLot(id);
            return "Parqueo eliminado exitosamente.";
        }
        return "Error: El parqueo no existe.";
    }


    public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot lot) {

        for (Space s : lot.getSpaces()) {

            if (s != null && !s.isSpaceTaken()) {
              if (vehicle.getVehicleType().getId() == s.getVehicleType().getId()) {

                    boolean hasDisability = vehicle.getCustomers().get(0).isDisabilityPresented();

                    if ((hasDisability && s.isDisabilityAdaptation()) || !hasDisability) {

                        s.setSpaceTaken(true);

                        if (lot.getVehicles() == null) {
                            lot.setVehicles(new ArrayList<>());
                        }

                        lot.getVehicles().add(vehicle);

                        parkingLotData.updateParkingLot(lot.getId(), lot.getName(), lot.getSpaces());

                        return s.getId();
                    }
                }
            }
        }

        return -1;
    }
}

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

    public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        int index = parkingLotData.registerVehicleInParkingLot(vehicle, parkingLot);
        
        if (index != -1) {
            float feeFromSpace = parkingLot.getSpaces()[index].getVehicleType().getFee();
            vehicle.getVehicleType().setFee(feeFromSpace);

            int typeIdFromSpace = parkingLot.getSpaces()[index].getVehicleType().getId();
            vehicle.getVehicleType().setId(typeIdFromSpace);
        }
        return index;
    }

    public float removeVehicleFromParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        return parkingLotData.removeVehicleFromParkingLot(vehicle, parkingLot);
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
}
package controller;

import java.util.ArrayList;
import model.entities.ParkingLot;
import model.data.ParkingLotData;
import model.entities.Space;
import model.entities.Vehicle;

/**
 *
 * @author Lenovo
 */
public class ParkingLotController {

    private ParkingLotData parkingLotData = new ParkingLotData();

    public ParkingLot registerParkingLot(String name, Space spaces[]) {

        return parkingLotData.registerParkingLot(name, spaces);
    }

    public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot parkingLot) {

        return parkingLotData.registerVehicleInParkingLot(vehicle, parkingLot);

    }

    public void removeVehicleFromParkingLot(Vehicle vehicle, ParkingLot parkingLot) {

        parkingLotData.removeVehicleFromParkingLot(vehicle, parkingLot);
    }

    public ParkingLot findParkingLotById(int id) {

        return parkingLotData.findParkingLotById(id);
    }

    public ArrayList<ParkingLot> getAllParkingLots() {
        return parkingLotData.getAllParkingLots();
    }

    public String deleteParkingLot(int id) {
        if (parkingLotData.findParkingLotById(id) != null) {
            parkingLotData.deleteParkingLot(id); // Asegúrate que ParkingLotData tenga este método
            return "Parqueo eliminado exitosamente.";
        }
        return "Error: El parqueo no existe.";
    }

    public String updateParkingLotName(int id, String newName) {
        ParkingLot pl = parkingLotData.findParkingLotById(id);
        if (pl != null) {
            pl.setName(newName);
            // Suponiendo que tienes un método de actualización en Data
            return "Nombre del parqueo actualizado.";
        }
        return "Error: Parqueo no encontrado.";
    }
}

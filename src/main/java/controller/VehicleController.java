package controller;

import java.util.ArrayList;
import model.entities.Vehicle;
import model.data.VehicleData;

public class VehicleController {
    private VehicleData vehicleData = new VehicleData();

    public void insertVehicle(Vehicle vehicle) {
        vehicleData.insertVehicle(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleData.getAllVehicles();
    }

    public Vehicle findVehicleByPlate(String plate) {
        return vehicleData.findVehicleByPlate(plate);
    }

    public String deleteVehicle(String plate) {
        if (findVehicleByPlate(plate) != null) {
            vehicleData.deleteVehicle(plate);
            return "Vehículo eliminado exitosamente.";
        }
        return "El vehículo marcado no existe.";
    }

    public String updateVehicle(String plate, Vehicle vehicle) {
        if (findVehicleByPlate(plate) != null) {
            vehicleData.updateVehicle(vehicle);
            return "Vehículo actualizado con éxito.";
        }
        return "Error: El vehículo no existe.";
    }
}
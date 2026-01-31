package controller;

import java.util.ArrayList;
import model.entities.Vehicle;
import model.data.VehicleData;

public class VehicleController {
    private VehicleData vehicleData = new VehicleData();

    public void insertVehicle(Vehicle vehicle) {
        if (findVehicleByPlate(vehicle.getPlate()) == null) {
            vehicleData.insertVehicle(vehicle);
        }
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleData.getAllVehicles();
    }

    public Vehicle findVehicleByPlate(String plate) {
        if (plate == null) return null;
        return vehicleData.findVehicleByPlate(plate);
    }

    public String deleteVehicle(String plate) {
        if (findVehicleByPlate(plate) != null) {
            vehicleData.deleteVehicle(plate);
            return "Vehículo eliminado exitosamente.";
        }
        return "El vehículo marcado no existe.";
    }

    public String updateVehicle(String plateOriginal, Vehicle vehicleActualizado) {
        Vehicle existente = findVehicleByPlate(plateOriginal);
        
        if (existente != null) {
            ArrayList<Vehicle> lista = vehicleData.getAllVehicles();
            int index = lista.indexOf(existente);
            
           
            lista.set(index, vehicleActualizado);
            
            vehicleData.updateVehicle(vehicleActualizado); 
            return "Vehículo actualizado con éxito.";
        }
        return "Error: El vehículo con placa " + plateOriginal + " no existe.";
    }
}
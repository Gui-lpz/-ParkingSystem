package controller;

import java.util.ArrayList;
import model.entities.Vehicle;
import model.data.VehicleData;
import model.data.ParkingLotData;

public class VehicleController {

    private VehicleData vehicleData = new VehicleData();
    private ParkingLotData parkingData = new ParkingLotData(); 

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
            vehicleData.updateVehicle(plateOriginal, vehicleActualizado);
            return "Vehículo actualizado con éxito.";
        }
        return "Error: El vehículo con placa " + plateOriginal + " no existe.";
    }

    public float processExit(Vehicle v) {
        float monto = parkingData.calculateFee(v);
        
        vehicleData.deleteVehicle(v.getPlate());
        
        return monto;
    }
}
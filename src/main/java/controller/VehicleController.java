/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.data.VehicleData;
import model.entities.Customer;
import model.entities.Vehicle;

/**
 *
 * @author adrie
 */
public class VehicleController {
    
    private VehicleData vehicleData = new VehicleData();

    public void insertVehicle(Vehicle vehicle) {
        vehicleData.insertVehicle(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicleData.getAllVehicles();
    }

    public Vehicle findVehicle(Customer customer) {
        return vehicleData.findVehicle(customer);
    }
    
}
    

       /* String result = "";

        //Reglas de negocio:
        //Un cliente no puede tener más de un vehículo
        //Varios clientes no pueden tener un vehículo con la misma placa
        Vehicle vehicleByCustomer = vehicleData.findVehicle(vehicle.getCustomer());

        if (vehicleByCustomer != null) {

            result = "El cliente ya tiene un vehículo registrado.";
        } else {

            vehicleData.insertVehicle(vehicle);
            result = "Vehículo insertado con éxito.";
        }
        return result;
    }*/


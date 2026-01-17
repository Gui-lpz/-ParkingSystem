/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.data;

import java.util.ArrayList;
import model.entities.Customer;
import model.entities.Vehicle;

/**
 *
 * @author adrie
 */
public class VehicleData {
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void insertVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public Vehicle findVehicle(Customer customer) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getCustomer().getId().equals(customer.getId())) {
                return vehicle;
            }
        }
        return new Vehicle();
    }

}

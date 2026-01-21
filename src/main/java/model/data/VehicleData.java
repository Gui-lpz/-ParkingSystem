package model.data;

import java.util.ArrayList;
import model.entities.Customer;
import model.entities.Vehicle;


public class VehicleData {

    ArrayList<Vehicle> vehicles= new ArrayList();
    ArrayList<Customer> customers;


    public void insertVehicle(Vehicle vehicle){


        vehicles.add(vehicle);
    }

    public void insertCustomer(Customer customer){


        customers.add(customer);
    }

    public ArrayList<Vehicle> getAllVehicles(){


        return vehicles;
    }

    public Vehicle findVehicleByCurstomer(Customer customer) {
        Vehicle vehicleToReturn = null;

        for (Vehicle vehicle : vehicles) {
            for (Customer c : vehicle.getCustomers()) {
                if (c.getId().equals(customer.getId())) {
                    vehicleToReturn = vehicle;
                    return vehicleToReturn;
                }
            }
        }
        return vehicleToReturn;
    }

    public void deleteVehicle(String plate){
        Vehicle vehicleToDelete = findVehicleByPlate(plate);
        if(vehicleToDelete!=null){
            vehicles.remove(vehicleToDelete);
        }

    }

    public Vehicle findVehicleByPlate(String plate) {
        Vehicle vehicleToReturn = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPlate().equals(plate)) {
                vehicleToReturn = vehicle;
                return vehicleToReturn;
            }

        }
        return vehicleToReturn;
    }
    public void updateVehicle(Vehicle updatedVehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getPlate().equals(updatedVehicle.getPlate())) {
                vehicles.set(i, updatedVehicle);
                return;
            }
        }
    }

}


package controller;

import java.util.ArrayList;
import model.entities.Customer;
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


    public String deleteVehicle(String plate){
        if(vehicleData.findVehicleByPlate(plate)!=null){
            vehicleData.deleteVehicle(plate);
            return "Vehiculo eliminado exitosamente";

        }else{ return "El vehiculo marcado no existe";}


    }

    public String updateVehicle(String plate, Vehicle vehicle) {
        if (vehicleData.findVehicleByPlate(plate) != null) {
            vehicleData.updateVehicle(vehicle);
            return "Vehiculo actualizado con éxito.";
        } else {
            return "Error: El cliente no existe, no se puede actualizar.";
        }
    }
}






 /*public ArrayList<Vehicle> getAllVehicles() {

        return vehicleData.getAllVehicles();
    }

 public Customer searchCustomer(String id){
     Customer customerToReturn=null;

      for (Customer customer : customers) {

          if (customer.getId().equals(id)){

              customerToReturn= customer;
             break;
          }

  }
      if(customerToReturn==null){
          JOptionPane.showMessageDialog(null, "NO EXISTE ESE CLIENTE");
      }

     return customerToReturn;

 }

 /*public static void imprimirCustomers(){

    JOptionPane.showMessageDialog(null, customerController.getAllCustomers().toString());

 }

    public static void main(String[] args) {
        Customer dario=new Customer("123","DARIO",false);
        customerController.insertCustomer(dario);
        imprimirCustomers();
    }*/

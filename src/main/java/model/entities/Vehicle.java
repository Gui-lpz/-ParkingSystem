/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;
import java.util.ArrayList;
/**
 *
 * @author Lab 5 Guiselle López 
 */



public class Vehicle {
    // CAMBIO: Ahora es una lista de clientes
    private ArrayList<Customer> customers;
    private String plate;
    private String color;
    private String brand;
    private String model;
    private VehicleType vehicleType;
    private Space assignedSpace;

    public Vehicle(String plate, String color, String brand, String model, ArrayList<Customer> customers, VehicleType vehicleType) {
        this.customers = customers;
        this.plate = plate;
        this.color = color;
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {
        this.customers = new ArrayList<>(); // Inicializar para evitar NullPointerException
    }
    public Space getAssignedSpace() {
        return assignedSpace;
    }

    // Método para obtener el primer cliente (para compatibilidad con tu lógica de discapacidad)
    public Customer getPrimaryCustomer() {
        return (customers != null && !customers.isEmpty()) ? customers.get(0) : null;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }


    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    public void setAssignedSpace(Space assignedSpace) {
        this.assignedSpace = assignedSpace;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "Placa='" + plate + '\'' +
                ", Marca='" + brand + '\'' +
                ", Clientes=" + customers + '\''+
                ", Tipo=" + vehicleType.getDescription()+'\'' +
                " Posición asignada: " + (assignedSpace != null ? assignedSpace.getId() : "N/A");
    }


}

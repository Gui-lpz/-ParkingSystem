/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import java.util.ArrayList;

public class ParkingLot {

    private int id;
    private String name;
    private int numberOfSpaces;
    private ArrayList<Vehicle> vehicles;
    private Space[] spaces;

    public ParkingLot(int id, String name, int numberOfSpaces, ArrayList<Vehicle> vehicles, Space[] spaces) {
        this.id = id;
        this.name = name;
        this.numberOfSpaces = numberOfSpaces;
        this.vehicles = vehicles;
        this.spaces = spaces;
    }

    public ParkingLot() {
    }



    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numberOfSpaces
     */
    public int getNumberOfSpaces() {
        return numberOfSpaces;
    }

    /**
     * @param numberOfSpaces the numberOfSpaces to set
     */
    public void setNumberOfSpaces(int numberOfSpaces) {
        this.numberOfSpaces = numberOfSpaces;
    }

    /**
     * @return the vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * @return the spaces
     */
    public Space[] getSpaces() {
        return spaces;
    }

    /**
     * @param spaces the spaces to set
     */
    public void setSpaces(Space[] spaces) {
        this.spaces = spaces;
    }

    @Override
    public String toString() {
        return "ParkingLot{" + "id=" + id + ", name=" + name + ", numberOfSpaces=" + numberOfSpaces + ", vehicles=" + vehicles + ", spaces=" + spaces + '}';
    }


}


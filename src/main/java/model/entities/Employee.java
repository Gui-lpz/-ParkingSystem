/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;


/**
 *
 * @author Lab07 Guiselle López
 */

public interface Employee {
    public float calculateSalary(float dailySalary);   //Pueden tener cuerpos


    public ParkingLot assignWorkplace(int parkingLotId);  // ya son abstractos
}

package model.entities;


public interface Employee {
    public float calculateSalary(float dailySalary);   //Pueden tener cuerpos


    public ParkingLot assignWorkplace(int parkingLotId);  // ya son abstractos
}

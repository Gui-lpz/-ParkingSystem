/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.data;

import java.util.ArrayList;
import model.entities.ParkingLot;
import model.entities.Space;
import model.entities.Vehicle;

/**
 *
 * @author adrie
 */
public class ParkingLotData {

    public ArrayList<ParkingLot> parkingLots;
    static int parkingLotId = 0;

    public ParkingLotData() {
        parkingLots = new ArrayList<>();
    }

    public ParkingLot registerParkingLot(String name, Space spaces[]) {

        ParkingLot parkingLot = new ParkingLot();
        parkingLotId++;
        parkingLot.setId(parkingLotId);
        parkingLot.setName(name);
        parkingLot.setSpaces(spaces);
        parkingLots.add(parkingLot);

        return parkingLot;
    }

    
     public int registerVehicleInParkingLot(Vehicle vehicle, ParkingLot parkingLot) {

        ArrayList<Vehicle> vehiclesInParkingLot = parkingLot.getVehicles();
        Space spaces[] = parkingLot.getSpaces();
        int spaceId = 0;
        //recorre la lista de vehículos para ver en qué posición
        //podemos ingresar al vehículo actual
        for (Space space : spaces) {
            if (!space.isSpaceTaken()) {
                //preguntamos si el cliente presenta una capacidad particular
                //y requiere de un espacio adaptado
                if (vehicle.getCustomer().isDisabilityPresented()) {
                    if (space.isDisabilityAdaptation()) {
                        //compara el tipo de vehículo del espacio y del vehículo que se va a 
                        //estacionar (tipos: moto, automóvil, bus, etc)
                        if (space.getVehicleType().getId() == vehicle.getVehicleType().getId()) {
                            vehiclesInParkingLot.add(vehicle);
                            space.setSpaceTaken(true);
                            //este es el número del espacio que se va a retornar
                            spaceId = space.getId();
                            break;
                        }
                    }
                } else {
                    if (!space.isDisabilityAdaptation()) {
                        //compara el tipo de vehículo del espacio y del vehículo que se va a 
                        //estacionar (tipos: moto, automóvil, bus, etc)
                        if (space.getVehicleType().getId() == vehicle.getVehicleType().getId()) {
                            vehiclesInParkingLot.add(vehicle);
                            space.setSpaceTaken(true);
                            //este es el número del espacio que se va a retornar
                            spaceId = space.getId();
                            break;
                        }
                    }
                }
            }
        }

        //*************actualizamos los espacios tomados
        //y los vehículos registrados en el parqueo
        parkingLot.setSpaces(spaces);//maybe not needed                                ****tarea debugear
        parkingLot.setVehicles(vehiclesInParkingLot);//maybe not needed

        return spaceId;
    }
     
      public void removeVehicleFromParkingLot(Vehicle vehicle, ParkingLot parkingLot) {

        ArrayList<Vehicle> vehiclesInParkingLot = parkingLot.getVehicles();
        Space spaces[] = parkingLot.getSpaces();
        //recorre la lista de vehículos para ver en qué posición
        //debemos retirar al vehículo actual
        for (int i = 0; i < vehiclesInParkingLot.size(); i++) {

            if (vehiclesInParkingLot.get(i) == vehicle) {

                vehiclesInParkingLot.remove(vehicle);
                spaces[i].setSpaceTaken(false);
                break;
            }

        }
        //*************actualizamos los espacios liberados
        //y los vehículos registrados en el parqueo

        parkingLot.setSpaces(spaces);
        parkingLot.setVehicles(vehiclesInParkingLot);

    }
      
       public ParkingLot findParkingLotById(int id) {

        ParkingLot parkingLotToBeReturned = null;

        for (ParkingLot parkingLot : parkingLots) {

            if (parkingLot.getId() == id) {

                parkingLotToBeReturned = parkingLot;
                break;
            }
        }
        return parkingLotToBeReturned;
    }
       
        public ArrayList<ParkingLot> getAllParkingLots() {

        return parkingLots;

    }
        
}

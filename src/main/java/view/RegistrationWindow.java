/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package view;

import controller.CustomerController;
import controller.ParkingLotController;
import controller.VehicleController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.entities.Customer;
import model.entities.ParkingLot;
import model.entities.Space;
import model.entities.Vehicle;
import model.entities.VehicleType;

/**
 *
 * @author adrie
 */
public class RegistrationWindow {

    static VehicleController vehicleController = new VehicleController();
    static CustomerController customerController = new CustomerController();
    static ParkingLotController parkingLotController = new ParkingLotController();

    public static void main(String[] args) {

        showMainMenu();
    }

    static void showAllCustomers() {

        JOptionPane.showMessageDialog(null, customerController.getAllCustomers().toString());
    }

    static void showAllVehicles() {
        JOptionPane.showMessageDialog(null, vehicleController.getAllVehicles().toString());
    }

    static void showAllParkingLots() {
        JOptionPane.showMessageDialog(null, parkingLotController.getAllParkingLots().toString());
    }

    static void showMainMenu() {
        int choice = 1;
        while (choice != 0) {

            choice = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 0 para terminar el programa\n 1 para añadir nuevo cliente\n 2 para mostrar todos los clientes\n 3 para parquear un vehículo\n 4 para mostrar todos los vehículos del parqueo\n 5 para administrar parqueos\n 6 actualizar un cliente\n 7 para eliminar un cliente\n 8 para retirar vehículo\n 9 para ver clientes con vehículos en parqueo  "));

            switch (choice) {
                case 0 -> {

                    JOptionPane.showMessageDialog(null, "Hasta pronto!");
                }
                case 1 -> {

                    insertCustomer();
                }

                case 2 -> {
                    showAllCustomers();
                }
                case 3 -> {
                    insertVehicle();
                }
                case 4 -> {
                    showAllVehicles();
                }
                case 5 -> {
                    insertParkingLot();
                }

                case 6 -> { //tarea
                    updateCustomer();
                }

                case 7 -> { //tarea
                    deleteCustomer();
                }
                case 8 -> { //tarea2
                    exitVehicle();
                }
                case 9 -> {
                    showCurrentParkedCustomers();
                }
                default -> {
                }

            }//switch
        }//while

    }
    private static void getDataFromCustomer() {

        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");

        int option = JOptionPane.showConfirmDialog(
                null,
                "¿El cliente tiene discapacidad?",
                "Discapacidad",
                JOptionPane.YES_NO_OPTION
        );

        boolean disabilityPresented = (option == JOptionPane.YES_OPTION);

        Customer customerToInsert = new Customer(id, name, disabilityPresented);

        JOptionPane.showMessageDialog(
                null,
                customerController.insertCustomer(customerToInsert)
        );
    }

    static void getDataFromVehicle() {

        String plate = JOptionPane.showInputDialog("Ingrese la placa del vehículo");
        String color = JOptionPane.showInputDialog("Ingrese el color del vehículo");
        String brand = JOptionPane.showInputDialog("Ingrese la marca del vehículo");
        String model = JOptionPane.showInputDialog("Ingrese el modelo del vehículo");

        VehicleType vehicleType = selectVehicleType();

        if (vehicleType == null) {
            return;
        }
        Customer customer = getExistingCustomer();

        if (customer == null) {
            return;
        }

        Vehicle vehicle = new Vehicle(plate, color, brand, model, customer, vehicleType);

        vehicleController.insertVehicle(vehicle);

        ParkingLot parkingLot = selectParkingLot();
        int spaceAssigned = parkingLotController.registerVehicleInParkingLot(vehicle, parkingLot);

        JOptionPane.showMessageDialog(null, "El vehículo de: " + customer.getName() + " con placa: " + vehicle.getPlate() + " fue ingresado en el parqueo: " + parkingLot.getName());
        JOptionPane.showMessageDialog(null, "El espacio asignado en el parqueo es: " + spaceAssigned);
    }

    private static void insertParkingLot() {

        String name = JOptionPane.showInputDialog("Ingrese el nombre del parqueo");
        int numberOfSpaces = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de espacios que tiene el parqueo"));
        int numberOfSpacesWithDisabiltyAdaptation = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de espacios designados para personas con discapacidad"));

        Space[] spaces = new Space[numberOfSpaces];
        spaces = configureSpaces(spaces, numberOfSpacesWithDisabiltyAdaptation);

        ParkingLot parkingLot = parkingLotController.registerParkingLot(name, spaces);
        parkingLot.setNumberOfSpaces(numberOfSpaces);
        parkingLot.setVehicles(new ArrayList<Vehicle>());

    }

    public static Customer getExistingCustomer() {

        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");

        Customer customer = customerController.findCustomerById(id);

        if (customer == null) {
            JOptionPane.showMessageDialog(null, "El cliente no existe. Debe registrarlo primero.");
            return null;
        }

        return customer;
    }

    private static Space[] configureSpaces(Space[] spaces, int numberOfSpacesWithDisabilityAdaptation) {

        if (numberOfSpacesWithDisabilityAdaptation <= spaces.length) {
            for (int i = 0; i < numberOfSpacesWithDisabilityAdaptation; i++) {
                Space space = new Space();

                space.setId(i);
                space.setDisabilityAdaptation(true);
                space.setVehicleType(configureVehicleTypeOfSpaces(i, true));

                spaces[i] = space;
            }

            for (int i = numberOfSpacesWithDisabilityAdaptation; i < spaces.length; i++) {
                Space space = new Space();

                space.setId(i);
                space.setDisabilityAdaptation(false);
                space.setVehicleType(configureVehicleTypeOfSpaces(i, false));

                spaces[i] = space;
            }

        } else {

            JOptionPane.showMessageDialog(null, "El número de espacios seleccionados sobrepasa el máximo configurado para este parqueo");
        }

        return spaces;
    }

    private static ParkingLot selectParkingLot() {

        String parkingLotsInformation = "Lista de parqueos en el sistema\n\n";
        for (ParkingLot parkingLot : parkingLotController.getAllParkingLots()) {

            parkingLotsInformation += "Número de parqueo: " + parkingLot.getId() + " Nombre del parqueo: " + parkingLot.getName() + "\n";

        }
        int option;
        ParkingLot parkingLot;
        do {

            option = Integer.parseInt(JOptionPane.showInputDialog(parkingLotsInformation + "\n Ingrese el número de parqueo que desea consultar\n o en el que desea ingresar un vehículo"));
            parkingLot = parkingLotController.findParkingLotById(option);

        } while (parkingLot == null);

        return parkingLot;
    }

    private static VehicleType configureVehicleTypeOfSpaces(int position, boolean disabilityPresented) {

        String[] types = {"Tipos de vehículo", "1)moto", "2)liviano", "3)pesado", "4)bicicleta", "5)otro"};
        byte[] tires = {0, 2, 4, 8, 12, -1};

        String allTypes = "";
        for (String type : types) {

            allTypes += type + "\n";
        }
        VehicleType vehicleType = new VehicleType();

        byte typeNumber;
        typeNumber = Byte.parseByte(JOptionPane.showInputDialog(allTypes + "\n" + "Ingrese el número del tipo de vehículo del espacio # " + position + " "
                + "                                                                                      ¿Discapacidad?=" + (disabilityPresented ? "Sí" : "No")));
        vehicleType.setId(typeNumber);                                                                                                                                                                                                                           //Operador ternario.
        vehicleType.setDescription(types[typeNumber]);
        vehicleType.setNumberOfTires(tires[typeNumber]);

        return vehicleType;
    }

    //Tarea
    private static void updateCustomer() {

        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");

        int option = JOptionPane.showConfirmDialog(
                null,
                "¿El cliente tiene discapacidad?",
                "Discapacidad",
                JOptionPane.YES_NO_OPTION
        );

        boolean disabilityPresented = (option == JOptionPane.YES_OPTION);

        Customer updatedCustomer = new Customer(id, name, disabilityPresented);

        JOptionPane.showMessageDialog(
                null,
                customerController.updateCustomer(updatedCustomer)
        );
    }

    //Tarea
    private static void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente que desea eliminar");
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente que desea eliminar");

        Customer customerDelete = new Customer();//***
        customerDelete.setId(id);

        JOptionPane.showMessageDialog(null, customerController.deleteCustomer(customerDelete));
    }

    private static VehicleType selectVehicleType() {

        String menu
                = "Seleccione el tipo de vehículo:\n"
                + "1. Moto\n"
                + "2. Liviano\n"
                + "3. Pesado\n"
                + "4. Bicicleta";

        int option = Integer.parseInt(JOptionPane.showInputDialog(menu));

        float fee = Float.parseFloat(
                JOptionPane.showInputDialog("Ingrese el precio por hora para este tipo de vehículo")
        );

        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(option);
        vehicleType.setFee(fee);

        switch (option) {
            case 1 -> {
                vehicleType.setDescription("Moto");
                vehicleType.setNumberOfTires(2);
            }
            case 2 -> {
                vehicleType.setDescription("Liviano");
                vehicleType.setNumberOfTires(4);
            }
            case 3 -> {
                vehicleType.setDescription("Pesado");
                vehicleType.setNumberOfTires(8);
            }
            case 4 -> {
                vehicleType.setDescription("Bicicleta");
                vehicleType.setNumberOfTires(2);
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Tipo inválido");
                return null;
            }
        }

        return vehicleType;
    }

}

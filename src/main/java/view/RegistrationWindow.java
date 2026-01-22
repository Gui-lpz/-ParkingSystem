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
 * @author Guiselle
 */
public class RegistrationWindow {

    static CustomerController customerController
            = new CustomerController();
    static VehicleController vehicleController = new VehicleController();
    static ParkingLotController parkingLotController= new ParkingLotController();

    public static void main(String[] args) {

        showMainMenu();
        // newManu();
    }


    static void showMainMenu() {
        int choice = 1;
        while (choice != 0) {

            choice = Integer.parseInt(JOptionPane.showInputDialog("Ingrese\n0 para terminar el programa\n1 para añadir nuevo cliente\n2 para mostrar todos los clientes\n3  para parquear un nuevo vehiculo"
                    + "\n4 para mostrar los vehiculos\n5 Configurar parqueo\n6 para mostrar parqueo\n7 para eliminar vehiculo\n8 para actualizar vehiculo" + "\n0 para salir"));

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

                case 6 ->{ showCustomersInParkingLot();
                }

                case 7 ->{ deleteVehicle();}

                case 8 ->{ updateVehicle();}

                case 9 ->{ updateCustomer();}

                case 10 ->{ deleteCustomer();}



                default -> {
                } //Switch

            }//While
        }
        //Este comentario deberia aparecer si el comit se hizo de forma correcta, si no dario nos debe un café
          //Este comentario deberia aparecer si el comit se hizo de forma correcta, si no dario nos debe un café
    }

    private static Customer insertCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");

        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");

        String answerDisability=JOptionPane.showInputDialog("Cuenta o transporta alguna persona con discapacidad? si/no");
        boolean disabilityPresented;
        if(answerDisability.equals("si")){
            disabilityPresented=true;
        }else{
            disabilityPresented=false;
        }

        Customer customerToInsert = new Customer(id, name, disabilityPresented);

        JOptionPane.showMessageDialog(null, customerController.
                insertCustomer(customerToInsert));
        return customerToInsert;
    }

    private static void updateCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente que desea actualizar");
        String name = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente");
        String answerDisability=JOptionPane.showInputDialog("Cuenta o transporta alguna persona con discapacidad? si/no");
        boolean disabilityPresented;
        if(answerDisability.equals("si")){
            disabilityPresented=true;
        }else{
            disabilityPresented=false;
        }

        Customer updatedCustomer = new Customer(id, name, disabilityPresented);

        JOptionPane.showMessageDialog(null, customerController.updateCustomer(updatedCustomer));
    }
    private static void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente que desea eliminar");

        Customer customerDelete = new Customer();
        customerDelete.setId(id);

        JOptionPane.showMessageDialog(null, customerController.deleteCustomer(customerDelete));
    }



    private static void insertVehicle() {

        String plate = JOptionPane.showInputDialog("Ingrese el número de placa");
        String brand = JOptionPane.showInputDialog("Ingrese la marca");
        String model = JOptionPane.showInputDialog("Ingrese el modelo");
        String color = JOptionPane.showInputDialog("Ingrese el color");

        ArrayList<Customer> vehicleCustomers = new ArrayList<>();
        int numOwners = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dueños tiene este vehículo?"));

        for(int i = 0; i < numOwners; i++) {
            JOptionPane.showMessageDialog(null, "Datos del dueño #" + (i+1));
            vehicleCustomers.add(insertCustomer());
        }

        Customer customerVehicle=vehicleCustomers.get(0);
        VehicleType type = configureVehicleTypeOfSpaces(0, customerVehicle.isDisabilityPresented());

        Vehicle vehicle = new Vehicle(plate, color, brand, model, vehicleCustomers, type);
        vehicleController.insertVehicle(vehicle);

        ParkingLot parkingLot = selectParkingLot();
        int spaceAssigned = parkingLotController.registerVehicleInParkingLot(vehicle, parkingLot);

        JOptionPane.showMessageDialog(null, "El espacio asignado en el parqueo es: " + spaceAssigned);

    }

    private static void deleteVehicle() {
        String plate = JOptionPane.showInputDialog("Ingrese el número de placa del vehiculo que desea eliminar");

        JOptionPane.showMessageDialog(null, vehicleController.deleteVehicle(plate));
    }

    private static void updateVehicle() {
        String plate = JOptionPane.showInputDialog("Ingrese el número de placa del carro que desea actualizar");
        String brand = JOptionPane.showInputDialog("Ingrese la nueva marca");
        String model = JOptionPane.showInputDialog("Ingrese el nuevo modelo");
        String color = JOptionPane.showInputDialog("Ingrese el nuevo color");

        ArrayList<Customer> vehicleCustomers = new ArrayList<>();
        int numOwners = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dueños tiene este vehículo ahora?"));

        for(int i = 0; i < numOwners; i++) {
            JOptionPane.showMessageDialog(null, "Datos del dueño #" + (i+1));
            vehicleCustomers.add(insertCustomerToUpdateCar());
        }

        Customer customerVehicle=vehicleCustomers.get(0);
        VehicleType type = configureVehicleTypeOfSpaces(0, customerVehicle.isDisabilityPresented());

        Vehicle updatedVehicle = new Vehicle(plate, color, brand, model, vehicleCustomers, type);

        JOptionPane.showMessageDialog(null, vehicleController.updateVehicle(plate, updatedVehicle));

    }

    private static Customer insertCustomerToUpdateCar() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");

        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");

        String answerDisability=JOptionPane.showInputDialog("Cuenta o transporta alguna persona con discapacidad? si/no");
        boolean disabilityPresented;
        if(answerDisability.equals("si")){
            disabilityPresented=true;
        }else{
            disabilityPresented=false;
        }

        Customer customerToUpdate = new Customer(id, name, disabilityPresented);

        JOptionPane.showMessageDialog(null, customerController.updateCustomer(customerToUpdate)); //diferencia con el metodo insertCustomer
        return customerToUpdate;
    }


    static void showAllCustomers() {

        JOptionPane.showMessageDialog(null, customerController.getAllCustomers().toString());

    }

    static void showAllVehicles() {

        JOptionPane.showMessageDialog(null, vehicleController.getAllVehicles().toString());

    }

    private static void insertParkingLot() {

        int parkingLotId = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del parqueo"));
        String name = JOptionPane.showInputDialog("Ingrese el nombre del parqueo");
        int numberOfSpaces = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de espacios que tiene el parqueo"));
        int numberOfSpacesWithDisabiltyAdaptation = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de espacios designados para personas con discapacidad"));

        Space[] spaces = new Space[numberOfSpaces];
        spaces = configureSpaces(spaces, numberOfSpacesWithDisabiltyAdaptation);

        ParkingLot parkingLot = parkingLotController.registerParkingLot(name, spaces);
        parkingLot.setNumberOfSpaces(numberOfSpaces);
        parkingLot.setVehicles(new ArrayList<Vehicle>());

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




    private static VehicleType configureVehicleTypeOfSpaces(int position, boolean disabilityPresented) {

        String[] types = {"Tipos de vehículo", "1)moto", "2)liviano", "3)pesado", "4)bicicleta", "5)otro"};
        byte[] tires = {0, 2, 4, 8, 12, -1};

        String allTypes = "";
        for (String type : types) {

            allTypes += type + "\n";
        }
        VehicleType vehicleType = new VehicleType();

        byte typeNumber;
        typeNumber = Byte.parseByte(JOptionPane.showInputDialog(allTypes + "\n" + "Ingrese el número del tipo de vehículo del espacio # " + position + " ¿Discapacidad?=" + (disabilityPresented ? "Sí" : "No")));
        vehicleType.setId(typeNumber);
        vehicleType.setDescription(types[typeNumber]);
        vehicleType.setNumberOfTires(tires[typeNumber]);

        return vehicleType;
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


    private static void showCustomersInParkingLot() {
        StringBuilder report = new StringBuilder(" CLIENTES CON VEHÍCULOS EN PARQUEO \n\n");
        ArrayList<ParkingLot> allLots = parkingLotController.getAllParkingLots();

        if (allLots.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay parqueos configurados.");
            return;
        }

        for (ParkingLot lot : allLots) {
            report.append("Parqueo: ").append(lot.getName()).append("\n");
            ArrayList<Vehicle> vehicles = lot.getVehicles();

            if (vehicles.isEmpty()) {
                report.append("  (No hay vehículos en este parqueo)\n");
            } else {
                for (Vehicle v : vehicles) {
                    report.append("  > Vehículo [").append(v.getPlate()).append("]: ");

                    // Agrupa los clientes en una sola línea separados por comas
                    ArrayList<String> ownerNames = new ArrayList<>();
                    for (Customer c : v.getCustomers()) {
                        ownerNames.add(c.getName() + (c.isDisabilityPresented() ? " (D)" : ""));
                    }

                    // Une la lista con comas para que se vea: Juan, María, Pedro
                    report.append(String.join(", ", ownerNames));

                    // Muestra el espacio
                    if (v.getAssignedSpace() != null) {
                        report.append(" | Espacio: ").append(v.getAssignedSpace().getId());
                    }
                    report.append("\n");
                }
            }
            report.append("--------------------------------------\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
    }

       /* public static void newManu(){
    int firstChoice = 1;
        while (firstChoice != 0) {
            JOptionPane.showMessageDialog(null, "BIENVENIDO AL NUEVO MENÚ");
            firstChoice = Integer.parseInt(JOptionPane.showInputDialog("Ingrese\n0 para terminar el programa\n1 Acceder a las funciones de los clientes\n2 Acceder a las funciones de los vehiculos\n3 Acceder a las funciones de parqueo"));

            switch (firstChoice) {

                case 0 -> {

                    JOptionPane.showMessageDialog(null, "Hasta pronto!");
                }
                case 1 -> {

                   int secondChoice=1;
                   secondChoice = Integer.parseInt(JOptionPane.showInputDialog("Ingrese\n0 para volver al menú principal\n1 Insertar cliente\n2 Mostrar clientes"
                           + "\n3 editar cliente\n4 eliminar cliente"));
                   while (secondChoice!=0){

                       switch (secondChoice) {
                           case 1 -> {
                               insertCustomer();
                           }
                           case 2 -> {
                               showAllCustomers();
                           }
                           case 3 -> {
                               updateCustomer();
                           }
                           case 4 -> {
                               deleteCustomer();
                           }
                           default ->{
                               JOptionPane.showMessageDialog(null, "Por favor digite una opción válida");
                           }
                       }
                   }

                }

                case 2 -> {
                   int secondChoice=1;
                   secondChoice = Integer.parseInt(JOptionPane.showInputDialog("Ingrese\n0 para volver al menú principal\n1 Insertar cliente\n2 Mostrar clientes"
                           + "\n3 editar cliente\n4 eliminar cliente"));
                   while (secondChoice!=0){

                       switch (secondChoice) {
                           case 1 -> {
                               insertCustomer();
                           }
                           case 2 -> {
                               showAllCustomers();
                           }
                           case 3 -> {
                               updateCustomer();
                           }
                           case 4 -> {
                               deleteCustomer();
                           }
                           default ->{
                               JOptionPane.showMessageDialog(null, "Por favor digite una opción válida");
                           }
                       }
                   }

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

                case 6 ->{ showCustomersInParkingLot();
                }

                case 7 ->{ deleteVehicle();}

                case 8 ->{ updateVehicle();}

                case 9 ->{ updateCustomer();}

                case 10 ->{ deleteCustomer();}



                default -> {
                } //Switch

            }//While
        }
}*/
}

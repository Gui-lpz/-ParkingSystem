package view;

import controller.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.entities.*;

public class RegistrationWindow {

    // Controladores existentes
    static CustomerController customerController = new CustomerController();
    static VehicleController vehicleController = new VehicleController();
    static ParkingLotController parkingLotController = new ParkingLotController();
    static AdministratorController adminController = new AdministratorController();
    static ClerkController clerkController = new ClerkController();

    public static void main(String[] args) {
        showMainMenu();
    }

    static void showMainMenu() {
        int choice = 1;
        while (choice != 0) {
            String menu = " SISTEMA DE PARQUEO \n"
                    + "0. Salir\n"
                    + "1. Añadir cliente\n"
                    + "2. Mostrar todos los clientes\n"
                    + "3. Parquear un nuevo vehículo\n"
                    + "4. Mostrar los vehículos\n"
                    + "5. Configurar parqueo\n"
                    + "6. Mostrar ocupación de parqueos\n"
                    + "7. Eliminar vehículo\n"
                    + "8. Actualizar vehículo\n"
                    + "9. Actualizar cliente\n"
                    + "10. Eliminar cliente\n"
                    + "\n"
                    + "11. Registrar Administrador\n"
                    + "12. Mostrar Administradores\n"
                    + "13. Registrar Dependiente \n"
                    + "14. Mostrar Dependientes\n"
                    + "15. Eliminar Dependiente";

            try {
                String input = JOptionPane.showInputDialog(menu);
                if (input == null) {
                    break;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
                continue;
            }

            switch (choice) {
                case 0 ->
                    JOptionPane.showMessageDialog(null, "¡Hasta pronto!");
                case 1 ->
                    insertCustomer();
                case 2 ->
                    showAllCustomers();
                case 3 ->
                    insertVehicle();
                case 4 ->
                    showAllVehicles();
                case 5 ->
                    insertParkingLot();
                case 6 ->
                    showCustomersInParkingLot();
                case 7 ->
                    deleteVehicle();
                case 8 ->
                    updateVehicle();
                case 9 ->
                    updateCustomer();
                case 10 ->
                    deleteCustomer();

                case 11 ->
                    insertAdministrator();
                case 12 ->
                    showAllAdministrators();
                case 13 ->
                    insertClerk();
                case 14 ->
                    showAllClerks();
                case 15 ->
                    deleteClerk();

                default ->
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    public static void insertAdministrator() {
        String id = JOptionPane.showInputDialog("Cédula:");
        String name = JOptionPane.showInputDialog("Nombre:");
        String user = JOptionPane.showInputDialog("Usuario:");
        String pass = JOptionPane.showInputDialog("Contraseña:");
        int adminNum = Integer.parseInt(JOptionPane.showInputDialog("Número de Administrador:"));

        // Se crea el objeto (la clase Data se encarga de guardarlo en el txt)
        Administrator admin = new Administrator(adminNum, null, id, name, user, pass);
        JOptionPane.showMessageDialog(null, adminController.insertAdministrator(admin));
        
    }

    private static void showAllAdministrators() {
        ArrayList<Administrator> list = adminController.getAllAdministrators();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay administradores en el archivo.");
        } else {
            JOptionPane.showMessageDialog(null, list.toString());
        }
    }

    private static void insertClerk() {
        int code = Integer.parseInt(JOptionPane.showInputDialog("Código de Empleado:"));
        String schedule = JOptionPane.showInputDialog("Horario:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
        String id = JOptionPane.showInputDialog("Cédula:");
        String name = JOptionPane.showInputDialog("Nombre:");
        String user = JOptionPane.showInputDialog("Usuario:");
        String pass = JOptionPane.showInputDialog("Contraseña:");

        Clerk clerk = new Clerk(code, schedule, age, null, id, name, user, pass);
        JOptionPane.showMessageDialog(null, clerkController.insertClerk(clerk));
    }

    private static void showAllClerks() {
        ArrayList<Clerk> list = clerkController.getAllClerks();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay dependientes en el archivo.");
        } else {
            JOptionPane.showMessageDialog(null, list.toString());
        }
    }

    private static void deleteClerk() {
        int code = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Código de Empleado a eliminar:"));
        JOptionPane.showMessageDialog(null, clerkController.deleteClerk(code));
    }

    private static Customer insertCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String answerDisability = JOptionPane.showInputDialog("¿Cuenta o transporta alguna persona con discapacidad? si/no");
        boolean disabilityPresented = answerDisability.equalsIgnoreCase("si");

        Customer customerToInsert = new Customer(id, name, disabilityPresented);
        JOptionPane.showMessageDialog(null, customerController.insertCustomer(customerToInsert));
        return customerToInsert;
    }

    private static void updateCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese la cédula del cliente a actualizar");
        String name = JOptionPane.showInputDialog("Nuevo nombre:");
        String answerDisability = JOptionPane.showInputDialog("¿Discapacidad? si/no");
        boolean disability = answerDisability.equalsIgnoreCase("si");

        Customer updatedCustomer = new Customer(id, name, disability);
        JOptionPane.showMessageDialog(null, customerController.updateCustomer(updatedCustomer));
    }

    private static void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Cédula del cliente a eliminar:");
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

        for (int i = 0; i < numOwners; i++) {
            JOptionPane.showMessageDialog(null, "Datos del dueño #" + (i + 1));
            vehicleCustomers.add(insertCustomer());
        }

        Customer customerVehicle = vehicleCustomers.get(0);
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
        String plate = JOptionPane.showInputDialog("Ingrese placa a actualizar:");
        String brand = JOptionPane.showInputDialog("Nueva marca:");
        String model = JOptionPane.showInputDialog("Nuevo modelo:");
        String color = JOptionPane.showInputDialog("Nuevo color:");

        ArrayList<Customer> vehicleCustomers = new ArrayList<>();
        int numOwners = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dueños tiene ahora?"));

        for (int i = 0; i < numOwners; i++) {
            vehicleCustomers.add(insertCustomerToUpdateCar());
        }

        Customer mainOwner = vehicleCustomers.get(0);
        VehicleType type = configureVehicleTypeOfSpaces(0, mainOwner.isDisabilityPresented());

        Vehicle updatedVehicle = new Vehicle(plate, color, brand, model, vehicleCustomers, type);
        JOptionPane.showMessageDialog(null, vehicleController.updateVehicle(plate, updatedVehicle));
    }

    private static Customer insertCustomerToUpdateCar() {
        String id = JOptionPane.showInputDialog("Cédula:");
        String name = JOptionPane.showInputDialog("Nombre:");
        String answer = JOptionPane.showInputDialog("¿Discapacidad? si/no");
        boolean disability = answer.equalsIgnoreCase("si");

        Customer c = new Customer(id, name, disability);
        customerController.updateCustomer(c);
        return c;
    }

    static void showAllCustomers() {
        JOptionPane.showMessageDialog(null, customerController.getAllCustomers().toString());
    }

    static void showAllVehicles() {
        JOptionPane.showMessageDialog(null, vehicleController.getAllVehicles().toString());
    }

    private static void insertParkingLot() {
        String name = JOptionPane.showInputDialog("Nombre del parqueo:");
        int numberOfSpaces = Integer.parseInt(JOptionPane.showInputDialog("Número total de espacios:"));
        int numberOfDisabilitySpaces = Integer.parseInt(JOptionPane.showInputDialog("Espacios para discapacidad:"));

        Space[] spaces = new Space[numberOfSpaces];
        spaces = configureSpaces(spaces, numberOfDisabilitySpaces);

        ParkingLot parkingLot = parkingLotController.registerParkingLot(name, spaces);
        parkingLot.setNumberOfSpaces(numberOfSpaces);
        parkingLot.setVehicles(new ArrayList<Vehicle>());
    }

    private static Space[] configureSpaces(Space[] spaces, int disabilityCount) {
        if (disabilityCount <= spaces.length) {
            for (int i = 0; i < disabilityCount; i++) {
                Space space = new Space();
                space.setId(i);
                space.setDisabilityAdaptation(true);
                space.setVehicleType(configureVehicleTypeOfSpaces(i, true));
                spaces[i] = space;
            }
            for (int i = disabilityCount; i < spaces.length; i++) {
                Space space = new Space();
                space.setId(i);
                space.setDisabilityAdaptation(false);
                space.setVehicleType(configureVehicleTypeOfSpaces(i, false));
                spaces[i] = space;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: El número de espacios de discapacidad excede el total.");
        }
        return spaces;
    }

    private static VehicleType configureVehicleTypeOfSpaces(int position, boolean disability) {
        String[] types = {"Tipos de vehículo", "1)moto", "2)liviano", "3)pesado", "4)bicicleta", "5)otro"};
        byte[] tires = {0, 2, 4, 8, 12, -1};

        String allTypes = String.join("\n", types);
        byte typeNumber = Byte.parseByte(JOptionPane.showInputDialog(allTypes + "\nIngrese número para espacio #" + position));

        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(typeNumber);
        vehicleType.setDescription(types[typeNumber]);
        vehicleType.setNumberOfTires(tires[typeNumber]);
        return vehicleType;
    }

    private static ParkingLot selectParkingLot() {
        ArrayList<ParkingLot> list = parkingLotController.getAllParkingLots();
        if (list.isEmpty()) {
            return null;
        }

        String info = "Parqueos disponibles:\n";
        for (ParkingLot pl : list) {
            info += pl.getId() + " - " + pl.getName() + "\n";
        }

        ParkingLot found = null;
        do {
            int id = Integer.parseInt(JOptionPane.showInputDialog(info + "\nSeleccione ID:"));
            found = parkingLotController.findParkingLotById(id);
        } while (found == null);

        return found;
    }

    private static void showCustomersInParkingLot() {
        StringBuilder report = new StringBuilder(" REPORT DE OCUPACIÓN \n\n");
        ArrayList<ParkingLot> allLots = parkingLotController.getAllParkingLots();

        for (ParkingLot lot : allLots) {
            report.append("Parqueo: ").append(lot.getName()).append("\n");
            for (Vehicle v : lot.getVehicles()) {
                report.append("  > Placa: ").append(v.getPlate()).append(" Owners: ");
                for (Customer c : v.getCustomers()) {
                    report.append(c.getName()).append(" ");
                }
                report.append("\n");
            }
            report.append("--------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }
}

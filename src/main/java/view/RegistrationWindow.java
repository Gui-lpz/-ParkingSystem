package view;

import controller.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.entities.*;

public class RegistrationWindow {

    static CustomerController customerController = new CustomerController();
    static VehicleController vehicleController = new VehicleController();
    static ParkingLotController parkingLotController = new ParkingLotController();
    static AdministratorController adminController = new AdministratorController();
    static ClerkController clerkController = new ClerkController();

    public static void searchCustomerByName() {
        String query = JOptionPane.showInputDialog("Ingrese el nombre o cédula del cliente a buscar:");
        if (query != null) {
            Customer found = customerController.searchCustomer(query);
            if (found != null) {
                String info = "🆔 Cédula: " + found.getId() + "\n" +
                             "👤 Nombre: " + found.getName() + "\n" +
                             "♿ Discapacidad: " + (found.isDisabilityPresented() ? "SÍ" : "NO");
                JOptionPane.showMessageDialog(null, info, "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con ese criterio.");
            }
        }
    }

    public static Customer insertCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula del cliente");
        if (id == null) return null;
        String name = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
        String answerDisability = JOptionPane.showInputDialog("¿Cuenta o transporta alguna persona con discapacidad? si/no");
        boolean disabilityPresented = answerDisability != null && answerDisability.equalsIgnoreCase("si");

        Customer customerToInsert = new Customer(id, name, disabilityPresented);
        JOptionPane.showMessageDialog(null, customerController.insertCustomer(customerToInsert));
        return customerToInsert;
    }

    public static void showAllCustomers() {
        ArrayList<Customer> customers = customerController.getAllCustomers();
        StringBuilder report = new StringBuilder();
        report.append("======= LISTADO GENERAL DE CLIENTES =======\n\n");

        if (customers.isEmpty()) {
            report.append("  >> No hay clientes registrados en el sistema actual.\n");
        } else {
            for (Customer c : customers) {
                report.append("🆔 Cédula:         ").append(c.getId()).append("\n");
                report.append("👤 Nombre:         ").append(c.getName()).append("\n");
                String disabilityStatus = c.isDisabilityPresented() ? "SÍ presenta" : "NO presenta";
                report.append("♿ Discapacidad: ").append(disabilityStatus).append("\n");
                report.append("--------------------------------------------------\n");
            }
        }
        report.append("\nTotal de registros: ").append(customers.size());
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void updateCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese la cédula del cliente a actualizar");
        String name = JOptionPane.showInputDialog("Nuevo nombre:");
        String answerDisability = JOptionPane.showInputDialog("¿Discapacidad? si/no");
        boolean disability = answerDisability != null && answerDisability.equalsIgnoreCase("si");

        Customer updatedCustomer = new Customer(id, name, disability);
        JOptionPane.showMessageDialog(null, customerController.updateCustomer(updatedCustomer));
    }

    public static void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Cédula del cliente a eliminar:");
        Customer customerDelete = new Customer();
        customerDelete.setId(id);
        JOptionPane.showMessageDialog(null, customerController.deleteCustomer(customerDelete));
    }

    public static void insertVehicle() {
        try {
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
            VehicleType type = configureVehicleTypeOfSpacesforVehicles(0, customerVehicle.isDisabilityPresented());

            Vehicle vehicle = new Vehicle(plate, color, brand, model, vehicleCustomers, type);
            vehicleController.insertVehicle(vehicle);

            ParkingLot parkingLot = selectParkingLot();
            if (parkingLot != null) {
                int spaceAssigned = parkingLotController.registerVehicleInParkingLot(vehicle, parkingLot);
                JOptionPane.showMessageDialog(null, "El espacio asignado en el parqueo es: " + spaceAssigned);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al procesar los datos del vehículo.");
        }
    }

    public static void showAllVehicles() {
        JOptionPane.showMessageDialog(null, vehicleController.getAllVehicles().toString());
    }

    public static void updateVehicle() {
        String plate = JOptionPane.showInputDialog("Ingrese placa a actualizar:");
        String brand = JOptionPane.showInputDialog("Nueva marca:");
        String model = JOptionPane.showInputDialog("Nuevo modelo:");
        String color = JOptionPane.showInputDialog("Nuevo color:");

        ArrayList<Customer> vehicleCustomers = new ArrayList<>();
        int numOwners = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dueños tiene ahora?"));

        for (int i = 0; i < numOwners; i++) {
            vehicleCustomers.add(insertCustomer());
        }

        Customer mainOwner = vehicleCustomers.get(0);
        VehicleType type = configureVehicleTypeOfSpaces(0, mainOwner.isDisabilityPresented());

        Vehicle updatedVehicle = new Vehicle(plate, color, brand, model, vehicleCustomers, type);
        JOptionPane.showMessageDialog(null, vehicleController.updateVehicle(plate, updatedVehicle));
    }

    public static void deleteVehicle() {
        String plate = JOptionPane.showInputDialog("Ingrese placa del vehículo a eliminar:");
        JOptionPane.showMessageDialog(null, vehicleController.deleteVehicle(plate));
    }

    public static void insertAdministrator() {
        try {
            String id = JOptionPane.showInputDialog("Cédula:");
            String name = JOptionPane.showInputDialog("Nombre:");
            String user = JOptionPane.showInputDialog("Usuario:");
            String pass = JOptionPane.showInputDialog("Contraseña:");
            int adminNum = Integer.parseInt(JOptionPane.showInputDialog("Número de Administrador:"));

            Administrator admin = new Administrator(adminNum, null, id, name, user, pass);
            JOptionPane.showMessageDialog(null, adminController.insertAdministrator(admin));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: Datos inválidos.");
        }
    }

    public static void showAllAdministrators() {
        ArrayList<Administrator> admins = adminController.getAllAdministrators();
        StringBuilder report = new StringBuilder();
        report.append("======= LISTADO GENERAL DE ADMINISTRADORES =======\n\n");

        if (admins.isEmpty()) {
            report.append("  >> No hay admins registrados.\n");
        } else {
            for (Administrator a : admins) {
                report.append("🆔 Admin ID:       ").append(a.getAdminNumber()).append("\n");
                report.append("👤 Nombre:          ").append(a.getName()).append("\n");
                report.append("🔑 Usuario:         ").append(a.getUsername()).append("\n");
                report.append("--------------------------------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void updateAdministrator() {
        try {
            int adminNum = Integer.parseInt(JOptionPane.showInputDialog("Número de Admin a actualizar:"));
            String id = JOptionPane.showInputDialog("Nueva Cédula:");
            String name = JOptionPane.showInputDialog("Nuevo Nombre:");
            String user = JOptionPane.showInputDialog("Nuevo Usuario:");
            String pass = JOptionPane.showInputDialog("Nueva Contraseña:");

            Administrator updatedAdmin = new Administrator(adminNum, null, id, name, user, pass);
            JOptionPane.showMessageDialog(null, adminController.updateAdministrator(updatedAdmin));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los datos.");
        }
    }

    public static void deleteAdministrator() {
        try {
            int adminNum = Integer.parseInt(JOptionPane.showInputDialog("Número de Admin a eliminar:"));
            JOptionPane.showMessageDialog(null, adminController.deleteAdministrator(adminNum));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Número no válido.");
        }
    }

    public static void insertClerk() {
        try {
            int code = Integer.parseInt(JOptionPane.showInputDialog("Código de Empleado:"));
            String schedule = JOptionPane.showInputDialog("Horario:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Edad:"));
            String id = JOptionPane.showInputDialog("Cédula:");
            String name = JOptionPane.showInputDialog("Nombre:");
            String user = JOptionPane.showInputDialog("Usuario:");
            String pass = JOptionPane.showInputDialog("Contraseña:");

            Clerk clerk = new Clerk(code, schedule, age, null, id, name, user, pass);
            JOptionPane.showMessageDialog(null, clerkController.insertClerk(clerk));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el ingreso del dependiente.");
        }
    }

    public static void showAllClerks() {
        ArrayList<Clerk> clerks = clerkController.getAllClerks();
        StringBuilder report = new StringBuilder();
        report.append("======= LISTADO GENERAL DE CLERKS =======\n\n");

        if (clerks.isEmpty()) {
            report.append("  >> No hay clerks registrados.\n");
        } else {
            for (Clerk c : clerks) {
                report.append("🆔 Código Emp:      ").append(c.getEmployeeCode()).append("\n");
                report.append("👤 Nombre:          ").append(c.getName()).append("\n");
                report.append("⏰ Horario:         ").append(c.getSchedule()).append("\n");
                report.append("--------------------------------------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void updateClerk() {
        try {
            int code = Integer.parseInt(JOptionPane.showInputDialog("Código del Dependiente a actualizar:"));
            String schedule = JOptionPane.showInputDialog("Nuevo Horario:");
            int age = Integer.parseInt(JOptionPane.showInputDialog("Nueva Edad:"));
            String id = JOptionPane.showInputDialog("Nueva Cédula:");
            String name = JOptionPane.showInputDialog("Nuevo Nombre:");
            String user = JOptionPane.showInputDialog("Nuevo Usuario:");
            String pass = JOptionPane.showInputDialog("Nueva Contraseña:");

            Clerk updatedClerk = new Clerk(code, schedule, age, null, id, name, user, pass);
            JOptionPane.showMessageDialog(null, clerkController.updateClerk(updatedClerk));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización.");
        }
    }

    public static void deleteClerk() {
        try {
            int code = Integer.parseInt(JOptionPane.showInputDialog("Código de Empleado a eliminar:"));
            JOptionPane.showMessageDialog(null, clerkController.deleteClerk(code));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Código no válido.");
        }
    }

    public static void insertParkingLot() {
        try {
            String name = JOptionPane.showInputDialog("Nombre del parqueo:");
            int numberOfSpaces = Integer.parseInt(JOptionPane.showInputDialog("Número total de espacios:"));
            int numberOfDisabilitySpaces = Integer.parseInt(JOptionPane.showInputDialog("Espacios para discapacidad:"));

            Space[] spaces = new Space[numberOfSpaces];
            spaces = configureSpaces(spaces, numberOfDisabilitySpaces);

            ParkingLot parkingLot = parkingLotController.registerParkingLot(name, spaces);
            parkingLot.setNumberOfSpaces(numberOfSpaces);
            parkingLot.setVehicles(new ArrayList<Vehicle>());
            JOptionPane.showMessageDialog(null, "Parqueo '" + name + "' registrado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al configurar el parqueo.");
        }
    }

    public static void showAllParkingLots() {
        ArrayList<ParkingLot> lots = parkingLotController.getAllParkingLots();
        StringBuilder report = new StringBuilder("======= LISTADO DE PARQUEOS =======\n\n");
        if (lots.isEmpty()) {
            report.append("No hay parqueos registrados.");
        } else {
            for (ParkingLot p : lots) {
                report.append("🆔 ID: ").append(p.getId()).append(" | 🏢: ").append(p.getName())
                      .append(" | 🚗 Capacidad: ").append(p.getNumberOfSpaces()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void deleteParkingLot() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del parqueo a eliminar:"));
            JOptionPane.showMessageDialog(null, parkingLotController.deleteParkingLot(id));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        }
    }

    public static void showCustomersInParkingLot() {
        StringBuilder report = new StringBuilder("======= REPORTE DE OCUPACIÓN =======\n\n");
        ArrayList<ParkingLot> allLots = parkingLotController.getAllParkingLots();

        for (ParkingLot lot : allLots) {
            report.append("🏢 Parqueo: ").append(lot.getName()).append("\n");
            if (lot.getVehicles().isEmpty()) {
                report.append("    (Vacío)\n");
            } else {
                for (Vehicle v : lot.getVehicles()) {
                    report.append("  > 🚗 Placa: ").append(v.getPlate()).append(" | Dueños: ");
                    for (Customer c : v.getCustomers()) {
                        report.append(c.getName()).append(", ");
                    }
                    report.append("\n");
                }
            }
            report.append("--------------------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    private static Space[] configureSpaces(Space[] spaces, int disabilityCount) {
        if (disabilityCount <= spaces.length) {
            for (int i = 0; i < disabilityCount; i++) {
                Space space = new Space();
                space.setId(i + 1); 
                space.setDisabilityAdaptation(true);
                space.setVehicleType(configureVehicleTypeOfSpaces(i + 1, true));
                spaces[i] = space;
            }
            for (int i = disabilityCount; i < spaces.length; i++) {
                Space space = new Space();
                space.setId(i + 1);
                space.setDisabilityAdaptation(false);
                space.setVehicleType(configureVehicleTypeOfSpaces(i + 1, false));
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
        vehicleType.setType(types[typeNumber]);
        vehicleType.setNumberOfTires(tires[typeNumber]);
        return vehicleType;
    }

    private static VehicleType configureVehicleTypeOfSpacesforVehicles(int position, boolean disability) {
        String[] types = {"Tipos de vehículo", "1)moto", "2)liviano", "3)pesado", "4)bicicleta", "5)otro"};
        byte[] tires = {0, 2, 4, 8, 12, -1};
        String allTypes = String.join("\n", types);
        byte typeNumber = Byte.parseByte(JOptionPane.showInputDialog(allTypes + "\nIngrese número para el vehiculo"));
        String descriptionVehicle = JOptionPane.showInputDialog("Detalles del vehiculo");

        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(typeNumber);
        vehicleType.setType(types[typeNumber]);
        vehicleType.setNumberOfTires(tires[typeNumber]);
        vehicleType.setDescription(descriptionVehicle);
        return vehicleType;
    }

    private static ParkingLot selectParkingLot() {
        ArrayList<ParkingLot> list = parkingLotController.getAllParkingLots();
        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay parqueos registrados.");
            return null;
        }

        StringBuilder info = new StringBuilder("Parqueos disponibles:\n");
        for (ParkingLot pl : list) {
            info.append(pl.getId()).append(" - ").append(pl.getName()).append("\n");
        }

        ParkingLot found = null;
        try {
            String input = JOptionPane.showInputDialog(info + "\nSeleccione ID:");
            if (input != null) {
                int id = Integer.parseInt(input);
                found = parkingLotController.findParkingLotById(id);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selección inválida.");
        }
        return found;
    }
}
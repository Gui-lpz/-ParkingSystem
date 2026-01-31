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

    public static void insertAdministrator() {
        String id = JOptionPane.showInputDialog("Cédula:");
        String name = JOptionPane.showInputDialog("Nombre:");
        String user = JOptionPane.showInputDialog("Username:");
        String pass = JOptionPane.showInputDialog("Password:");
        int num = Integer.parseInt(JOptionPane.showInputDialog("Número de empleado:"));

        Administrator admin = new Administrator(num, null, id, name, user, pass);
        JOptionPane.showMessageDialog(null, adminController.insertAdministrator(admin));
    }

    public static void exitVehicle() {
        String plate = JOptionPane.showInputDialog("Ingrese la placa del vehículo que sale:");
        if (plate == null) return;
        JOptionPane.showMessageDialog(null, "Procesando salida para: " + plate);
    }

    public static void showAllAdministrators() {
        ArrayList<Administrator> admins = adminController.getAllAdministrators();
        StringBuilder sb = new StringBuilder(" ADMINISTRADORES\n");
        for(Administrator a : admins) {
            sb.append(a.getName()).append(" (").append(a.getUsername()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void searchCustomerByName() {
        String query = JOptionPane.showInputDialog("Ingrese el nombre o cédula del cliente a buscar:");
        if (query == null) return;
        Customer found = customerController.searchCustomer(query);
        if (found != null) {
            String info = "🆔 Cédula: " + found.getId() + "\n" +
                          "👤 Nombre: " + found.getName() + "\n" +
                          "♿ Discapacidad: " + (found.isDisabilityPresented() ? "SÍ" : "NO");
            JOptionPane.showMessageDialog(null, info, "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún cliente.");
        }
    }

    public static Customer insertCustomer() {
        String id = JOptionPane.showInputDialog("Ingrese el número de cédula");
        if (id == null) return null;
        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        String answer = JOptionPane.showInputDialog("¿Presenta discapacidad? si/no");
        boolean disability = answer != null && answer.equalsIgnoreCase("si");

        Customer customer = new Customer(id, name, disability);
        JOptionPane.showMessageDialog(null, customerController.insertCustomer(customer));
        return customer;
    }

    public static void showAllCustomers() {
        ArrayList<Customer> customers = customerController.getAllCustomers();
        StringBuilder report = new StringBuilder("    CLIENTES\n\n");
        if (customers.isEmpty()) {
            report.append("  >> No hay clientes registrados.\n");
        } else {
            for (Customer c : customers) {
                report.append("🆔 Cédula:          ").append(c.getId()).append("\n");
                report.append("👤 Nombre:          ").append(c.getName()).append("\n");
                report.append("♿ Discapacidad:   ").append(c.isDisabilityPresented() ? "SÍ presenta" : "NO presenta").append("\n");
                report.append("--------------------------------------------------\n");
            }
        }
        report.append("\nTotal de registros: ").append(customers.size());
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void updateCustomer() {
        String id = JOptionPane.showInputDialog("Cédula del cliente a actualizar:");
        if (id == null) return;
        String name = JOptionPane.showInputDialog("Nuevo nombre:");
        String dis = JOptionPane.showInputDialog("¿Discapacidad? si/no");
        Customer updated = new Customer(id, name, dis != null && dis.equalsIgnoreCase("si"));
        JOptionPane.showMessageDialog(null, customerController.updateCustomer(updated));
    }

    public static void deleteCustomer() {
        String id = JOptionPane.showInputDialog("Cédula a eliminar:");
        if (id == null) return;
        Customer c = new Customer();
        c.setId(id);
        JOptionPane.showMessageDialog(null, customerController.deleteCustomer(c));
    }

    public static void insertVehicle() {
        try {
            String plate = JOptionPane.showInputDialog("Placa:");
            String brand = JOptionPane.showInputDialog("Marca:");
            String model = JOptionPane.showInputDialog("Modelo:");
            String color = JOptionPane.showInputDialog("Color:");

            ArrayList<Customer> owners = new ArrayList<>();
            int num = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dueños?"));
            for (int i = 0; i < num; i++) {
                JOptionPane.showMessageDialog(null, "Dueño #" + (i + 1));
                owners.add(insertCustomer());
            }

            String[] options = {"1) Moto", "2) Liviano", "3) Pesado", "4) Bicicleta", "5) Otro"};
            String op = JOptionPane.showInputDialog(String.join("\n", options));

            VehicleType type = new VehicleType();
            type.setId(Integer.parseInt(op));

            Vehicle vehicle = new Vehicle(plate, color, brand, model, owners, type);

            ParkingLot lot = selectParkingLot();
            if (lot != null) {
                int space = parkingLotController.registerVehicleInParkingLot(vehicle, lot);
                if (space > 0) {
                    vehicleController.insertVehicle(vehicle);
                    JOptionPane.showMessageDialog(null, "Vehículo registrado en espacio #" + space);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay espacios disponibles para este tipo/condición.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en datos.");
        }
    }

    public static void showAllVehicles() {
        ArrayList<Vehicle> vehicles = vehicleController.getAllVehicles();
        StringBuilder report = new StringBuilder("    VEHÍCULOS  \n\n");
        for (Vehicle v : vehicles) {
            report.append("🚗 Placa: ").append(v.getPlate()).append("\n");
            report.append("🔖 Marca: ").append(v.getBrand()).append(" | ").append(v.getModel()).append("\n");
            report.append("🎨 Color: ").append(v.getColor()).append("\n");
            report.append("--------------------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void showAllParkingLots() {
        ArrayList<ParkingLot> lots = parkingLotController.getAllParkingLots();
        StringBuilder report = new StringBuilder("    PARQUEOS     \n\n");
        for (ParkingLot p : lots) {
            report.append("🆔 ID: ").append(p.getId()).append(" | 🏢: ").append(p.getName())
                  .append(" | 🚗 Capacidad: ").append(p.getNumberOfSpaces()).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void showCustomersInParkingLot() {
        StringBuilder report = new StringBuilder("        REPORTE DE OCUPACIÓN        \n\n");
        ArrayList<ParkingLot> allLots = parkingLotController.getAllParkingLots();

        for (ParkingLot lot : allLots) {
            report.append("🏢 Parqueo: ").append(lot.getName()).append("\n");
            for (Vehicle v : lot.getVehicles()) {
                report.append("  > 🚗 Placa: ").append(v.getPlate()).append("\n");
            }
            report.append("--------------------------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    private static ParkingLot selectParkingLot() {
        ArrayList<ParkingLot> list = parkingLotController.getAllParkingLots();
        if (list.isEmpty()) return null;
        StringBuilder info = new StringBuilder("Seleccione Parqueo:\n");
        for (ParkingLot pl : list)
            info.append(pl.getId()).append(" - ").append(pl.getName()).append("\n");

        String input = JOptionPane.showInputDialog(info.toString());
        return (input != null)
                ? parkingLotController.findParkingLotById(Integer.parseInt(input))
                : null;
    }
}

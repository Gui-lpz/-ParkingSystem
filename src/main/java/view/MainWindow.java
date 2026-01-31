package view;

import javax.swing.*;
import java.awt.*;
import controller.VehicleController;
import model.entities.Vehicle;

public class MainWindow extends JFrame {

    private JDesktopPane desktopPane;

    public MainWindow() {
        setTitle("SISTEMA DE GESTIÓN DE PARQUEO - Panel Principal");
        setSize(1200, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(45, 52, 54));
        add(desktopPane, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuUsers = new JMenu("Personal y Clientes");
        JMenuItem itemCustomers = new JMenuItem("Gestión de Clientes");
        itemCustomers.addActionListener(e -> openInternalFrame(new CustomerManagement()));
        
        JMenuItem itemClerks = new JMenuItem("Gestión de Dependientes");
        itemClerks.addActionListener(e -> openInternalFrame(new ClerkManagement()));

        JMenuItem itemAdmins = new JMenuItem("Gestión de Administradores");
        itemAdmins.addActionListener(e -> openInternalFrame(new AdministratorManagement()));

        menuUsers.add(itemCustomers);
        menuUsers.add(itemClerks);
        menuUsers.add(itemAdmins);

        JMenu menuParking = new JMenu("Operaciones Parqueo");

        JMenuItem itemEntry = new JMenuItem("Registrar Entrada (Vehículo)");
        itemEntry.addActionListener(e -> {
            VehicleWindow vw = new VehicleWindow(null, "ENTRY");
            openInternalFrame(vw);
        });

        JMenuItem itemExit = new JMenuItem("Registrar Salida (Cobro)");
        itemExit.addActionListener(e -> {
            String plate = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo que sale:");
            if (plate != null && !plate.trim().isEmpty()) {
                VehicleController controller = new VehicleController();
                Vehicle v = controller.findVehicleByPlate(plate);
                if (v != null) {
                    VehicleWindow vw = new VehicleWindow(null, "EXIT");
                    vw.loadVehicle(v);
                    openInternalFrame(vw);
                } else {
                    JOptionPane.showMessageDialog(this, "Vehículo no encontrado en el sistema.");
                }
            }
        });

        JMenuItem itemVehicles = new JMenuItem("Gestión de Vehículos");
        itemVehicles.addActionListener(e -> openInternalFrame(new VehicleManagement()));

        JMenuItem itemLots = new JMenuItem("Gestión de Parqueos");
        itemLots.addActionListener(e -> openInternalFrame(new ParkingLotManagement()));

        menuParking.add(itemEntry);
        menuParking.add(itemExit);
        menuParking.add(new JSeparator());
        menuParking.add(itemVehicles);
        menuParking.add(itemLots);

        menuBar.add(menuUsers);
        menuBar.add(menuParking);

        return menuBar;
    }

    public void openInternalFrame(JInternalFrame frame) {
        for (JInternalFrame f : desktopPane.getAllFrames()) {
            if (f.getClass().equals(frame.getClass()) && !(frame instanceof VehicleWindow)) {
                try {
                    f.setSelected(true);
                    f.toFront();
                    return;
                } catch (java.beans.PropertyVetoException e) {}
            }
        }
        desktopPane.add(frame);
        frame.setVisible(true);
    }
}
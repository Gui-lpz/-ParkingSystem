package view;

import javax.swing.*;
import java.awt.*;

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
        itemEntry.addActionListener(e -> RegistrationWindow.insertVehicle());

        JMenuItem itemExit = new JMenuItem("Registrar Salida (Cobro)");
        itemExit.addActionListener(e -> RegistrationWindow.exitVehicle());

        JMenuItem itemVehicles = new JMenuItem("Listado de Vehículos");
        itemVehicles.addActionListener(e -> openInternalFrame(new VehicleManagement()));

        JMenuItem itemLots = new JMenuItem("Configurar Parqueos");
        itemLots.addActionListener(e -> openInternalFrame(new ParkingLotManagement()));

        JMenuItem itemOcupacion = new JMenuItem("Ver Ocupación Actual");
        itemOcupacion.addActionListener(e -> RegistrationWindow.showCustomersInParkingLot());

        menuParking.add(itemEntry);
        menuParking.add(itemExit);
        menuParking.add(new JSeparator());
        menuParking.add(itemVehicles);
        menuParking.add(itemLots);
        menuParking.add(itemOcupacion);

        menuBar.add(menuUsers);
        menuBar.add(menuParking);

        return menuBar;
    }

    public void openInternalFrame(JInternalFrame frame) {
        for (JInternalFrame f : desktopPane.getAllFrames()) {
            if (f.getClass().equals(frame.getClass())) {
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
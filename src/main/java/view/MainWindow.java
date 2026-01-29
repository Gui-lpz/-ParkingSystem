package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JDesktopPane desktopPane;

    public MainWindow() {
        setTitle("SISTEMA DE GESTIÓN DE PARQUEO - Panel Principal");
        setSize(1100, 800);
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
        JMenuItem itemAdmins = new JMenuItem("Gestión de Administradores");
        JMenuItem itemClerks = new JMenuItem("Gestión de Dependientes");

        itemCustomers.addActionListener(e -> {
            RegistrationWindow.showAllCustomers();
        });

        itemAdmins.addActionListener(e -> {
            RegistrationWindow.showAllAdministrators();
        });

        itemClerks.addActionListener(e -> {
            RegistrationWindow.showAllClerks();
        });

        menuUsers.add(itemCustomers);
        menuUsers.add(itemAdmins);
        menuUsers.add(itemClerks);

        JMenu menuParking = new JMenu("Operaciones Parqueo");
        JMenuItem itemVehicles = new JMenuItem("Gestión de Vehículos");
        JMenuItem itemLots = new JMenuItem("Configurar Parqueos");
        JMenuItem itemOcupacion = new JMenuItem("Ver Ocupación");

        itemVehicles.addActionListener(e -> RegistrationWindow.insertVehicle());
        itemLots.addActionListener(e -> RegistrationWindow.insertParkingLot());
        itemOcupacion.addActionListener(e -> RegistrationWindow.showCustomersInParkingLot());

        menuParking.add(itemVehicles);
        menuParking.add(itemLots);
        menuParking.add(itemOcupacion);

        menuBar.add(menuUsers);
        menuBar.add(menuParking);

        return menuBar;
    }

    public void openInternalFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        try {
            frame.setSelected(true);
            frame.setVisible(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
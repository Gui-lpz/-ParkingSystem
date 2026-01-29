
package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JDesktopPane desktopPane;

    public MainWindow() {
        // Configuración básica de la ventana principal
        setTitle("SISTEMA DE GESTIÓN DE PARQUEO - Panel Principal");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(45, 52, 54)); // Un gris oscuro moderno
        add(desktopPane, BorderLayout.CENTER);

      
        setJMenuBar(createMenuBar());
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuUsers = new JMenu("Personal y Clientes");
        JMenuItem itemCustomers = new JMenuItem("Gestión de Clientes");
        JMenuItem itemAdmins = new JMenuItem("Gestión de Administradores");
        JMenuItem itemClerks = new JMenuItem("Gestión de Dependientes");

        JMenu menuParking = new JMenu("Operaciones Parqueo");
        JMenuItem itemVehicles = new JMenuItem("Gestión de Vehículos");
        JMenuItem itemLots = new JMenuItem("Configurar Parqueos");

        
        itemCustomers.addActionListener(e -> {
            CustomerManagement win = new CustomerManagement();
            openInternalFrame(win);
        });

        itemAdmins.addActionListener(e -> {
            AdministratorManagement win = new AdministratorManagement();
            openInternalFrame(win);
        });

        itemClerks.addActionListener(e -> {
            ClerkManagement win = new ClerkManagement();
            openInternalFrame(win);
        });

    
        menuUsers.add(itemCustomers);
        menuUsers.add(itemAdmins);
        menuUsers.add(itemClerks);

        menuParking.add(itemVehicles);
        menuParking.add(itemLots);

        menuBar.add(menuUsers);
        menuBar.add(menuParking);

        return menuBar;
    }

    private void openInternalFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
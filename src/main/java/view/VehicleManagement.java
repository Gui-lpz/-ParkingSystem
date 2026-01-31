package view;

import controller.VehicleController;
import model.entities.Vehicle;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VehicleManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit, buttonAdd;
    JPanel panelVehicles;
    JTable tableVehicles;
    DefaultTableModel modelDataTable;
    

    final String[] headings = {"Placa", "Marca", "Modelo", "Color", "Tipo", "Entrada"};

    VehicleController vehicleController;
    VehicleWindow vehicleWindow;

    public VehicleManagement() {
        super("Gestión de Vehículos", false, true, false, true);
        this.setSize(700, 500);
        this.setLocation(50, 50);
        
        vehicleController = new VehicleController();
        vehicleWindow = new VehicleWindow();

        panelVehicles = new JPanel();
        panelVehicles.setLayout(null);
        panelVehicles.setBackground(Color.WHITE);
        this.add(panelVehicles);


        tableVehicles = new JTable();
        JScrollPane scrollBar = new JScrollPane(tableVehicles);
        scrollBar.setBounds(25, 50, 630, 250);
        panelVehicles.add(scrollBar);

        createTable();

        // Botón Agregar
        buttonAdd = new JButton("Nuevo");
        buttonAdd.setBounds(100, 350, 100, 30);
        panelVehicles.add(buttonAdd);
        buttonAdd.addActionListener(e -> {
            JDesktopPane desktop = getDesktopPane();
            vehicleWindow = new VehicleWindow(); 
            desktop.add(vehicleWindow);
            vehicleWindow.toFront();
        });

   
        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(250, 350, 100, 30);
        panelVehicles.add(buttonDelete);
        buttonDelete.addActionListener(e -> {
            int row = tableVehicles.getSelectedRow();
            if(row != -1) {
                String plate = tableVehicles.getValueAt(row, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(null, "¿Borrar vehículo " + plate + "?");
                if(confirm == JOptionPane.YES_OPTION) {
                    vehicleController.deleteVehicle(plate);
                    createTable();
                }
            }
        });
    }

    public void createTable() {
        ArrayList<Vehicle> list = vehicleController.getAllVehicles();
        String[][] matrix = new String[list.size()][6];
        
        for (int i = 0; i < list.size(); i++) {
            Vehicle v = list.get(i);
            matrix[i][0] = v.getPlate();
            matrix[i][1] = v.getBrand();
            matrix[i][2] = v.getModel();
            matrix[i][3] = v.getColor();
            matrix[i][4] = v.getVehicleType() != null ? v.getVehicleType().getType() : "N/A";
            matrix[i][5] = v.getEntryTime() != null ? v.getEntryTime().toString() : "No ha entrado";
        }

        modelDataTable = new DefaultTableModel(matrix, headings);
        tableVehicles.setModel(modelDataTable);
    }
}
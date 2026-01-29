package view;

import controller.ParkingLotController;
import model.entities.Space;
import model.entities.VehicleType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ParkingLotWindow extends JInternalFrame implements ActionListener {
    private JPanel panel;
    public JTextField txtName, txtTotalSpaces, txtDisabilitySpaces, txtMotorcycleSpaces;
    public JButton btnSave, btnCancel;
    private ParkingLotController parkingController;

    public ParkingLotWindow() {
        super("Configurar Nuevo Parqueo", false, true, false, true);
        parkingController = new ParkingLotController();
        initComponents();
    }

    private void initComponents() {
        setSize(450, 350);
        setLocation(50, 50);
        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        JLabel lblName = new JLabel("Nombre:");
        lblName.setBounds(30, 30, 150, 25);
        txtName = new JTextField();
        txtName.setBounds(200, 30, 180, 25);

        JLabel lblTotal = new JLabel("Espacios Regulares:");
        lblTotal.setBounds(30, 70, 150, 25);
        txtTotalSpaces = new JTextField("10"); 
        txtTotalSpaces.setBounds(200, 70, 180, 25);

        JLabel lblDis = new JLabel("Espacios Discapacidad:");
        lblDis.setBounds(30, 110, 150, 25);
        txtDisabilitySpaces = new JTextField("2");
        txtDisabilitySpaces.setBounds(200, 110, 180, 25);

        JLabel lblMoto = new JLabel("Espacios Motos:");
        lblMoto.setBounds(30, 150, 150, 25);
        txtMotorcycleSpaces = new JTextField("5");
        txtMotorcycleSpaces.setBounds(200, 150, 180, 25);

        btnSave = new JButton("Crear Parqueo");
        btnSave.setBounds(80, 220, 130, 30);
        btnSave.addActionListener(this);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(220, 220, 100, 30);
        btnCancel.addActionListener(e -> dispose());

        panel.add(lblName); panel.add(txtName);
        panel.add(lblTotal); panel.add(txtTotalSpaces);
        panel.add(lblDis); panel.add(txtDisabilitySpaces);
        panel.add(lblMoto); panel.add(txtMotorcycleSpaces);
        panel.add(btnSave); panel.add(btnCancel);
        
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            try {
                String name = txtName.getText();
                int reg = Integer.parseInt(txtTotalSpaces.getText());
                int dis = Integer.parseInt(txtDisabilitySpaces.getText());
                int mot = Integer.parseInt(txtMotorcycleSpaces.getText());

                int total = reg + dis + mot;
                Space[] spaces = new Space[total];
                int count = 0;

                VehicleType tipoLiviano = new VehicleType(1, "Liviano", (byte)0, 0.0f, "Std");
                VehicleType tipoMoto = new VehicleType(3, "Moto", (byte)0, 0.0f, "Std");

                
                for (int i = 0; i < dis; i++) {
                    spaces[count] = new Space(count + 1, true, false, tipoLiviano);
                    count++;
                }
                
                for (int i = 0; i < mot; i++) {
                    spaces[count] = new Space(count + 1, false, false, tipoMoto);
                    count++;
                }
                
                for (int i = 0; i < reg; i++) {
                    spaces[count] = new Space(count + 1, false, false, tipoLiviano);
                    count++;
                }

                parkingController.registerParkingLot(name, spaces);
                JOptionPane.showMessageDialog(this, "Parqueo '" + name + "' creado con éxito.");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese solo números en las cantidades.");
            }
        }
    }
}
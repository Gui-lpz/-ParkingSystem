package view;

import controller.VehicleController;
import model.entities.Vehicle;
import model.entities.VehicleType;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VehicleWindow extends JInternalFrame {

    JPanel panel;
    JTextField txtPlate, txtBrand, txtModel, txtColor;
    JButton btnSave, btnCancel;
    VehicleController controller;

    public VehicleWindow() {
        super("Formulario de Vehículo", false, true, false, true);
        this.setSize(400, 400);
        this.setVisible(true);
        controller = new VehicleController();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        JLabel l1 = new JLabel("Placa:"); l1.setBounds(30, 20, 80, 25); panel.add(l1);
        txtPlate = new JTextField(); txtPlate.setBounds(120, 20, 200, 25); panel.add(txtPlate);

        JLabel l2 = new JLabel("Marca:"); l2.setBounds(30, 60, 80, 25); panel.add(l2);
        txtBrand = new JTextField(); txtBrand.setBounds(120, 60, 200, 25); panel.add(txtBrand);

        JLabel l3 = new JLabel("Modelo:"); l3.setBounds(30, 100, 80, 25); panel.add(l3);
        txtModel = new JTextField(); txtModel.setBounds(120, 100, 200, 25); panel.add(txtModel);

        JLabel l4 = new JLabel("Color:"); l4.setBounds(30, 140, 80, 25); panel.add(l4);
        txtColor = new JTextField(); txtColor.setBounds(120, 140, 200, 25); panel.add(txtColor);

        btnSave = new JButton("Guardar");
        btnSave.setBounds(80, 250, 100, 30);
        panel.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vehicle v = new Vehicle();
                v.setPlate(txtPlate.getText());
                v.setBrand(txtBrand.getText());
                v.setModel(txtModel.getText());
                v.setColor(txtColor.getText());
                
                controller.insertVehicle(v);
                JOptionPane.showMessageDialog(null, "Vehículo Guardado");
                dispose(); 
            }
        });
    }
}
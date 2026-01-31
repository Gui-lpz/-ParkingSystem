package view;

import controller.VehicleController;
import model.entities.Vehicle;
import model.entities.VehicleType;
import javax.swing.*;
import java.awt.Color;

public class VehicleWindow extends JInternalFrame {

    JPanel panel;
    public JTextField txtPlate, txtBrand, txtModel, txtColor;
    public JComboBox<String> cmbType;
    JButton btnSave;

    VehicleController controller;

    private boolean isEdit = false;
    private String originalPlate = null;
    private VehicleManagement parent;

    public VehicleWindow(VehicleManagement parent) {
        super("Formulario de Vehículo", false, true, false, true);
        this.parent = parent;

        this.setSize(400, 420);
        controller = new VehicleController();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        JLabel l1 = new JLabel("Placa:");
        l1.setBounds(30, 20, 80, 25);
        panel.add(l1);

        txtPlate = new JTextField();
        txtPlate.setBounds(120, 20, 200, 25);
        panel.add(txtPlate);

        JLabel l2 = new JLabel("Marca:");
        l2.setBounds(30, 60, 80, 25);
        panel.add(l2);

        txtBrand = new JTextField();
        txtBrand.setBounds(120, 60, 200, 25);
        panel.add(txtBrand);

        JLabel l3 = new JLabel("Modelo:");
        l3.setBounds(30, 100, 80, 25);
        panel.add(l3);

        txtModel = new JTextField();
        txtModel.setBounds(120, 100, 200, 25);
        panel.add(txtModel);

        JLabel l4 = new JLabel("Color:");
        l4.setBounds(30, 140, 80, 25);
        panel.add(l4);

        txtColor = new JTextField();
        txtColor.setBounds(120, 140, 200, 25);
        panel.add(txtColor);

        JLabel l5 = new JLabel("Tipo:");
        l5.setBounds(30, 180, 80, 25);
        panel.add(l5);

        cmbType = new JComboBox<>(new String[]{
            "Liviano", "Pesado", "Moto", "Bicicleta", "Otros"
        });
        cmbType.setBounds(120, 180, 200, 25);
        panel.add(cmbType);

        btnSave = new JButton("Guardar");
        btnSave.setBounds(120, 250, 120, 30);
        panel.add(btnSave);

        btnSave.addActionListener(e -> saveVehicle());
    }

    public void loadVehicle(Vehicle v) {
        txtPlate.setText(v.getPlate());
        txtBrand.setText(v.getBrand());
        txtModel.setText(v.getModel());
        txtColor.setText(v.getColor());
        cmbType.setSelectedItem(v.getVehicleType().getType());

        originalPlate = v.getPlate();
        txtPlate.setEditable(false);
        isEdit = true;
    }

    private void saveVehicle() {

        Vehicle v = new Vehicle();
        v.setPlate(txtPlate.getText());
        v.setBrand(txtBrand.getText());
        v.setModel(txtModel.getText());
        v.setColor(txtColor.getText());

        VehicleType vt = new VehicleType();
        vt.setType(cmbType.getSelectedItem().toString());
        v.setVehicleType(vt);

        if (isEdit) {
            controller.updateVehicle(originalPlate, v);
            JOptionPane.showMessageDialog(this, "Vehículo Modificado");
        } else {
            controller.insertVehicle(v);
            JOptionPane.showMessageDialog(this, "Vehículo Guardado");
        }

        parent.createTable(); 
        dispose();
    }
}

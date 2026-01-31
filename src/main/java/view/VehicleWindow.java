package view;

import controller.VehicleController;
import model.entities.Vehicle;
import model.entities.VehicleType;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class VehicleWindow extends JInternalFrame {

    JPanel panel;
    JTextField txtPlate, txtBrand, txtModel, txtColor;
    JComboBox<String> cmbType;
    JButton btnSave;

    VehicleController controller;
    VehicleManagement parent;

    private String mode;
    private String originalPlate;

    public VehicleWindow(VehicleManagement parent, String mode) {
        super("Formulario de Vehículo", false, true, false, true);

        this.parent = parent;
        this.mode = mode;

        setSize(400, 420);
        setLocation(150, 50);
        controller = new VehicleController();

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        add(panel);

        addLabel("Placa:", 20);
        txtPlate = addText(20);

        addLabel("Marca:", 60);
        txtBrand = addText(60);

        addLabel("Modelo:", 100);
        txtModel = addText(100);

        addLabel("Color:", 140);
        txtColor = addText(140);

        addLabel("Tipo:", 180);
        cmbType = new JComboBox<>(new String[]{
                "Liviano", "Pesado", "Moto", "Bicicleta", "Otros"
        });
        cmbType.setBounds(120, 180, 200, 25);
        panel.add(cmbType);

        btnSave = new JButton();
        btnSave.setBounds(120, 250, 150, 30);
        panel.add(btnSave);

        configureByMode();
        btnSave.addActionListener(e -> saveVehicle());
    }

    private void configureByMode() {
        switch (mode) {
            case "NEW":
                btnSave.setText("Guardar Vehículo");
                break;
            case "EDIT":
                btnSave.setText("Modificar Vehículo");
                txtPlate.setEditable(false);
                break;
            case "ENTRY":
                btnSave.setText("Registrar Entrada");
                break;
            case "EXIT":
                btnSave.setText("Registrar Salida");

                txtPlate.setEditable(false);
                txtBrand.setEditable(false);
                txtModel.setEditable(false);
                txtColor.setEditable(false);
                cmbType.setEnabled(false);
                break;
        }
    }

    public void loadVehicle(Vehicle v) {
        if (v == null) return;
        txtPlate.setText(v.getPlate());
        txtBrand.setText(v.getBrand());
        txtModel.setText(v.getModel());
        txtColor.setText(v.getColor());

        if (v.getVehicleType() != null) {
            cmbType.setSelectedIndex(Math.max(0, v.getVehicleType().getId() - 1));
        }
        originalPlate = v.getPlate();
    }

    private void saveVehicle() {
        String plate = txtPlate.getText().trim();
        if (plate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La placa es obligatoria");
            return;
        }

        if (mode.equals("EXIT")) {
            Vehicle v = controller.findVehicleByPlate(plate);
            if (v != null) {
                // Llamamos al proceso de salida que calcula el monto y elimina el vehículo
                float monto = controller.processExit(v); 
                JOptionPane.showMessageDialog(this, "SALIDA REGISTRADA\nVehículo: " + plate + "\nTotal Cobrado: ₡" + monto);
            } else {
                JOptionPane.showMessageDialog(this, "Error: El vehículo no existe en el registro.");
            }
            
            if (parent != null) {
                parent.createTable();
            }
            dispose();
            return;
        }

        Vehicle v = new Vehicle();
        v.setPlate(plate);
        v.setBrand(txtBrand.getText());
        v.setModel(txtModel.getText());
        v.setColor(txtColor.getText());

        VehicleType vt = new VehicleType();
        vt.setId(cmbType.getSelectedIndex() + 1);
        vt.setType(cmbType.getSelectedItem().toString());

        float[] tarifas = {1000f, 2000f, 500f, 200f, 800f};
        vt.setFee(tarifas[cmbType.getSelectedIndex()]);
        v.setVehicleType(vt);

        try {
            if (mode.equals("EDIT")) {
                controller.updateVehicle(originalPlate, v);
                JOptionPane.showMessageDialog(this, "Vehículo Actualizado");
            } else if (mode.equals("ENTRY")) {
                v.setEntryTime(LocalDateTime.now());
                controller.insertVehicle(v);
                JOptionPane.showMessageDialog(this, "Entrada registrada exitosamente.");
            } else {
                controller.insertVehicle(v);
                JOptionPane.showMessageDialog(this, "Vehículo Guardado");
            }

            if (parent != null) {
                parent.createTable();
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar: " + ex.getMessage());
        }
    }

    private void addLabel(String text, int y) {
        JLabel l = new JLabel(text);
        l.setBounds(30, y, 80, 25);
        panel.add(l);
    }

    private JTextField addText(int y) {
        JTextField t = new JTextField();
        t.setBounds(120, y, 200, 25);
        panel.add(t);
        return t;
    }
}
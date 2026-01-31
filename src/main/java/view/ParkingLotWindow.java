package view;

import controller.ParkingLotController;
import model.entities.Space;
import model.entities.VehicleType;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ParkingLotWindow extends JInternalFrame implements ActionListener {
    private JPanel panel;
    public JTextField txtName, txtRegSpaces, txtDisabilitySpaces, txtMotoSpaces, txtHeavySpaces, txtBikeSpaces, txtOtherSpaces;

    public JTextField txtFeeLiviano, txtFeeMoto, txtFeeHeavy, txtFeeBike, txtFeeOther; 
    public JButton btnSave, btnCancel;
    private ParkingLotController parkingController;

    public ParkingLotWindow() {
        super("Configurar Nuevo Parqueo", false, true, false, true);
        parkingController = new ParkingLotController();
        initComponents();
    }

    private void initComponents() {
        setSize(500, 550);
        setLocation(50, 50);

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(450, 650)); 

        addLabelAndField("Nombre Parqueo:", txtName = new JTextField(), 20);
        
        addLabelAndField("Espacios Regulares:", txtRegSpaces = new JTextField("10"), 60);
        addLabelAndField("Espacios Discapacidad:", txtDisabilitySpaces = new JTextField("2"), 95);
        addLabelAndField("Espacios Motos:", txtMotoSpaces = new JTextField("5"), 130);
        addLabelAndField("Espacios Pesados:", txtHeavySpaces = new JTextField("2"), 165);
        addLabelAndField("Espacios Bicicletas:", txtBikeSpaces = new JTextField("10"), 200);
        addLabelAndField("Espacios Otros:", txtOtherSpaces = new JTextField("0"), 235);

        addLabelAndField("Tarifa Liviano ($):", txtFeeLiviano = new JTextField("1000"), 285);
        addLabelAndField("Tarifa Moto ($):", txtFeeMoto = new JTextField("500"), 320);
        addLabelAndField("Tarifa Pesado ($):", txtFeeHeavy = new JTextField("2500"), 355);
        addLabelAndField("Tarifa Bicicleta ($):", txtFeeBike = new JTextField("200"), 390);
        addLabelAndField("Tarifa Otros ($):", txtFeeOther = new JTextField("1000"), 425);

        btnSave = new JButton("Crear Parqueo");
        btnSave.setBounds(80, 500, 140, 30);
        btnSave.addActionListener(this);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(240, 500, 100, 30);
        btnCancel.addActionListener(e -> dispose());

        panel.add(btnSave); 
        panel.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(null);
        this.getContentPane().add(scrollPane);
    }

    private void addLabelAndField(String labelText, JTextField textField, int y) {
        JLabel lbl = new JLabel(labelText);
        lbl.setBounds(30, y, 160, 25);
        textField.setBounds(200, y, 180, 25);
        panel.add(lbl);
        panel.add(textField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            try {
                VehicleType vLiviano = new VehicleType(1, "Liviano", (byte)4, Float.parseFloat(txtFeeLiviano.getText()), "L");
                VehicleType vMoto    = new VehicleType(2, "Moto", (byte)2, Float.parseFloat(txtFeeMoto.getText()), "M");
                VehicleType vPesado  = new VehicleType(3, "Pesado", (byte)6, Float.parseFloat(txtFeeHeavy.getText()), "P");
                VehicleType vBici    = new VehicleType(4, "Bicicleta", (byte)2, Float.parseFloat(txtFeeBike.getText()), "B");
                VehicleType vOther   = new VehicleType(5, "Otro", (byte)4, Float.parseFloat(txtFeeOther.getText()), "O");

                int cDis = Integer.parseInt(txtDisabilitySpaces.getText());
                int cReg = Integer.parseInt(txtRegSpaces.getText());
                int cMot = Integer.parseInt(txtMotoSpaces.getText());
                int cPes = Integer.parseInt(txtHeavySpaces.getText());
                int cBic = Integer.parseInt(txtBikeSpaces.getText());
                int cOth = Integer.parseInt(txtOtherSpaces.getText());

                int total = cDis + cReg + cMot + cPes + cBic + cOth;
                Space[] spaces = new Space[total];
                int count = 0;

                for (int i = 0; i < cDis; i++) spaces[count++] = new Space(count + 1, true, false, vLiviano);
                for (int i = 0; i < cMot; i++) spaces[count++] = new Space(count + 1, false, false, vMoto);
                for (int i = 0; i < cPes; i++) spaces[count++] = new Space(count + 1, false, false, vPesado);
                for (int i = 0; i < cBic; i++) spaces[count++] = new Space(count + 1, false, false, vBici);
                for (int i = 0; i < cOth; i++) spaces[count++] = new Space(count + 1, false, false, vOther);
                for (int i = 0; i < cReg; i++) spaces[count++] = new Space(count + 1, false, false, vLiviano);

                parkingController.registerParkingLot(txtName.getText(), spaces);
                JOptionPane.showMessageDialog(this, "Parqueo '" + txtName.getText() + "' creado con éxito.");
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error: Ingrese solo números en espacios y tarifas.");
            }
        }
    }
}
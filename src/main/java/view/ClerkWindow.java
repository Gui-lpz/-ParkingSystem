package view;

import controller.ClerkController;
import model.entities.Clerk;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClerkWindow extends JInternalFrame implements ActionListener {

    JPanel panel;
    JLabel lblCode, lblName, lblSchedule, lblId, lblAge, lblUser, lblPass;

    public JTextField txtCode, txtName, txtSchedule, txtId, txtAge, txtUser;
    public JPasswordField txtPass;

    public JButton btnSave, btnCancel;

    ClerkController clerkController;

    public ClerkWindow() {
        super("Insertar Dependiente", false, true, false, true);
        this.setSize(420, 520);
        this.setLocation(215, 20);

        clerkController = new ClerkController();

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

   
        lblCode = new JLabel("Código:");
        lblCode.setBounds(50, 30, 100, 30);
        panel.add(lblCode);

        txtCode = new JTextField();
        txtCode.setBounds(150, 30, 200, 25);
        panel.add(txtCode);


        lblName = new JLabel("Nombre:");
        lblName.setBounds(50, 70, 100, 30);
        panel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 70, 200, 25);
        panel.add(txtName);

        // Horario
        lblSchedule = new JLabel("Horario:");
        lblSchedule.setBounds(50, 110, 100, 30);
        panel.add(lblSchedule);

        txtSchedule = new JTextField();
        txtSchedule.setBounds(150, 110, 200, 25);
        panel.add(txtSchedule);


        lblId = new JLabel("Cédula:");
        lblId.setBounds(50, 150, 100, 30);
        panel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(150, 150, 200, 25);
        panel.add(txtId);


        lblAge = new JLabel("Edad:");
        lblAge.setBounds(50, 190, 100, 30);
        panel.add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(150, 190, 200, 25);
        panel.add(txtAge);


        lblUser = new JLabel("Usuario:");
        lblUser.setBounds(50, 230, 100, 30);
        panel.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(150, 230, 200, 25);
        panel.add(txtUser);

 
        lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(50, 270, 100, 30);
        panel.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(150, 270, 200, 25);
        panel.add(txtPass);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(80, 360, 100, 30);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(220, 360, 100, 30);
        btnCancel.addActionListener(e -> dispose());
        panel.add(btnCancel);
    }

    public void clearForm() {
        txtCode.setText("");
        txtName.setText("");
        txtSchedule.setText("");
        txtId.setText("");
        txtAge.setText("");
        txtUser.setText("");
        txtPass.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Clerk clerk = new Clerk(
                Integer.parseInt(txtCode.getText()),
                txtSchedule.getText(),
                Integer.parseInt(txtAge.getText()),
                null,
                txtId.getText(),
                txtName.getText(),
                txtUser.getText(),
                new String(txtPass.getPassword())
        );

        String resp;

        if (btnSave.getText().equalsIgnoreCase("Modificar")) {
            resp = clerkController.updateClerk(clerk);
        } else {
            resp = clerkController.insertClerk(clerk);
        }

        JOptionPane.showMessageDialog(this, resp);
        dispose();
    }
}

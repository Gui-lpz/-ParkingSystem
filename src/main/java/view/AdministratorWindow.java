package view;

import controller.AdministratorController;
import model.entities.Administrator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdministratorWindow extends JInternalFrame implements ActionListener {

    JPanel panel;
    JLabel lblId, lblName, lblUser, lblPass, lblAdminNum;
    public JTextField txtId, txtName, txtUser, txtAdminNum;
    public JPasswordField txtPass;
    JButton btnSave, btnCancel;
    AdministratorController adminController;

    public AdministratorWindow() {
        super("Insertar Administrador", false, true, false, true);
        this.setSize(420, 450);
        this.setLocation(215, 50);
        adminController = new AdministratorController();

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        lblId = new JLabel("Cédula:");
        lblId.setBounds(50, 10, 100, 50);
        lblId.setForeground(Color.BLUE);
        txtId = new JTextField();
        txtId.setBounds(150, 25, 200, 25);
        panel.add(lblId); panel.add(txtId);

        lblName = new JLabel("Nombre:");
        lblName.setBounds(50, 70, 100, 50);
        lblName.setForeground(Color.BLUE);
        txtName = new JTextField();
        txtName.setBounds(150, 85, 200, 25);
        panel.add(lblName); panel.add(txtName);

        lblUser = new JLabel("Usuario:");
        lblUser.setBounds(50, 130, 100, 50);
        lblUser.setForeground(Color.BLUE);
        txtUser = new JTextField();
        txtUser.setBounds(150, 145, 200, 25);
        panel.add(lblUser); panel.add(txtUser);

        lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(50, 190, 100, 50);
        lblPass.setForeground(Color.BLUE);
        txtPass = new JPasswordField();
        txtPass.setBounds(150, 205, 200, 25);
        panel.add(lblPass); panel.add(txtPass);

        lblAdminNum = new JLabel("N° Admin:");
        lblAdminNum.setBounds(50, 250, 100, 50);
        lblAdminNum.setForeground(Color.BLUE);
        txtAdminNum = new JTextField();
        txtAdminNum.setBounds(150, 265, 200, 25);
        panel.add(lblAdminNum); panel.add(txtAdminNum);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(90, 350, 100, 25);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(220, 350, 100, 25);
        btnCancel.addActionListener(e -> setVisible(false));
        panel.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Insertar")) {
            try {
                int adminNum = Integer.parseInt(txtAdminNum.getText());
                Administrator admin = new Administrator(adminNum, null, txtId.getText(), txtName.getText(), txtUser.getText(), new String(txtPass.getPassword()));
                String resp = adminController.insertAdministrator(admin);
                JOptionPane.showMessageDialog(this, resp);
                if(resp.contains("éxito")) dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos.");
            }
        }
    }
}
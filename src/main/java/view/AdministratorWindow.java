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
    public JButton btnSave, btnCancel;
    AdministratorController adminController;

    public AdministratorWindow() {
        super("Insertar Administrador", false, true, false, true);
        this.setSize(420, 450);
        this.setLocation(215, 50);
        adminController = new AdministratorController();

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        lblId = new JLabel("Cédula:");
        lblId.setBounds(50, 30, 100, 30);
        lblId.setForeground(Color.BLUE);
        txtId = new JTextField();
        txtId.setBounds(150, 30, 200, 25);
        panel.add(lblId); panel.add(txtId);

        lblName = new JLabel("Nombre:");
        lblName.setBounds(50, 80, 100, 30);
        lblName.setForeground(Color.BLUE);
        txtName = new JTextField();
        txtName.setBounds(150, 80, 200, 25);
        panel.add(lblName); panel.add(txtName);

        lblUser = new JLabel("Usuario:");
        lblUser.setBounds(50, 130, 100, 30);
        lblUser.setForeground(Color.BLUE);
        txtUser = new JTextField();
        txtUser.setBounds(150, 130, 200, 25);
        panel.add(lblUser); panel.add(txtUser);

        lblPass = new JLabel("Contraseña:");
        lblPass.setBounds(50, 180, 100, 30);
        lblPass.setForeground(Color.BLUE);
        txtPass = new JPasswordField();
        txtPass.setBounds(150, 180, 200, 25);
        panel.add(lblPass); panel.add(txtPass);

        lblAdminNum = new JLabel("N° Admin:");
        lblAdminNum.setBounds(50, 230, 100, 30);
        lblAdminNum.setForeground(Color.BLUE);
        txtAdminNum = new JTextField();
        txtAdminNum.setBounds(150, 230, 200, 25);
        panel.add(lblAdminNum); panel.add(txtAdminNum);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(90, 320, 100, 30);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(220, 320, 100, 30);
        btnCancel.addActionListener(e -> {
            int op = JOptionPane.showConfirmDialog(this, "¿Desea salir sin guardar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if(op == 0) dispose();
        });
        panel.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            try {
                int adminNum = Integer.parseInt(txtAdminNum.getText());
                String pass = new String(txtPass.getPassword());

                Administrator admin = new Administrator(adminNum, null, txtId.getText(), txtName.getText(), txtUser.getText(), pass);
                
                String resp;
                if (btnSave.getText().equalsIgnoreCase("Modificar")) {
                    resp = adminController.updateAdministrator(admin);
                } else {
                    resp = adminController.insertAdministrator(admin);
                }

                JOptionPane.showMessageDialog(this, resp);

                if (resp.toLowerCase().contains("éxito")) {
                    // Regresar a la tabla de gestión
                    JDesktopPane desktop = this.getDesktopPane();
                    this.dispose();
                    AdministratorManagement m = new AdministratorManagement();
                    desktop.add(m);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El Número de Admin debe ser numérico.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al procesar: " + ex.getMessage());
            }
        }
    }
}
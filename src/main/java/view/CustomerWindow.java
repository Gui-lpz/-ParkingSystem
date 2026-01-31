package view;

import controller.CustomerController;
import model.entities.Customer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomerWindow extends JInternalFrame implements ActionListener {

    JPanel panel;
    JLabel labelId, labelName, labelDisability;

    public JTextField txtId, txtName;
    public JCheckBox chkDisability; 
    public JButton btnSave, btnCancel;
    
    CustomerController customerController;

    public CustomerWindow() {
        super("Insertar Cliente", false, true, false, true);
        this.setSize(420, 350);
        this.setLocation(215, 50);
        this.customerController = new CustomerController();

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        labelId = new JLabel("Cédula:");
        labelId.setBounds(50, 30, 100, 30);
        labelId.setForeground(Color.BLUE);
        panel.add(labelId);

        txtId = new JTextField();
        txtId.setBounds(150, 30, 200, 25);
        panel.add(txtId);

        labelName = new JLabel("Nombre:");
        labelName.setBounds(50, 80, 100, 30);
        labelName.setForeground(Color.BLUE);
        panel.add(labelName);

        txtName = new JTextField();
        txtName.setBounds(150, 80, 200, 25);
        panel.add(txtName);

        labelDisability = new JLabel("Discapacidad:");
        labelDisability.setBounds(50, 130, 100, 30);
        labelDisability.setForeground(Color.BLUE);
        panel.add(labelDisability);

        chkDisability = new JCheckBox("Presenta");
        chkDisability.setBounds(150, 130, 100, 25);
        chkDisability.setBackground(Color.WHITE);
        panel.add(chkDisability);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(80, 220, 100, 30);
        btnSave.addActionListener(this);
        panel.add(btnSave);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(220, 220, 100, 30);
        btnCancel.addActionListener(e -> {
             int op = JOptionPane.showConfirmDialog(this, "¿Desea cerrar?", "Confirmar", JOptionPane.YES_NO_OPTION);
             if(op == 0) dispose();
        });
        panel.add(btnCancel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String id = txtId.getText();
            String name = txtName.getText();
            boolean dis = chkDisability.isSelected();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos");
            } else {
                Customer c = new Customer(id, name, dis);
                String resp;

                if (btnSave.getText().equalsIgnoreCase("Modificar")) {
                    resp = customerController.updateCustomer(c);
                } else {
                    resp = customerController.insertCustomer(c);
                }

                JOptionPane.showMessageDialog(this, resp);
                
                if(resp.toLowerCase().contains("éxito")) {
                    if (btnSave.getText().equalsIgnoreCase("Modificar")) {
                        JDesktopPane desktop = this.getDesktopPane();
                        this.dispose();
                        CustomerManagement m = new CustomerManagement();
                        desktop.add(m);
                    } else {
                        dispose();
                    }
                }
            }
        }
    }
}
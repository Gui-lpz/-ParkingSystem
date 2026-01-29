package view;

import model.entities.Customer;
import controller.CustomerController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CustomerWindow extends JInternalFrame implements ActionListener {
    private JPanel panel;
    private JLabel lblId, lblName, lblDisability;
    public JTextField txtId, txtName;
    private JCheckBox chkDisability;
    public JButton btnSave, btnCancel;
    private CustomerController customerController;

    public CustomerWindow() {
        super("Insertar Cliente", false, true, false, true);
        customerController = new CustomerController();
        initComponents();
    }

    private void initComponents() {
        setSize(400, 300);
        setLocation(50, 50);
        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);

        lblId = new JLabel("Cédula:");
        lblId.setBounds(30, 30, 80, 25);
        lblId.setForeground(Color.BLUE);
        txtId = new JTextField();
        txtId.setBounds(120, 30, 200, 25);

        lblName = new JLabel("Nombre:");
        lblName.setBounds(30, 80, 80, 25);
        lblName.setForeground(Color.BLUE);
        txtName = new JTextField();
        txtName.setBounds(120, 80, 200, 25);

        lblDisability = new JLabel("Discapacidad:");
        lblDisability.setBounds(30, 130, 100, 25);
        lblDisability.setForeground(Color.BLUE);
        chkDisability = new JCheckBox("Presenta");
        chkDisability.setBounds(120, 130, 100, 25);
        chkDisability.setBackground(Color.WHITE);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(80, 200, 100, 30);
        btnSave.addActionListener(this);

        btnCancel = new JButton("Cancelar");
        btnCancel.setBounds(200, 200, 100, 30);
        btnCancel.addActionListener(e -> dispose());

        panel.add(lblId); panel.add(txtId);
        panel.add(lblName); panel.add(txtName);
        panel.add(lblDisability); panel.add(chkDisability);
        panel.add(btnSave); panel.add(btnCancel);
        
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String id = txtId.getText();
            String name = txtName.getText();
            boolean dis = chkDisability.isSelected();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos");
                return;
            }

            Customer c = new Customer(id, name, dis);
            String msg = customerController.insertCustomer(c);
            JOptionPane.showMessageDialog(this, msg);
            if (msg.contains("éxito")) dispose();
        }
    }
}
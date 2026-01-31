package view;

import controller.CustomerController;
import model.entities.Customer;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomerManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit, buttonAdd; 
    JPanel panelCustomers;
    JTable tableCustomers;
    DefaultTableModel modelDataTable;

    final String[] headings = {"Cédula", "Nombre", "Discapacidad"};

    CustomerController customerController; 
    CustomerWindow customerWindow;

    public CustomerManagement() {
        super("Gestión de Clientes", false, true, false, true);
        this.setSize(650, 500);
        this.setLocation(230, 50);
        this.setResizable(false);

        customerController = new CustomerController();
        customerWindow = new CustomerWindow();

        panelCustomers = new JPanel();
        panelCustomers.setLayout(null);
        panelCustomers.setBackground(Color.WHITE);
        this.add(panelCustomers);

        tableCustomers = new JTable();
        JScrollPane scrollBar = new JScrollPane(tableCustomers);
        scrollBar.setBounds(25, 75, 600, 249);
        panelCustomers.add(scrollBar);

        createTable();

    
        buttonAdd = new JButton("Agregar");
        buttonAdd.setBounds(80, 375, 100, 25);
        buttonAdd.setToolTipText("Presione para registrar un nuevo cliente");
        panelCustomers.add(buttonAdd);
        buttonAdd.addActionListener(e -> {
            JDesktopPane desktopPane = this.getDesktopPane();
            this.dispose();
            customerWindow.setTitle("Insertar Cliente");
            customerWindow.txtId.setEditable(true); 
            customerWindow.txtId.setText("");
            customerWindow.txtName.setText("");
            customerWindow.chkDisability.setSelected(false);
            customerWindow.btnSave.setText("Insertar");
            customerWindow.setVisible(true);
            desktopPane.add(customerWindow);
        });

        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(270, 375, 100, 25); 
        buttonDelete.setToolTipText("Presione para borrar un cliente");
        panelCustomers.add(buttonDelete);
        buttonDelete.addActionListener(e -> {
            int selectedRow = tableCustomers.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla");
                return;
            }

            int borrar = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar este cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (borrar == 0) {
                removeCustomer(); 
                createTable();   
                JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
            }
        });

        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(460, 375, 100, 25); 
        panelCustomers.add(buttonEdit);
        buttonEdit.addActionListener(e -> {
            if (tableCustomers.getSelectedRow() != -1) {
                fillCustomerFormToModify(); 
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para editar");
            }
        });

        this.setVisible(true);
    }

    public void createTable() {
        ArrayList<Customer> customers = customerController.getAllCustomers();
        modelDataTable = new DefaultTableModel(headings, 0);

        for (Customer c : customers) {
            Object[] row = {
                c.getId(), 
                c.getName(), 
                c.isDisabilityPresented() ? "Sí" : "No"
            };
            modelDataTable.addRow(row);
        }
        tableCustomers.setModel(modelDataTable);
    }

    public void removeCustomer() {
        int row = tableCustomers.getSelectedRow();
        if (row != -1) {
            String id = tableCustomers.getValueAt(row, 0).toString();
            Customer temp = new Customer();
            temp.setId(id);
            customerController.deleteCustomer(temp);
        }
    }

    public void fillCustomerFormToModify() {
        JDesktopPane desktopPane = this.getDesktopPane();
        int row = tableCustomers.getSelectedRow();
        
        String id = tableCustomers.getValueAt(row, 0).toString();
        String name = tableCustomers.getValueAt(row, 1).toString();
        String disStr = tableCustomers.getValueAt(row, 2).toString();

        this.dispose();

        customerWindow.setTitle("Modificar Cliente");
        customerWindow.txtId.setText(id);      
        customerWindow.txtId.setEditable(false); 
        customerWindow.txtName.setText(name);  
        customerWindow.chkDisability.setSelected(disStr.equals("Sí"));
        customerWindow.btnSave.setText("Modificar"); 
        
        customerWindow.setVisible(true);
        desktopPane.add(customerWindow);
    }
}
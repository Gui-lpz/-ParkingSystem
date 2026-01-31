package view;

import controller.AdministratorController;
import model.entities.Administrator;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit, buttonAdd; // Añadido buttonAdd
    JPanel panelAdmins;
    JTable tableAdmins;
    DefaultTableModel modelDataTable;

    final String[] headings = {"Cédula", "Nombre", "Usuario", "N° Admin"};

    AdministratorController adminController;
    AdministratorWindow adminWindow;

    public AdministratorManagement() {
        super("Gestión de Administradores", false, true, false, true);
        this.setSize(650, 500);
        this.setLocation(230, 50);
        this.setResizable(false);

        adminController = new AdministratorController();
        adminWindow = new AdministratorWindow();

        panelAdmins = new JPanel(null);
        panelAdmins.setBackground(Color.WHITE);
        this.add(panelAdmins);

        tableAdmins = new JTable();
        JScrollPane scrollBar = new JScrollPane(tableAdmins);
        scrollBar.setBounds(25, 75, 600, 249);
        panelAdmins.add(scrollBar);

        createTable();
        
        buttonAdd = new JButton("Agregar");
        buttonAdd.setBounds(80, 375, 100, 25);
        panelAdmins.add(buttonAdd);
        buttonAdd.addActionListener(e -> {
            JDesktopPane desktopPane = this.getDesktopPane();
            this.dispose();
            adminWindow.setTitle("Insertar Administrador");
            adminWindow.txtId.setEditable(true);
            adminWindow.txtId.setText("");
            adminWindow.txtName.setText("");
            adminWindow.txtUser.setText("");
            adminWindow.txtPass.setText("");
            adminWindow.txtAdminNum.setText("");
            adminWindow.btnSave.setText("Insertar");
            adminWindow.setVisible(true);
            desktopPane.add(adminWindow);
        });

        // BOTÓN BORRAR
        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(270, 375, 100, 25);
        panelAdmins.add(buttonDelete);
        buttonDelete.addActionListener(e -> {
            int selectedRow = tableAdmins.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un administrador de la tabla");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar este administrador?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                removeAdmin();
                createTable();
            }
        });

        // BOTÓN EDITAR
        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(460, 375, 100, 25);
        panelAdmins.add(buttonEdit);
        buttonEdit.addActionListener(e -> {
            if (tableAdmins.getSelectedRow() != -1) {
                fillAdminFormToModify();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un administrador para editar");
            }
        });

        this.setVisible(true);
    }

    public void createTable() {
        ArrayList<Administrator> list = adminController.getAllAdministrators();
        modelDataTable = new DefaultTableModel(headings, 0);
        for (Administrator a : list) {
            Object[] row = {a.getId(), a.getName(), a.getUsername(), a.getAdminNumber()};
            modelDataTable.addRow(row);
        }
        tableAdmins.setModel(modelDataTable);
    }

    public void removeAdmin() {
        int row = tableAdmins.getSelectedRow();
        int adminNum = Integer.parseInt(tableAdmins.getValueAt(row, 3).toString());
        String result = adminController.deleteAdministrator(adminNum);
        JOptionPane.showMessageDialog(null, result);
    }

    public void fillAdminFormToModify() {
        JDesktopPane desktopPane = this.getDesktopPane();
        int row = tableAdmins.getSelectedRow();
        
        String id = tableAdmins.getValueAt(row, 0).toString();
        String name = tableAdmins.getValueAt(row, 1).toString();
        String user = tableAdmins.getValueAt(row, 2).toString();
        String adminNum = tableAdmins.getValueAt(row, 3).toString();

        this.dispose();

        adminWindow.setTitle("Modificar Administrador");
        adminWindow.txtId.setText(id);
        adminWindow.txtId.setEditable(false); 
        adminWindow.txtName.setText(name);
        adminWindow.txtUser.setText(user);
        adminWindow.txtAdminNum.setText(adminNum);
        adminWindow.txtAdminNum.setEditable(false);
        adminWindow.btnSave.setText("Modificar");
        
        adminWindow.setVisible(true);
        desktopPane.add(adminWindow);
    }
}
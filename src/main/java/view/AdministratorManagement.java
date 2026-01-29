package view;

import controller.AdministratorController;
import model.entities.Administrator;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdministratorManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit;
    JPanel panelAdmins;
    JTable tableAdmins;
    DefaultTableModel modelDataTable;

    final String[] headings = {"Cédula", "Nombre", "Usuario", "N° Admin"};

    AdministratorController adminController;
    AdministratorWindow adminWindow;

    public AdministratorManagement() {
        super("Gestión de Administradores", false, true, false, true);
        this.setVisible(true);
        this.setSize(650, 500);
        this.setLocation(230, 50);

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

        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(140, 375, 100, 25);
        panelAdmins.add(buttonDelete);
        buttonDelete.addActionListener(e -> {
            if (tableAdmins.getSelectedRow() != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Borrar administrador?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    removeAdmin();
                    createTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un administrador de la tabla.");
            }
        });

        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(360, 375, 100, 25);
        panelAdmins.add(buttonEdit);
        buttonEdit.addActionListener(e -> {
            if (tableAdmins.getSelectedRow() != -1) fillAdminFormToModify();
        });
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
        // El error anterior era pasar el ID como String. Ahora pasamos el adminNumber como int.
        int adminNum = Integer.parseInt(tableAdmins.getValueAt(row, 3).toString());
        String result = adminController.deleteAdministrator(adminNum);
        JOptionPane.showMessageDialog(null, result);
    }

    public void fillAdminFormToModify() {
        JDesktopPane desktopPane = this.getDesktopPane();
        int row = tableAdmins.getSelectedRow();
        
        adminWindow.txtId.setText(tableAdmins.getValueAt(row, 0).toString());
        adminWindow.txtName.setText(tableAdmins.getValueAt(row, 1).toString());
        adminWindow.txtUser.setText(tableAdmins.getValueAt(row, 2).toString());
        adminWindow.txtAdminNum.setText(tableAdmins.getValueAt(row, 3).toString());
        
        this.dispose();
        adminWindow.btnSave.setText("Modificar");
        adminWindow.setVisible(true);
        desktopPane.add(adminWindow);
    }
}
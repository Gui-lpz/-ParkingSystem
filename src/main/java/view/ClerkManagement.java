package view;

import controller.ClerkController;
import model.entities.Clerk;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClerkManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit;
    JPanel panelClerks;
    JTable tableClerks;
    DefaultTableModel modelDataTable;

    final String[] headings = {"Código", "Nombre", "Horario", "Cédula", "Edad"};

    ClerkController clerkController;
    ClerkWindow clerkWindow;

    public ClerkManagement() {
        super("Gestión de Dependientes", false, true, false, true);
        this.setVisible(true);
        this.setSize(650, 500);
        this.setLocation(230, 50);

        panelClerks = new JPanel(null);
        panelClerks.setBackground(Color.WHITE);
        this.add(panelClerks);

        clerkController = new ClerkController();
        clerkWindow = new ClerkWindow();

        tableClerks = new JTable();
        JScrollPane scrollBar = new JScrollPane(tableClerks);
        scrollBar.setBounds(25, 75, 600, 249);
        panelClerks.add(scrollBar);

        createTable();

        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(140, 375, 100, 25);
        panelClerks.add(buttonDelete);
        buttonDelete.addActionListener(e -> {
            int row = tableClerks.getSelectedRow();
            if (row != -1) {
                int code = Integer.parseInt(tableClerks.getValueAt(row, 0).toString());
                clerkController.deleteClerk(code);
                createTable();
            }
        });

        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(360, 375, 100, 25);
        panelClerks.add(buttonEdit);
        buttonEdit.addActionListener(e -> fillClerkFormToModify());
    }

    public void createTable() {
        ArrayList<Clerk> list = clerkController.getAllClerks();
        modelDataTable = new DefaultTableModel(headings, 0);
        for (Clerk c : list) {
            Object[] row = {c.getEmployeeCode(), c.getName(), c.getSchedule(), c.getId(), c.getAge()};
            modelDataTable.addRow(row);
        }
        tableClerks.setModel(modelDataTable);
    }

    private void fillClerkFormToModify() {
        int row = tableClerks.getSelectedRow();
        if (row == -1) return;

        JDesktopPane desktopPane = this.getDesktopPane();
        this.dispose();

        clerkWindow.txtCode.setText(tableClerks.getValueAt(row, 0).toString());
        clerkWindow.txtName.setText(tableClerks.getValueAt(row, 1).toString());
        clerkWindow.txtSchedule.setText(tableClerks.getValueAt(row, 2).toString());
        clerkWindow.txtId.setText(tableClerks.getValueAt(row, 3).toString());
        
        clerkWindow.btnSave.setText("Modificar");
        clerkWindow.setVisible(true);
        desktopPane.add(clerkWindow);
    }
}
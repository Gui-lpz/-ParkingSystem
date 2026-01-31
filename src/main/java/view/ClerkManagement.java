package view;

import controller.ClerkController;
import model.entities.Clerk;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClerkManagement extends JInternalFrame {

    JButton buttonDelete, buttonEdit, buttonAdd;
    JPanel panelClerks;
    JTable tableClerks;
    DefaultTableModel modelDataTable;

    final String[] headings = {"Código", "Nombre", "Horario", "Cédula", "Edad"};

    ClerkController clerkController;
    ClerkWindow clerkWindow;

    public ClerkManagement() {
        super("Gestión de Dependientes", false, true, false, true);
        this.setSize(650, 500);
        this.setLocation(230, 50);

        clerkController = new ClerkController();
        clerkWindow = new ClerkWindow();

        panelClerks = new JPanel(null);
        panelClerks.setBackground(Color.WHITE);
        this.add(panelClerks);

        tableClerks = new JTable();
        JScrollPane scrollBar = new JScrollPane(tableClerks);
        scrollBar.setBounds(25, 75, 600, 249);
        panelClerks.add(scrollBar);

        createTable();

        buttonAdd = new JButton("Agregar");
        buttonAdd.setBounds(80, 375, 100, 25);
        panelClerks.add(buttonAdd);
        buttonAdd.addActionListener(e -> {
            JDesktopPane desktop = this.getDesktopPane();
            this.dispose();

            clerkWindow.setTitle("Insertar Dependiente");
            clerkWindow.clearForm();
            clerkWindow.btnSave.setText("Insertar");

            desktop.add(clerkWindow);
            clerkWindow.setVisible(true);
            clerkWindow.moveToFront();
        });

        buttonDelete = new JButton("Borrar");
        buttonDelete.setBounds(270, 375, 100, 25);
        panelClerks.add(buttonDelete);
        buttonDelete.addActionListener(e -> removeClerk());

        buttonEdit = new JButton("Editar");
        buttonEdit.setBounds(460, 375, 100, 25);
        panelClerks.add(buttonEdit);
        buttonEdit.addActionListener(e -> fillClerkFormToModify());

        this.setVisible(true);
    }

    public void createTable() {
        ArrayList<Clerk> list = clerkController.getAllClerks();
        modelDataTable = new DefaultTableModel(headings, 0);

        for (Clerk c : list) {
            Object[] row = {
                c.getEmployeeCode(),
                c.getName(),
                c.getSchedule(),
                c.getId(),
                c.getAge()
            };
            modelDataTable.addRow(row);
        }

        tableClerks.setModel(modelDataTable);
    }

    private void removeClerk() {
        int row = tableClerks.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un dependiente");
            return;
        }

        int op = JOptionPane.showConfirmDialog(this, "¿Desea borrar este dependiente?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (op == 0) {
            int code = Integer.parseInt(tableClerks.getValueAt(row, 0).toString());
            clerkController.deleteClerk(code);
            createTable();
        }
    }

    private void fillClerkFormToModify() {
        int row = tableClerks.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un dependiente para editar");
            return;
        }
        
        int code = Integer.parseInt(tableClerks.getValueAt(row, 0).toString());
        Clerk c = clerkController.getClerkByCode(code);

        if (c == null) return;

        JDesktopPane desktop = this.getDesktopPane();
        this.dispose();

        clerkWindow.setTitle("Modificar Dependiente");

        clerkWindow.txtCode.setText(String.valueOf(c.getEmployeeCode()));
        clerkWindow.txtName.setText(c.getName());
        clerkWindow.txtSchedule.setText(c.getSchedule());
        clerkWindow.txtId.setText(c.getId());
        clerkWindow.txtAge.setText(String.valueOf(c.getAge()));
        clerkWindow.txtUser.setText(c.getUsername());
        clerkWindow.txtPass.setText(c.getPassword());

        clerkWindow.btnSave.setText("Modificar");

        desktop.add(clerkWindow);
        clerkWindow.setVisible(true);
        clerkWindow.moveToFront();
    }
}

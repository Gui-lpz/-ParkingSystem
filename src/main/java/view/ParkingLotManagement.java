package view;

import controller.ParkingLotController;
import model.entities.ParkingLot;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ParkingLotManagement extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private ParkingLotController controller;

    public ParkingLotManagement() {
        super("Gestión de Parqueos", false, true, false, true);
        controller = new ParkingLotController();
        setSize(600, 400);
        initComponents();
        loadData();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Espacios Totales"}, 0);
        table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();

        JButton btnAdd = new JButton("Agregar");
        JButton btnEdit = new JButton("Editar");
        JButton btnDelete = new JButton("Eliminar");

        btnAdd.addActionListener(e -> openInsert());
        btnEdit.addActionListener(e -> openEdit());
        btnDelete.addActionListener(e -> deleteAction());

        btnPanel.add(btnAdd);
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        panel.add(btnPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void loadData() {
        model.setRowCount(0);
        ArrayList<ParkingLot> list = controller.getAllParkingLots();
        for (ParkingLot pl : list) {
            model.addRow(new Object[]{pl.getId(), pl.getName(), pl.getNumberOfSpaces()});
        }
    }

    private void openInsert() {
        JDesktopPane desktop = this.getDesktopPane();
        ParkingLotWindow w = new ParkingLotWindow();
        w.setTitle("Insertar Parqueo");
        w.btnSave.setText("Insertar");
        w.clearFields();

        this.dispose();
        desktop.add(w);
        w.setVisible(true);
    }

    private void openEdit() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un parqueo");
            return;
        }

        int id = (int) table.getValueAt(row, 0);
        ParkingLot pl = controller.getParkingLotById(id);

        JDesktopPane desktop = this.getDesktopPane();
        ParkingLotWindow w = new ParkingLotWindow();
        w.setTitle("Modificar Parqueo");
        w.btnSave.setText("Modificar");
        w.loadParkingLotData(pl);

        this.dispose();
        desktop.add(w);
        w.setVisible(true);
    }

    private void deleteAction() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) table.getValueAt(row, 0);
            controller.deleteParkingLot(id);
            loadData();
        }
    }
}

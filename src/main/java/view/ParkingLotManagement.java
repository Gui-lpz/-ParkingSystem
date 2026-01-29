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
        JButton btnDelete = new JButton("Eliminar");
        btnDelete.addActionListener(e -> deleteAction());
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

    private void deleteAction() {
        int row = table.getSelectedRow();
        if (row != -1) {
            int id = (int) table.getValueAt(row, 0);
            controller.deleteParkingLot(id);
            loadData();
            JOptionPane.showMessageDialog(this, "Parqueo eliminado");
        }
    }
}
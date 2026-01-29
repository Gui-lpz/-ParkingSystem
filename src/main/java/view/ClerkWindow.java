package view;

import controller.ClerkController;
import model.entities.Clerk;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ClerkWindow extends JInternalFrame implements ActionListener {
    JPanel panel;
    JLabel lblId, lblName, lblUser, lblPass, lblCode, lblSchedule, lblAge;
    public JTextField txtId, txtName, txtUser, txtCode, txtSchedule, txtAge;
    public JPasswordField txtPass;
    JButton btnSave, btnCancel;
    ClerkController clerkController;

    public ClerkWindow() {
        super("Insertar Dependiente", false, true, false, true);
        this.setSize(420, 500);
        this.setLocation(215, 20);
        clerkController = new ClerkController();

        panel = new JPanel(null);
        panel.setBackground(Color.WHITE);
        this.add(panel);

        btnSave = new JButton("Insertar");
        btnSave.setBounds(90, 420, 100, 25);
        btnSave.addActionListener(this);
        panel.add(btnSave);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Insertar")) {
            Clerk clerk = new Clerk(
                Integer.parseInt(txtCode.getText()), 
                txtSchedule.getText(), 
                Integer.parseInt(txtAge.getText()), 
                null, txtId.getText(), txtName.getText(), txtUser.getText(), new String(txtPass.getPassword())
            );
            JOptionPane.showMessageDialog(this, clerkController.insertClerk(clerk));
            dispose();
        }
    }
}
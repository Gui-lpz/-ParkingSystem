package view;

import controller.AdministratorController;
import model.entities.Administrator;
import model.entities.User;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginWindow extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSignIn;
    private JButton btnRegister;
    private AdministratorController administratorController;

    public LoginWindow() {
        administratorController = new AdministratorController();

        setTitle("Acceso al Sistema");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginWindow().setVisible(true);
        });
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("Welcome", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JPanel usernamePanel = new JPanel(new BorderLayout(10, 0));
        usernamePanel.add(new JLabel("Username:"), BorderLayout.WEST);
        txtUsername = new JTextField(15);
        usernamePanel.add(txtUsername, BorderLayout.CENTER);

        JPanel passwordPanel = new JPanel(new BorderLayout(10, 0));
        passwordPanel.add(new JLabel("Password:"), BorderLayout.WEST);
        txtPassword = new JPasswordField(15);
        passwordPanel.add(txtPassword, BorderLayout.CENTER);

        formPanel.add(usernamePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordPanel);

        JPanel buttonPanel = new JPanel();
        btnSignIn = new JButton("Sign In");
        btnRegister = new JButton("Register");

        btnSignIn.addActionListener(this);
        btnRegister.addActionListener(this);

        buttonPanel.add(btnSignIn);
        buttonPanel.add(btnRegister);

        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSignIn) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Administrator foundAdmin = administratorController.searchAdministratorByUsername(username);

            if (foundAdmin != null) {
                String[] loginDetails = {username, password};

                if (foundAdmin.verifyUserLogin(loginDetails, 0)) {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + foundAdmin.getName(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); 
                    
                    MainWindow mainApp = new MainWindow();
                    mainApp.setVisible(true); 
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnRegister) {
            RegistrationWindow.insertAdministrator(); 
            txtUsername.setText("");
            txtPassword.setText("");
        }
    }
}
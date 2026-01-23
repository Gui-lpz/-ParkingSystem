/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.AdministratorController;
import model.entities.Administrator;
import model.entities.User;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginWindow extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnSignIn;
    private JButton btnRegister;
    private AdministratorController administratorController; //instancia de AdministratorController para probar tanto el register como el login

    public LoginWindow() {

        administratorController = new AdministratorController();

        setTitle("Login");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    public static void main(String[] args) {

        new LoginWindow().setVisible(true);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título
        JLabel lblTitle = new JLabel("Welcome", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Formulario para username y password
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        JPanel usernamePanel = new JPanel(new BorderLayout(10, 0));
        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(15);
        usernamePanel.add(lblUsername, BorderLayout.WEST);
        usernamePanel.add(txtUsername, BorderLayout.CENTER);

        JPanel passwordPanel = new JPanel(new BorderLayout(10, 0));
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(15);
        passwordPanel.add(lblPassword, BorderLayout.WEST);
        passwordPanel.add(txtPassword, BorderLayout.CENTER);

        formPanel.add(usernamePanel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(passwordPanel);

        // Botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));

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

        // Login
        if (e.getSource() == btnSignIn) {

            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Usuario temporal para buscar el admin y validarlo con el txt
            User tempUser = new Administrator();
            tempUser.setUsername(username);
            tempUser.setPassword(password);

            User loggedUser = administratorController.searchUser(tempUser);

            if (loggedUser != null) {

                Administrator admin = (Administrator) loggedUser;

                JOptionPane.showMessageDialog(this,
                        "Welcome " + admin.getName(),
                        "Login successful",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose(); //Cierra una ventana en especifico sin cerrar toda la aplicación

                RegistrationWindow.showMainMenu(); //Abre el menú hecho con switch case despues de loguearse de forma correcta

            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password",
                        "Login error",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        //Register
        if (e.getSource() == btnRegister) {
            RegistrationWindow.insertAdministrator(); //Llama al método insertAdministrator para pedir todos los datos del administrador que se quiera loguear una vez
                                                                                //haya ingresado todos sus datos
            dispose();
            new LoginWindow().setVisible(true); //Creamos una nueva ventana de login para que se refresquen los datos en el txt y no se caiga el programa
        }
    }
}

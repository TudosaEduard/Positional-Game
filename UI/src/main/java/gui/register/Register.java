package gui.register;

import dao.User;
import dao.DAOConnection;
import dao.DAOUsers;
import gui.login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Register extends JFrame{
    private JPanel registerPanel;
    private JTextField usernameRegisterField;
    private JPasswordField passwordRegisterField1;
    private JPasswordField passwordRegisterField2;
    private JTextField emailRegisterField;
    private JButton registerButton;
    private JLabel titleRegister;
    private JLabel usernameRegister;
    private JLabel passwordRegister1;
    private JLabel passwordRegister2;
    private JLabel emailRegister;
    private JButton loginNowButton;

    public Register(JFrame frame) {
        super();
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        loginNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login(null);
                User user = login.user;

                if(user != null){
                    System.out.println("Successfully logged in of: " + user.getUsername() + " !");
                } else {
                    System.out.println("Login canceled!");
                }
            }
        });

        setVisible(true);
    }

    public User user;
    private void registerUser() {
        String username = usernameRegisterField.getText();
        String password1 = String.valueOf(passwordRegisterField1.getPassword());
        String password2 = String.valueOf(passwordRegisterField2.getPassword());
        String email = emailRegisterField.getText();

        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Try again", JOptionPane.ERROR_MESSAGE);
        } else if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Try again", JOptionPane.ERROR_MESSAGE);
        } else {
            user = addNewUserToDatabase(username, password1, email);
            if(user != null){
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create a new user! Username, email or password are invalid!", "Try again", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private User addNewUserToDatabase(String username, String password, String email) {
        User user = null;

        Connection connection = DAOConnection.getConnection();
        var usersConnection = new DAOUsers(connection);
        if(!usersConnection.existsTable()) {
            usersConnection.createTable();
        }
        if(usersConnection.existsUser(username, email)){
            JOptionPane.showMessageDialog(this, "Username or email already exists!", "Try again", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            usersConnection.insertUser(username, password, email);
            user = new User(username, password, email);
        }

        return user;
    }
}

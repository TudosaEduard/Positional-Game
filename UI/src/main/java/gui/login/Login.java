package gui.login;

import dao.User;
import dao.DAOConnection;
import dao.DAOUsers;
import gui.game.Menu;
import gui.register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class Login extends JFrame{
    private JPanel loginPanel;
    private JPanel welcomePanel;
    private JLabel welcomeText1;
    private JTextField usernameLoginField;
    private JPasswordField passwordLoginField;
    private JButton loginButton;
    private JLabel welcomeText2;
    private JPanel loginNowPanel;
    private JLabel titleLogin;
    private JLabel usernameLogin;
    private JLabel passwordLogin;
    private JButton registerNowButton;

    public Login(JFrame frame) {
        super();
        setTitle("Access your account");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        registerNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Register register = new Register(null);
                User user = register.user;

                if(user != null){
                    System.out.println("Successfully registered of: " + user.getUsername() + " !");
                    Login login = new Login(null);
                } else {
                    System.out.println("Registration canceled!");
                }
            }
        });

        setVisible(true);
    }

    private void loginUser() {
        String username = usernameLoginField.getText();
        String password = String.valueOf(passwordLoginField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Try again", JOptionPane.ERROR_MESSAGE);
        } else {
            user = getAuthenticatedUser(username, password);

            if(user != null){
                dispose();
                Menu menu = new Menu(null);
                DAOConnection.closeConnection();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username(email) or password!", "Try again", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public User user;
    private User getAuthenticatedUser(String username, String password) {
        User user = null;

        Connection connection = DAOConnection.getConnection();
        var usersConnection = new DAOUsers(connection);

        if(usersConnection.checkLoginByUsername(username, password)){
            user = new User(username, password, usersConnection.getEmailByUsername(username));
        } else if(usersConnection.checkLoginByEmail(username, password)) {
            user = new User(usersConnection.getUsernameByEmail(username), password, username);
        } else {
            return null;}

        return user;
    }
}

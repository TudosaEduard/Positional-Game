package gui.login;

import javax.swing.*;
import java.awt.*;

public class Login extends JDialog{
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

    public Login(JFrame frame) {
        super(frame, "Access your account", true);
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login(null);
    }
}

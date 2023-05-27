package gui.register;

import javax.swing.*;
import java.awt.*;

public class Register extends JDialog{
    private JPanel registerPanel;
    private JTextField usernameRegisterField;
    private JTextField passwordRegisterField1;
    private JPasswordField passwordRegisterField2;
    private JTextField emailRegisterField;
    private JButton registerButton;
    private JLabel titleRegister;
    private JLabel usernameRegister;
    private JLabel passwordRegister1;
    private JLabel passwordRegister2;
    private JLabel emailRegister;
    private JLabel loginNowButton;

    public Register(JFrame frame) {
        super(frame, "Create a new account", true);
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(500, 500));
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public static void main(String[] args) {
        Register register = new Register(null);
    }
}

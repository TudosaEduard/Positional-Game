package gui.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JDialog{
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JPanel menuPanel;

    public Menu(JFrame frame) {
        super(frame, "Menu Positional Game", true);
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Game game = new Game(null);
            }
        });
        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

//    public static void main(String[] args) {
//        Game game = new Game(null);
//    }
}

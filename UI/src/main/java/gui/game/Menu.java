package gui.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton playButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JPanel menuPanel;

    public int valueNodes = 8;
    public double valueProbability = 0.5;
    public String valuePlayer = "AI";

    public Menu(JFrame frame) {
        super();
        setTitle("Menu Positional Game");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Game game = new Game(null, valueNodes, valueProbability, valuePlayer);
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Settings settings = new Settings(null);
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

    public Menu(JFrame frame, int valueNodes, double valueProbability, String valuePlayer) {
        super();
        this.valueNodes = valueNodes;
        this.valueProbability = valueProbability;
        this.valuePlayer = valuePlayer;
        setTitle("Menu Positional Game");
        setContentPane(menuPanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Game game = new Game(null, valueNodes, valueProbability, valuePlayer);
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Settings settings = new Settings(null);
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
//        Game game = new Game(null, valueNodes, valueProbability, valuePlayer);
//    }
}

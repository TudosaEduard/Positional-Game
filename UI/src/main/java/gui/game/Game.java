package gui.game;

import javax.swing.*;
import java.awt.*;

public class Game extends JDialog{

    private JPanel gamePanel;
    private JPanel scoreGame;
    private JPanel pictureGame;
    private JLabel playerRound;
    private JLabel scorePlayer;

    public Game(JFrame frame) {
        super(frame, "Positional Game", true);
        setContentPane(gamePanel);
        setMinimumSize(new Dimension(700, 700));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        scoreGame.setMinimumSize(new Dimension(700, 30));
        pictureGame.setMinimumSize(new Dimension(700, 500));

        startGame();
        setVisible(true);
    }

    private void startGame() {
        
    }
}

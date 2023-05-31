package gui.game;

import game.implement.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game extends JFrame {
    private JPanel gamePanel;
    private JPanel scoreGame;
    public JLabel playerRound;
    public JLabel scorePlayer;
    private JPanel drawGame;
    private Board board;

    public int valueNodes;
    public double valueProbability;
    public String valuePlayer;

    public Game(JFrame frame, int valueNodes, double valueProbability, String valuePlayer) {
        super();
        this.valueNodes = valueNodes;
        this.valueProbability = valueProbability;
        this.valuePlayer = valuePlayer;
        setTitle("Positional Game");
        setContentPane(gamePanel);
        setMinimumSize(new Dimension(900, 900));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        scoreGame.setMinimumSize(new Dimension(900, 100));
        drawGame.setMinimumSize(new Dimension(900, 800));

        board = new Board(this,valueNodes, 900, 800, valueProbability, valuePlayer);
        drawGame.setLayout(new BorderLayout());
        drawGame.add(board, BorderLayout.CENTER);
        drawGame.setBackground(new Color(255, 236, 194));

        drawGame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                board.createBoard();
            }
        });

        setVisible(true);
    }
}

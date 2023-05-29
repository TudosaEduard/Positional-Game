package gui.game;

import game.implement.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Game extends JDialog {
    private JPanel gamePanel;
    private JPanel scoreGame;
    private JLabel playerRound;
    private JLabel scorePlayer;
    private JPanel drawGame;
    private Board board;

    public Game(JFrame frame) {
        super(frame, "Positional Game", true);
        setContentPane(gamePanel);
        setMinimumSize(new Dimension(900, 900));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        scoreGame.setMinimumSize(new Dimension(900, 100));
        drawGame.setMinimumSize(new Dimension(900, 800));

        board = new Board(8, 900, 800, 0.5);
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

        //startGame();
        setVisible(true);
    }

//    private void startGame() {
//
//    }
}

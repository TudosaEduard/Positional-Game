package game.implement;

import com.sun.tools.javac.Main;
import game.components.Edge;
import game.components.Node;
import game.components.Vertex;
import gui.game.Game;
import gui.game.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    private Game game;
    private int W, H;
    private int numVertices;
    private double probability;
    private Vertex node1 = null, node2 = null;
    private String edge;

    List<Node> nodes;
    List <Edge> edges;
    private List<Vertex> vertices = new ArrayList<>();
    private int[] x, y;
    Graphics2D graphics;
    BufferedImage image;
    int playerTurn = 1;
    Ai ai = new Ai();
    int scorePlayer1 = 1000;
    int scorePlayer2 = 1000;

    int nrEdgesPlayer1 = 0;
    int nrEdgesPlayer2 = 0;

    public Board(Game game, int numVertices, int W, int H, double probability, String valuePlayer) {
        this.game = game;
        this.numVertices = numVertices;
        this.probability = probability;
        this.W = W;
        this.H = H;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        if(valuePlayer.equals("AI")){
            ai.setStartAi(1);
        }else{
            ai.setStartAi(0);
        }
    }

    public void createBoard(){
        createOffscreenImage();
        createNodes(numVertices);
        createEdges(numVertices, probability);
        createVertices();
        drawLines();
        drawVertices();
        ai.giveEdges(edges);
        selectNodes();
        repaint();
    }

    public void createNodes(int numNodes){
        for (int i = 0; i < numNodes; i++) {
            nodes.add(new Node("N" + i));
        }
    }

    public void createEdges(int numNodes, double probability){
        int nrEdges = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                if (Math.random() < probability) {
                    Edge edge = new Edge("E" + nrEdges, nodes.get(i), nodes.get(j), "black");
                    edges.add(edge);
                    nrEdges += 1;
                }
            }
        }
    }

    private void createVertices() {
        int x0 = W / 2;
        int y0 = H / 2;
        int radius = (H - 200) / 2 - 10;
        double alpha = 2 * Math.PI / numVertices;

        x = new int[numVertices];
        y = new int[numVertices];

        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            vertices.add(new Vertex(nodes.get(i), x[i], y[i]));
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(new Color(255, 236, 194));
        graphics.fillRect(0, 0, W, H);
    }

    private void drawLines() {
        for (Edge e : edges) {
            graphics.setColor(Color.BLACK);
            Node n1 = e.getNode1();
            Node n2 = e.getNode2();

            Vertex v1 = null, v2 = null;
            for (Vertex v : vertices) {
                if (v.getNode().equals(n1)) {
                    v1 = v;
                }
                if (v.getNode().equals(n2)) {
                    v2 = v;
                }
            }
            graphics.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        }
    }

    private void drawVertices() {
        for (int i = 0; i < numVertices; i++) {
            graphics.setColor(Color.BLACK);
            graphics.fillOval(x[i] - 10, y[i] - 10, 20, 20);
            graphics.setColor(Color.WHITE);
            graphics.fillOval(x[i] - 6, y[i] - 6, 12, 12);
        }
    }

    public void selectNodes() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                for (Vertex v : vertices) {
                    if (v.contains(x, y)) {
                        if (node1 == null) {
                            node1 = v;
                            graphics.setColor(Color.GREEN);
                            graphics.fillOval(v.getX() - 6, v.getY() - 6, 12, 12);
                            repaint();
                        } else if (node2 == null && node1 != v && edgeAvailable(node1.getNode(), v.getNode())) {
                            node2 = v;
                            graphics.setColor(Color.GREEN);
                            graphics.fillOval(v.getX() - 6, v.getY() - 6, 12, 12);

                            String color = playerTurn == 1 ? "#ff0000" : "#0000ff";

                            graphics.setColor(Color.decode(color));
                            graphics.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
                            repaint();

                            for (Edge e1 : edges) {
                                if (e1.getNode1().equals(node1.getNode()) && e1.getNode2().equals(node2.getNode()) ||
                                        e1.getNode1().equals(node2.getNode()) && e1.getNode2().equals(node1.getNode())) {
                                    e1.setColor(color);
                                }
                            }

                            edge = node1.getNode().getName() + " " + node2.getNode().getName();

                            updateScore();
                            Win_1_vs_1();
                            setPlayerTurn();

                        } else {

                            if(node2 == null)
                            {
                                graphics.setColor(Color.WHITE);
                                graphics.fillOval(node1.getX() - 6, node1.getY() - 6, 12, 12);
                                repaint();
                                node1 = null;
                                return;
                            }

                            graphics.setColor(Color.WHITE);
                            graphics.fillOval(node1.getX() - 6, node1.getY() - 6, 12, 12);
                            graphics.fillOval(node2.getX() - 6, node2.getY() - 6, 12, 12);
                            startAI();
                            repaint();
                            node1 = null;
                            node2 = null;
                            return;
                        }
                        break;
                    }
                }
            }
        });
    }

    public void updateScore(){
        if(playerTurn == 1 || ai.getStartAi() == 1)
        {
            nrEdgesPlayer1++;
            scorePlayer1 -= 50;
            System.out.println("Player 1 : " + scorePlayer1 + " " + nrEdgesPlayer1);
        }
                            else
        {
            nrEdgesPlayer2++;
            scorePlayer2 -= 50;
            System.out.println("Player 2 : " + scorePlayer2 + " " + nrEdgesPlayer2);
        }
    }

    public void Win_1_vs_1(){
        if(winCondition())
        {
            if(playerTurn == 1)
            {
                JOptionPane.showMessageDialog(null, "Player 1 wins!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Player 2 wins!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
        } else if(nrEdgesPlayer1 + nrEdgesPlayer2 == edges.size())
        {
            if(scorePlayer1 > scorePlayer2)
            {
                JOptionPane.showMessageDialog(null, "Player 1 wins!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
            else if(scorePlayer1 < scorePlayer2)
            {
                JOptionPane.showMessageDialog(null, "Player 2 wins!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Draw!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
        }
    }
    public void setPlayerTurn(){
        playerTurn = playerTurn == 1 ? 2 : 1;

        if(playerTurn == 1 || ai.getStartAi() == 1)
        {
            game.playerRound.setText("Player 1");
            game.scorePlayer.setText("Score : " + scorePlayer1);
        }
        else
        {
            game.playerRound.setText("Player 2");
            game.scorePlayer.setText("Score : " + scorePlayer2);
        }
    }
    public void startAI(){
        if(ai.getStartAi() == 1){
            playerTurn = playerTurn == 1 ? 2 : 1;
            System.out.println("Player turn: " + playerTurn);
            ai.removeTriangle(node1.getNode(), node2.getNode());
            if(ai.isUnwinnable())
            {
                JOptionPane.showMessageDialog(null, "You win!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
            List<Node> bestEdge = ai.getBestEdge();
            for (Vertex vert : vertices) {
                if (vert.getNode().equals(bestEdge.get(0))) {
                    node1 = vert;
                }
                if (vert.getNode().equals(bestEdge.get(1))) {
                    node2 = vert;
                }
            }
            graphics.setColor(Color.BLUE);
            graphics.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
            repaint();

            for (Edge e1 : edges) {
                if (e1.getNode1().equals(node1.getNode()) && e1.getNode2().equals(node2.getNode()) ||
                        e1.getNode1().equals(node2.getNode()) && e1.getNode2().equals(node1.getNode())) {
                    e1.setColor("blue");
                }
            }

            if(winCondition())
            {
                JOptionPane.showMessageDialog(null, "You lose!", "Win game", JOptionPane.INFORMATION_MESSAGE);
                game.dispose();
                Menu menu = new Menu(null);
            }
        }
    }

    public Boolean edgeAvailable(Node node1, Node node2){
        for(Edge e : edges){
            if(e.getNode1().equals(node1) && e.getNode2().equals(node2) || e.getNode1().equals(node2) && e.getNode2().equals(node1))
                if(e.getColor().equals("black"))
                    return true;
        }
        return false;
    }

    public Boolean winCondition(){
        for (Edge e : edges) {
            System.out.println(e.getNode1().getName() + " " + e.getNode2().getName() + " " + e.getColor());
        }
        for(Edge e1 : edges){
            for(Edge e2 : edges){
                for(Edge e3 : edges){
                    if(e1.equals(e2) || e1.equals(e3) || e2.equals(e3))
                        continue;
                    if(e1.getNode1() == null || e1.getNode2() == null || e2.getNode1() == null || e2.getNode2() == null || e3.getNode1() == null || e3.getNode2() == null)
                        continue;
                    if(e1.getNode1().equals(e2.getNode1()) && e1.getNode2().equals(e3.getNode1()) && e2.getNode2().equals(e3.getNode2()) ||
                            e1.getNode1().equals(e2.getNode2()) && e1.getNode2().equals(e3.getNode1()) && e2.getNode1().equals(e3.getNode2()) ||
                            e1.getNode1().equals(e2.getNode1()) && e1.getNode2().equals(e3.getNode2()) && e2.getNode2().equals(e3.getNode1()) ||
                            e1.getNode1().equals(e2.getNode2()) && e1.getNode2().equals(e3.getNode2()) && e2.getNode1().equals(e3.getNode1()))
                    {
                        if(e1.getColor().equals(e2.getColor()) && e1.getColor().equals(e3.getColor()) && e2.getColor().equals(e3.getColor()) && !e1.getColor().equals("black"))
                            return true;
                    }
                }
            }
        }
        return false;
    }

}


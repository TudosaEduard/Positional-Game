package game.implement;

import game.components.Edge;
import game.components.Node;
import game.components.Vertex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    private int W, H;
    private int numVertices;
    private double probability;
    private Vertex node1 = null, node2 = null;
    private String edge;

    List<Node> nodes;
    List <Edge> edges;
    private List<Vertex> vertices = new ArrayList<>();
    private int[] x, y;

    public Board(int numVertices, int W, int H, double probability) {
        this.numVertices = numVertices;
        this.probability = probability;
        this.W = W;
        this.H = H;
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public void createBoard(){
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createOffscreenImage(g);
        createNodes(numVertices);
        createEdges(numVertices, probability);
        createVertices();
        drawLines(g);
        drawVertices(g);
    }

    private void createOffscreenImage(Graphics g) {
        g.setColor(new Color(255, 236, 194));
        g.fillRect(0, 0, W, H);
    }

    private void drawLines(Graphics g) {
        for (Edge e : edges) {
            g.setColor(Color.BLACK);
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
            g.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY());
        }
    }

    private void drawVertices(Graphics g) {
        for (int i = 0; i < numVertices; i++) {
            g.setColor(Color.BLACK);
            g.fillOval(x[i] - 10, y[i] - 10, 20, 20);
            g.setColor(Color.WHITE);
            g.fillOval(x[i] - 6, y[i] - 6, 12, 12);
        }
    }
}

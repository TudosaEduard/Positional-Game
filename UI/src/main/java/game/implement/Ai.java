package game.implement;

import game.components.Edge;
import game.components.Node;

import java.util.ArrayList;
import java.util.List;

public class Ai {

    public int startAi = 1;
    List<Edge> edges = new ArrayList<>();
    List<Edge> coloredEdges = new ArrayList<>();

    List<Node> triangleNode1 = new ArrayList<>();
    List<Node> triangleNode2 = new ArrayList<>();
    List<Node> triangleNode3 = new ArrayList<>();

    public Ai() {
    }

    public int getStartAi() {
        return startAi;
    }

    public void setStartAi(int startAi) {
        this.startAi = startAi;
    }

    public void giveEdges(List<Edge> edges) {
        this.edges = edges;
        findTriangles();
    }

    public void findTriangles() {
        for (int i1 = 0; i1 < edges.size(); i1++) {
            Edge e1 = edges.get(i1);
            for (int i2 = i1 + 1; i2 < edges.size(); i2++) {
                Edge e2 = edges.get(i2);
                for (int i3 = i2 + 1; i3 < edges.size(); i3++) {
                    Edge e3 = edges.get(i3);
                    if (e1.equals(e2) || e1.equals(e3) || e2.equals(e3))
                        continue;
                    if (e1.getNode1() == null || e1.getNode2() == null || e2.getNode1() == null || e2.getNode2() == null || e3.getNode1() == null || e3.getNode2() == null)
                        continue;
                    if (e1.getNode1().equals(e2.getNode1()) && e1.getNode2().equals(e3.getNode1()) && e2.getNode2().equals(e3.getNode2()) ||
                            e1.getNode1().equals(e2.getNode2()) && e1.getNode2().equals(e3.getNode1()) && e2.getNode1().equals(e3.getNode2()) ||
                            e1.getNode1().equals(e2.getNode1()) && e1.getNode2().equals(e3.getNode2()) && e2.getNode2().equals(e3.getNode1()) ||
                            e1.getNode1().equals(e2.getNode2()) && e1.getNode2().equals(e3.getNode2()) && e2.getNode1().equals(e3.getNode1())) {
                        triangleNode1.add(e1.getNode1());
                        triangleNode2.add(e1.getNode2());
                        triangleNode3.add(e2.getNode2());
                    }
                }
            }
        }
    }

    void removeTriangle(Node node1, Node node2) {
        for (int i = 0; i < triangleNode1.size(); i++) {
            if (triangleNode1.get(i).equals(node1) && triangleNode2.get(i).equals(node2) ||
                    triangleNode1.get(i).equals(node2) && triangleNode2.get(i).equals(node1) ||
                    triangleNode1.get(i).equals(node1) && triangleNode3.get(i).equals(node2) ||
                    triangleNode1.get(i).equals(node2) && triangleNode3.get(i).equals(node1) ||
                    triangleNode2.get(i).equals(node1) && triangleNode3.get(i).equals(node2) ||
                    triangleNode2.get(i).equals(node2) && triangleNode3.get(i).equals(node1)) {
                triangleNode1.remove(i);
                triangleNode2.remove(i);
                triangleNode3.remove(i);
                return;
            }
        }
    }

    public List<Node> getBestEdge() {
        Node node1 = triangleNode1.get(0);
        Node node2 = triangleNode2.get(0);
        Node node3 = triangleNode3.get(0);

        List<Integer> e = new ArrayList<Integer>();
        e.add(1);
        e.add(1);
        e.add(1);

        for (Edge colorEdge : coloredEdges) {
            if (colorEdge.getNode1().equals(node1) && colorEdge.getNode2().equals(node2) || colorEdge.getNode1().equals(node2) && colorEdge.getNode2().equals(node1))
                e.set(0, 0);
            if (colorEdge.getNode1().equals(node1) && colorEdge.getNode2().equals(node3) || colorEdge.getNode1().equals(node3) && colorEdge.getNode2().equals(node1))
                e.set(1, 0);
            if (colorEdge.getNode1().equals(node2) && colorEdge.getNode2().equals(node3) || colorEdge.getNode1().equals(node3) && colorEdge.getNode2().equals(node2))
                e.set(2, 0);
        }

        System.out.println(e.get(0) + " " + e.get(1) + " " + e.get(2));

        for (int i = 0; i < e.size(); i++) {
            List<Node> nodes = new ArrayList<Node>();
            if (e.get(i) == 1) {
                if (i == 0) {
                    coloredEdges.add(new Edge("random1", node1, node2, "1"));
                    nodes.add(node1);
                    nodes.add(node2);
                    return nodes;
                }
                if (i == 1) {
                    coloredEdges.add(new Edge("random2", node1, node3, "1"));
                    nodes.add(node1);
                    nodes.add(node3);
                    return nodes;
                }
                if (i == 2) {
                    coloredEdges.add(new Edge("random3", node2, node3, "1"));
                    nodes.add(node2);
                    nodes.add(node3);
                    return nodes;
                }
            }
        }
        return null;
    }

    public Boolean isUnwinnable(){
        return triangleNode1.size() == 0;
    }
}


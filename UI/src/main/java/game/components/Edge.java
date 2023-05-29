package game.components;

import java.io.Serializable;

public class Edge implements Serializable {
    private String name;
    private Node node1;
    private Node node2;
    private String color;

    public Edge(String name, Node node1, Node node2, String color){
        this.name = name;
        this.node1 = node1;
        this.node2 = node2;
        this.color = color;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " [" + node1 + "," + node2 + "," + color + "]";
    }
}
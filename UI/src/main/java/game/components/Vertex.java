package game.components;

public class Vertex {
    public Node node;
    public int x, y;

    public Vertex(Node node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Boolean contains(int x, int y) {
        if (x >= this.x - 10 && x <= this.x + 10 && y >= this.y - 10 && y <= this.y + 10) {
            return true;
        }
        return false;
    }
}

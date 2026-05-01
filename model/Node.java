package model;

public class Node {
    public String name;
    public int x, y;

    public double g = Double.MAX_VALUE;
    public double h, f;
    public Node parent;

    public Node(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
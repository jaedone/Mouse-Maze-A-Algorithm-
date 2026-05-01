package model;

public class Edge {
    public Node from, to;
    public double cost;

    public Edge(Node from, Node to, double cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}
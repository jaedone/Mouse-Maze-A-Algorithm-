package mousemaze.model;

public class Node {

    public final int row, col;
    public final String value;

    public double g = Double.MAX_VALUE;
    public double h = 0;
    public double f = Double.MAX_VALUE;

    public Node parent;

    public final boolean walkable;

    public Node(int r, int c, String val) {
        this.row = r;
        this.col = c;
        this.value = val;
        this.walkable = !val.equals("WW");
    }
}
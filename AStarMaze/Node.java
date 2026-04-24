public class Node {
    int row, col;
    String value;

    double g, h, f;
    Node parent;

    boolean walkable;

    public Node(int r, int c, String val) {
        row = r;
        col = c;
        value = val;

        walkable = !val.equals("WW");
    }
}
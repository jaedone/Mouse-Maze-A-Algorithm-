package mousemaze.loader;

import mousemaze.model.Node;
import java.io.*;
import java.util.*;

public class MazeLoader {

    public static Node[][] load(String file) throws Exception {
        List<String[]> rows = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("resources/" + file));
        String line;

        while ((line = br.readLine()) != null) {
            rows.add(line.trim().split("\\s+"));
        }

        String[][] grid = rows.toArray(new String[0][]);
        return buildNodes(grid);
    }

    public static Node[][] buildNodes(String[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Node[][] nodes = new Node[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                nodes[r][c] = new Node(r, c, grid[r][c]);
            }
        }

        return nodes;
    }

    public static Node find(Node[][] nodes, String target) {
        for (Node[] row : nodes) {
            for (Node n : row) {
                if (n.value.equals(target))
                    return n;
            }
        }
        return null;
    }
}
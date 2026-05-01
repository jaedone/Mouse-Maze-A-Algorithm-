package mousemaze.algorithm;

import mousemaze.model.Node;
import java.util.*;

public class PathUtils {

    public static double heuristic(Node a, Node b) {
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    public static List<Node> neighbors(Node[][] grid, Node n) {
        List<Node> list = new ArrayList<>();
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] d : dirs) {
            int r = n.row + d[0];
            int c = n.col + d[1];

            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
                list.add(grid[r][c]);
            }
        }

        return list;
    }

    public static List<Node> buildPath(Node end) {
        List<Node> path = new ArrayList<>();
        while (end != null) {
            path.add(end);
            end = end.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
import java.util.*;

public class AStar {

    public static List<Node> findPath(Node[][] grid, Node start, Node goal) {

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        Set<Node> closed = new HashSet<>();

        start.g = 0;
        start.h = heuristic(start, goal);
        start.f = start.h;

        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current == goal) return buildPath(current);

            closed.add(current);

            for (Node n : neighbors(grid, current)) {

                if (!n.walkable || closed.contains(n)) continue;

                double newG = current.g + 1;

                if (!open.contains(n) || newG < n.g) {
                    n.g = newG;
                    n.h = heuristic(n, goal);
                    n.f = n.g + n.h;
                    n.parent = current;

                    if (!open.contains(n)) open.add(n);
                }
            }
        }

        return new ArrayList<>();
    }

    static double heuristic(Node a, Node b) {
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    static List<Node> neighbors(Node[][] grid, Node n) {
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

    static List<Node> buildPath(Node end) {
        List<Node> path = new ArrayList<>();
        while (end != null) {
            path.add(end);
            end = end.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
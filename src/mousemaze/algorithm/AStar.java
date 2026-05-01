package mousemaze.algorithm;

import mousemaze.model.Node;
import java.util.*;

public class AStar {

    public static List<Node> findPath(Node[][] grid, Node start, Node goal) {

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        Set<Node> closed = new HashSet<>();

        start.g = 0;
        start.h = PathUtils.heuristic(start, goal);
        start.f = start.h;

        open.add(start);

        while (!open.isEmpty()) {
            Node current = open.poll();

            if (current == goal) return PathUtils.buildPath(current);

            closed.add(current);

            for (Node n : PathUtils.neighbors(grid, current)) {

                if (!n.walkable || closed.contains(n)) continue;

                double newG = current.g + 1;

                if (!open.contains(n) || newG < n.g) {
                    n.g = newG;
                    n.h = PathUtils.heuristic(n, goal);
                    n.f = n.g + n.h;
                    n.parent = current;

                    if (!open.contains(n)) open.add(n);
                }
            }
        }

        return new ArrayList<>();
    }
}
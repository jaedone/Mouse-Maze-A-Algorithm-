package algorithm;

import model.*;
import java.util.*;

public class AStar {

    private List<Edge> edges;
    private Node goal;

    public PriorityQueue<Node> openSet;
    public Set<Node> closedSet;

    public Node current;
    public boolean finished = false;

    public AStar(Node start, Node goal, List<Edge> edges) {
        this.edges = edges;
        this.goal = goal;

        openSet = new PriorityQueue<>(Comparator.comparing(n -> n.f));
        closedSet = new HashSet<>();

        start.g = 0;
        // start.h = heuristic(start, goal);
        start.f = start.h;

        openSet.add(start);
    }

    public void step() {
        if (openSet.isEmpty() || finished) return;

        current = openSet.poll();

        if (current == goal) {
            finished = true;
            return;
        }

        closedSet.add(current);

        for (Edge e : edges) {
            if (e.from == current) {
                Node neighbor = e.to;

                if (closedSet.contains(neighbor)) continue;

                double tentativeG = current.g + e.cost;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    // neighbor.h = heuristic(neighbor, goal);
                    neighbor.f = neighbor.g + neighbor.h;

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
    }

    // private double heuristic(Node a, Node b) {
    //     return Math.hypot(a.x - b.x, a.y - b.y);
    // }
}
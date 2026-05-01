package mousemaze.algorithm;

import mousemaze.model.Node;
import java.util.*;

public class AStarStepper {

    public PriorityQueue<Node> open;
    public Set<Node> closed;
    Node[][] grid;
    Node goal;

    public Node current;
    public String explanation = "";
    public boolean finished = false;
    public List<Node> finalPath = new ArrayList<>();

    List<StepState> history = new ArrayList<>();
    int stepIndex = -1;

    public AStarStepper(Node[][] grid, Node start, Node goal) {
        this.grid = grid;
        this.goal = goal;

        open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        closed = new HashSet<>();

        start.g = 0;
        start.h = PathUtils.heuristic(start, goal);
        start.f = start.g + start.h;

        open.add(start);
    }

    public void nextStep() {
        if (finished || open.isEmpty()) {
            finished = true;
            return;
        }

        current = open.poll();

        explanation = "Current node: (" + current.row + ", " + current.col + ")\n";
        explanation += "g=" + current.g + ", h=" + current.h + ", f=" + current.f + "\n\n";

        if (current == goal) {
            finalPath = PathUtils.buildPath(current);
            explanation += "Goal reached!";
            finished = true;
            return;
        }

        closed.add(current);

        for (Node n : PathUtils.neighbors(grid, current)) {

            if (!n.walkable || closed.contains(n)) continue;

            double newG = current.g + 1;

            if (!open.contains(n) || newG < n.g) {
                n.g = newG;
                n.h = PathUtils.heuristic(n, goal);
                n.f = n.g + n.h;
                n.parent = current;

                if (open.contains(n)) open.remove(n);
                open.add(n);
            }
        }
    }

    public void previousStep() {
        // Optional: keep your old implementation if needed
    }

    static class StepState {
        // Optional advanced feature
    }
}
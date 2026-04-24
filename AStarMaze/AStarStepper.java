import java.util.*;

public class AStarStepper {

    PriorityQueue<Node> open;
    Set<Node> closed;
    Node[][] grid;
    Node goal;

    Node current;
    String explanation = "";
    boolean finished = false;
    List<Node> finalPath = new ArrayList<>();
    List<StepState> history = new ArrayList<>();
    int stepIndex = -1;

    public AStarStepper(Node[][] grid, Node start, Node goal) {
        this.grid = grid;
        this.goal = goal;

        open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        closed = new HashSet<>();

        start.g = 0;
        start.h = heuristic(start, goal);
        start.f = start.g + start.h;

        open.add(start);
    }

    public void nextStep() {
        if (finished || open.isEmpty()) {
            finished = true;
            return;
        }

        current = open.poll();

        explanation = "Currently considering node: (" + current.row + ", " + current.col + ")\n";
        explanation += "g(n) = " + current.g + "\n";
        explanation += "h(n) = " + current.h + "\n";
        explanation += "f(n) = g(n) + h(n) = " + current.f + "\n\n";

        if (current == goal) {
            finalPath = buildPath(current);
            explanation += "Goal reached!";
            finished = true;

            history.add(new StepState(open, closed, current, finalPath, explanation, grid));
            stepIndex++;

            return;
        }

        closed.add(current);

        explanation += "Checking neighbors:\n";

        for (Node n : neighbors(grid, current)) {

            if (!n.walkable || closed.contains(n)) continue;

            double newG = current.g + 1;

            explanation += "Neighbor (" + n.row + ", " + n.col + "):\n";
            explanation += "new g = " + newG + "\n";
            explanation += "h = " + heuristic(n, goal) + "\n";
            explanation += "f = " + newG + " + " + heuristic(n, goal) + " = " + (newG + heuristic(n, goal)) + "\n\n";

            if (!open.contains(n) || newG < n.g) {
                n.g = newG;
                n.h = heuristic(n, goal);
                n.f = n.g + n.h;
                n.parent = current;

                if (open.contains(n)) {
                    open.remove(n);
                }

                open.add(n);
            }
        }

        history.add(new StepState(open, closed, current, finalPath, explanation, grid));
        stepIndex++;
    }

    public void previousStep() {
        if (stepIndex <= 0) return;

        stepIndex--;

        StepState prev = history.get(stepIndex);

        open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        open.addAll(prev.open);

        closed = new HashSet<>(prev.closed);
        current = prev.current;
        finalPath = new ArrayList<>(prev.finalPath);
        explanation = prev.explanation;

        finished = false;

        for (Node n : prev.nodeStates.keySet()) {
            NodeState ns = prev.nodeStates.get(n);
            n.g = ns.g;
            n.h = ns.h;
            n.f = ns.f;
            n.parent = ns.parent;
        }
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

    class NodeState {
        double g, h, f;
        Node parent;

        NodeState(double g, double h, double f, Node parent) {
            this.g = g;
            this.h = h;
            this.f = f;
            this.parent = parent;
        }
    }

    class StepState {
      PriorityQueue<Node> open;
      Set<Node> closed;
      Node current;
      List<Node> finalPath;
      String explanation;
      Map<Node, NodeState> nodeStates;

        StepState(PriorityQueue<Node> open, Set<Node> closed, Node current, List<Node> finalPath, String explanation, Node[][] grid) {

            this.open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
            this.open.addAll(open);

            this.closed = new HashSet<>(closed);
            this.current = current;
            this.finalPath = new ArrayList<>(finalPath);
            this.explanation = explanation;

            nodeStates = new HashMap<>();

            for (Node[] row : grid) {
                for (Node n : row) {
                    nodeStates.put(n, new NodeState(n.g, n.h, n.f, n.parent));
                }
            }
        }
    }
}
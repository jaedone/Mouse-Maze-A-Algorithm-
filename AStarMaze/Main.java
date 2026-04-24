import javax.swing.JFrame;
import javax.swing.Timer;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        String[][] grid = loadMaze("maze.txt");
        Node[][] nodes = buildNodes(grid);

        Node start = find(nodes, "S");
        Node goal = find(nodes, "G");

        List<Node> path = AStar.findPath(nodes, start, goal);

        MazePanel panel = new MazePanel(nodes);

        JFrame frame = new JFrame("A* Maze");
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 🎬 animation
        new Timer(80, e -> {
            if (!path.isEmpty()) {
                panel.path.add(path.remove(0));
                panel.repaint();
            }
        }).start();
    }

    public static String[][] loadMaze(String file) throws Exception {
        List<String[]> rows = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            rows.add(line.trim().split("\\s+"));
        }

        return rows.toArray(new String[0][]);
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
                if (n.value.equals(target)) return n;
            }
        }
        return null;
    }
}
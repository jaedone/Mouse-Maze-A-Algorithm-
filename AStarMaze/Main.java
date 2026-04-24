import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.BorderLayout;

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

        AStarStepper stepper = new AStarStepper(nodes, start, goal);
        // List<Node> path = AStar.findPath(nodes, start, goal);

        MazePanel panel = new MazePanel(nodes);
        panel.setStepper(stepper);

        JTextArea explanationArea = new JTextArea(15, 65);
        explanationArea.setEditable(false);
        explanationArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JButton nextButton = new JButton("Next Iteration");
        JButton runButton = new JButton("Run");
        JButton restartButton = new JButton("Restart");
        JButton backButton = new JButton("Back");

        Timer timer = new Timer(300, e -> {
            stepper.nextStep();
            explanationArea.setText(stepper.explanation);
            panel.repaint();

            if (stepper.finished) {
                ((Timer) e.getSource()).stop();
                runButton.setText("Run");
                nextButton.setEnabled(false);
            }
        });

        nextButton.addActionListener(e -> {
            stepper.nextStep();
            explanationArea.setText(stepper.explanation);
            panel.repaint();

            if (stepper.finished) {
                nextButton.setEnabled(false);
            }
        });

        runButton.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                runButton.setText("Run");
                nextButton.setEnabled(true);
                backButton.setEnabled(true);
            } else {
                timer.start();
                runButton.setText("Pause");
                nextButton.setEnabled(false);
                backButton.setEnabled(false);
            }
        });

        restartButton.addActionListener(e -> {

            timer.stop();

            resetNodes(nodes);

            Node startNode = find(nodes, "S");
            Node goalNode = find(nodes, "G");

            AStarStepper newStepper = new AStarStepper(nodes, startNode, goalNode);
            panel.setStepper(newStepper);

            stepper.open = newStepper.open;
            stepper.closed = newStepper.closed;
            stepper.current = newStepper.current;
            stepper.finished = newStepper.finished;
            stepper.finalPath = newStepper.finalPath;

            explanationArea.setText("");
            panel.repaint();

            nextButton.setEnabled(true);
            runButton.setEnabled(true);
            runButton.setText("Run");
        });

        backButton.addActionListener(e -> {
            stepper.previousStep();
            explanationArea.setText(stepper.explanation);
            panel.repaint();
        });

        JFrame frame = new JFrame("A* Maze");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(runButton);
        buttonPanel.add(restartButton);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JScrollPane(explanationArea), BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 🎬 animation
        // new Timer(80, e -> {
        //     if (!path.isEmpty()) {
        //         panel.path.add(path.remove(0));
        //         panel.repaint();
        //     }
        // }).start();
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

    public static void resetNodes(Node[][] nodes) {
        for (Node[] row : nodes) {
            for (Node n : row) {
                n.g = 0;
                n.h = 0;
                n.f = 0;
                n.parent = null;
            }
        }
    }
}
package mousemaze.app;

import javax.swing.*;
import mousemaze.algorithm.AStarStepper;
import mousemaze.loader.MazeLoader;
import mousemaze.model.Node;
import mousemaze.ui.MazePanel;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Node[][] nodes = MazeLoader.load("maze.txt");

        Node start = MazeLoader.find(nodes, "S");
        Node goal = MazeLoader.find(nodes, "G");

        AStarStepper stepper = new AStarStepper(nodes, start, goal);

        MazePanel panel = new MazePanel(nodes);
        panel.setStepper(stepper);

        JTextArea explanation = new JTextArea(10, 40);
        explanation.setEditable(false);

        JButton next = new JButton("Next");

        next.addActionListener(e -> {
            stepper.nextStep();
            explanation.setText(stepper.explanation);
            panel.repaint();
        });

        JFrame frame = new JFrame("Mouse Maze A*");
        frame.setLayout(new BorderLayout());

        frame.add(panel, BorderLayout.CENTER);
        frame.add(new JScrollPane(explanation), BorderLayout.EAST);
        frame.add(next, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
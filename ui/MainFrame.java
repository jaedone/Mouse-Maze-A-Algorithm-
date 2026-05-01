package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("A* Mouse Maze Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 0));

        MazePanel mazePanel = new MazePanel();
        ControlPanel controlPanel = new ControlPanel(mazePanel);

        add(mazePanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.EAST);

        setResizable(false);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
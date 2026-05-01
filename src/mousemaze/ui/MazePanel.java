package mousemaze.ui;

import javax.swing.*;
import mousemaze.algorithm.AStarStepper;
import mousemaze.algorithm.PathUtils;
import mousemaze.model.Node;

import java.awt.*;
import java.util.List;

public class MazePanel extends JPanel {

    Node[][] grid;
    AStarStepper stepper;

    int size = 35;
    int offsetX = 40;
    int offsetY = 40;

    public MazePanel(Node[][] grid) {
        this.grid = grid;
        setPreferredSize(new Dimension(
                grid[0].length * size + offsetX,
                grid.length * size + offsetY
        ));
    }

    public void setStepper(AStarStepper stepper) {
        this.stepper = stepper;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {

                Node n = grid[r][c];

                g.setColor(n.walkable ? Color.WHITE : Color.BLACK);

                if (stepper != null) {

                    if (stepper.closed.contains(n)) g.setColor(Color.LIGHT_GRAY);
                    if (stepper.open.contains(n)) g.setColor(Color.ORANGE);

                    if (stepper.current != null) {
                        List<Node> path = PathUtils.buildPath(stepper.current);
                        if (path.contains(n)) g.setColor(Color.CYAN);
                    }

                    if (n == stepper.current) g.setColor(Color.BLUE);
                    if (stepper.finalPath.contains(n)) g.setColor(Color.GREEN);
                }

                int x = offsetX + c * size;
                int y = offsetY + r * size;

                g.fillRect(x, y, size, size);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, size, size);
            }
        }
    }
}
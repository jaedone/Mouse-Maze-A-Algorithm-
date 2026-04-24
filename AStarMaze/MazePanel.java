import javax.swing.*;
import java.awt.*;
import java.util.*;
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

        g.setColor(Color.BLACK);

        for (int c = 0; c < grid[0].length; c++) {
            int x = offsetX + c * size + size / 2;
            g.drawString(String.valueOf(c), x - 5, offsetY - 10);
        }

        for (int r = 0; r < grid.length; r++) {
            int y = offsetY + r * size + size / 2;
            g.drawString(String.valueOf(r), offsetX - 25, y + 5);
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {

                Node n = grid[r][c];

                if (!n.walkable) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                if (stepper != null) {

                    if (stepper.closed.contains(n)) {
                        g.setColor(Color.LIGHT_GRAY);
                    }

                    if (stepper.open.contains(n)) {
                        g.setColor(Color.ORANGE);
                    }
                    
                    if (stepper.current != null) {
                        List<Node> currentPath = buildPath(stepper.current);

                        if (currentPath.contains(n)) {
                            g.setColor(Color.CYAN);
                        }
                    }

                    if (n == stepper.current) {
                        g.setColor(Color.BLUE);
                    }

                    if (stepper.finalPath.contains(n)) {
                        g.setColor(Color.GREEN);
                    }
                }

                int x = offsetX + c * size;
                int y = offsetY + r * size;

                g.fillRect(x, y, size, size);

                g.setColor(Color.GRAY);
                g.drawRect(x, y, size, size);

                // g.drawString(n.value, x + 10, y + 22);
            }
        }
    }

    private List<Node> buildPath(Node end) {
        List<Node> path = new ArrayList<>();

        while (end != null) {
            path.add(end);
            end = end.parent;
        }

        return path;
    }
}